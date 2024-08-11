import main.Calculator;
import main.CalculatorImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import static org.junit.Assert.*;

public class CalculatorRMITest {

    private Registry registry;
    private Calculator calculator;

    /**
     * Preparation for tests.
     */
    @Before
    public void setUp() throws Exception {
        // Start the RMI registry
        registry = LocateRegistry.createRegistry(1099);

        // Create and export the remote object
        calculator = new CalculatorImpl();
        Calculator stub = (Calculator) UnicastRemoteObject.exportObject(calculator, 0);

        // Bind the remote object's stub in the registry
        registry.bind("Calculator", stub);
    }

    @After
    public void tearDown() throws Exception {
        // Unbind and close the registry
        registry.unbind("Calculator");
        UnicastRemoteObject.unexportObject(calculator, true);
    }

    /**
     * Test basic push, pop-up, and isEmpty functionality with single Client
     */
    @Test
    public void testSingleClient() throws Exception {
        // Lookup the remote object
        Registry registry = LocateRegistry.getRegistry();
        Calculator stub = (Calculator) registry.lookup("Calculator");

        stub.initClientId();
        stub.pushValue(10);
        stub.pushValue(20);

        assertEquals(20, stub.pop());
        assertEquals(10, stub.pop());
        assertTrue(stub.isEmpty());
    }

    /**
     * Test basic push, pop-up, and isEmpty functionality with multiple Clients
     */
    @Test
    public void testMultipleClients() throws Exception {
        // Lookup the remote object
        Registry registry = LocateRegistry.getRegistry();
        Calculator stub = (Calculator) registry.lookup("Calculator");

        Runnable clientTask = () -> {
            try {
                Calculator clientStub = (Calculator) registry.lookup("Calculator");
                clientStub.initClientId();
                clientStub.pushValue(10);
                clientStub.pushValue(20);
                assertEquals(20, clientStub.pop());
                assertEquals(10, clientStub.pop());
                assertTrue(clientStub.isEmpty());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Thread client1 = new Thread(clientTask);
        Thread client2 = new Thread(clientTask);
        Thread client3 = new Thread(clientTask);
        Thread client4 = new Thread(clientTask);
        Thread client5 = new Thread(clientTask);

        client1.start();
        client2.start();
        client3.start();
        client4.start();
        client5.start();

        client1.join();
        client2.join();
        client3.join();
        client4.join();
        client5.join();
    }

    /**
     * Test pushOperation functionality with single Client
     */
    @Test
    public void testOperationsSingleClient() throws Exception {
        // Lookup the remote object
        Registry registry = LocateRegistry.getRegistry();
        Calculator stub = (Calculator) registry.lookup("Calculator");

        stub.initClientId();
        stub.pushValue(10);
        stub.pushValue(20);
        stub.pushValue(30);

        stub.pushOperation("min");
        assertEquals(10, stub.pop());

        stub.pushValue(10);
        stub.pushValue(20);
        stub.pushValue(30);

        stub.pushOperation("max");
        assertEquals(30, stub.pop());

        stub.pushValue(10);
        stub.pushValue(20);
        stub.pushValue(30);

        stub.pushOperation("gcd");
        assertEquals(10, stub.pop());

        stub.pushValue(10);
        stub.pushValue(20);
        stub.pushValue(30);

        stub.pushOperation("lcm");
        assertEquals(60, stub.pop());
    }

    /**
     * Test pushOperation functionality with multiple Clients
     */
    @Test
    public void testOperationsMultipleClients() throws Exception {
        // Lookup the remote object
        Registry registry = LocateRegistry.getRegistry();
        Calculator stub = (Calculator) registry.lookup("Calculator");

        Runnable clientTask = () -> {
            try {
                Calculator clientStub = (Calculator) registry.lookup("Calculator");
                clientStub.initClientId();
                clientStub.pushValue(10);
                clientStub.pushValue(20);
                clientStub.pushValue(30);

                clientStub.pushOperation("min");
                assertEquals(10, clientStub.pop());

                clientStub.pushValue(10);
                clientStub.pushValue(20);
                clientStub.pushValue(30);

                clientStub.pushOperation("max");
                assertEquals(30, clientStub.pop());

                clientStub.pushValue(10);
                clientStub.pushValue(20);
                clientStub.pushValue(30);

                clientStub.pushOperation("gcd");
                assertEquals(10, clientStub.pop());

                clientStub.pushValue(10);
                clientStub.pushValue(20);
                clientStub.pushValue(30);

                clientStub.pushOperation("lcm");
                assertEquals(60, clientStub.pop());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Thread client1 = new Thread(clientTask);
        Thread client2 = new Thread(clientTask);
        Thread client3 = new Thread(clientTask);
        Thread client4 = new Thread(clientTask);
        Thread client5 = new Thread(clientTask);

        client1.start();
        client2.start();
        client3.start();
        client4.start();
        client5.start();

        client1.join();
        client2.join();
        client3.join();
        client4.join();
        client5.join();
    }

    /**
     * Test delayPop functionality with single Client
     */
    @Test
    public void testDelayPopSingleClient() throws Exception {
        // Lookup the remote object
        Registry registry = LocateRegistry.getRegistry();
        Calculator stub = (Calculator) registry.lookup("Calculator");

        stub.initClientId();
        stub.pushValue(10);
        stub.pushValue(20);

        assertEquals(20, stub.delayPop(1000));
        assertEquals(10, stub.delayPop(1000));
        assertTrue(stub.isEmpty());
    }

    /**
     * Test delayPop functionality with multiple Clients
     */
    @Test
    public void testDelayPopMultipleClients() throws Exception {
        // Lookup the remote object
        Registry registry = LocateRegistry.getRegistry();
        Calculator stub = (Calculator) registry.lookup("Calculator");

        Runnable clientTask = () -> {
            try {
                Calculator clientStub = (Calculator) registry.lookup("Calculator");
                clientStub.initClientId();
                clientStub.pushValue(10);
                clientStub.pushValue(20);
                assertEquals(20, clientStub.delayPop(1000));
                assertEquals(10, clientStub.delayPop(1000));
                assertTrue(clientStub.isEmpty());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Thread client1 = new Thread(clientTask);
        Thread client2 = new Thread(clientTask);
        Thread client3 = new Thread(clientTask);
        Thread client4 = new Thread(clientTask);
        Thread client5 = new Thread(clientTask);

        client1.start();
        client2.start();
        client3.start();
        client4.start();
        client5.start();

        client1.join();
        client2.join();
        client3.join();
        client4.join();
        client5.join();
    }
}

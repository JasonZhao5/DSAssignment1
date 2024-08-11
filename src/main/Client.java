package main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) throws RemoteException {

        Calculator stub = null;
        try {
            Registry registry = LocateRegistry.getRegistry(null);
            stub = (Calculator) registry.lookup("Calculator");

            stub.initClientId();

            // Set up an interface.
            CommandLineInterface anInterface = new CommandLineInterface();
            anInterface.setScanner(stub, Calculator.class);
        } catch (Exception e) {
            System.err.println("main.Client exception: " + e);
            e.printStackTrace();
        } finally {
            //Remove threadLocal to avoid problems in the case of thread reuse.
            if (stub != null) {
                stub.removeClientId();
            }
        }
    }
}
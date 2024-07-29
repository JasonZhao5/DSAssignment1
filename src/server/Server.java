package server;

import Factory.impl.RemoteCalculatorFactory;
import service.Calculator;

import java.rmi.Remote;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Stack;

public class Server {

    private final HashMap<String, Stack<Integer>> stackMap = new HashMap<String, Stack<Integer>>();

    public static void main(String[] args) {


        try {
            LocateRegistry.createRegistry(1099);

            RemoteCalculatorFactory calculatorFactory = new RemoteCalculatorFactory();
            Remote calculator = calculatorFactory.createProduct();
            Calculator stub = (Calculator) UnicastRemoteObject.exportObject(calculator, 0);

            Server server = new Server();
//            String clientHost = stub.getHostName();
            String clientHost = "";
            Stack<Integer> stack = stub.getStack();
            server.stackMap.computeIfAbsent(clientHost, v -> stack);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Calculator", stub);

            System.err.println("server.Server ready");
        } catch (Exception e) {
            System.err.println("server.Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
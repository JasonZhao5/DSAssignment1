package main;

import java.rmi.Remote;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server {


    public static void main(String[] args) {


        try {
            LocateRegistry.createRegistry(1099);

            RemoteCalculatorFactory calculatorFactory = new RemoteCalculatorFactory();
            Remote calculator = calculatorFactory.createProduct();
            Calculator stub = (Calculator) UnicastRemoteObject.exportObject(calculator, 0);


            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Calculator", stub);

            System.err.println("main.Server ready");
        } catch (Exception e) {
            System.err.println("main.Server exception: " + e);
            e.printStackTrace();
        }
    }
}
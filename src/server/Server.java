package server;

import Factory.impl.RemoteCalculatorFactory;
import service.Calculator;

import java.rmi.Remote;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

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

            System.err.println("server.Server ready");
        } catch (Exception e) {
            System.err.println("server.Server exception: " + e);
            e.printStackTrace();
        }
    }
}
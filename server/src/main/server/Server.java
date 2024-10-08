package main.server;


import main.Factory.impl.RemoteCalculatorFactory;
import main.service.Calculator;

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

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("server.Server exception: " + e);
            e.printStackTrace();
        }
    }
}
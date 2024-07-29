package client;

import service.Calculator;
import util.CommandLineInterface;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Calculator stub = (Calculator) registry.lookup("Calculator");

            //set up an interface
            CommandLineInterface anInterface = new CommandLineInterface();
            anInterface.setScanner(stub, Calculator.class);
        } catch (Exception e) {
            System.err.println("client.Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
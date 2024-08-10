package client;

import service.Calculator;
import util.CommandLineInterface;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) throws RemoteException {

        Calculator stub = null;
        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            stub = (Calculator) registry.lookup("Calculator");

            stub.initClientId();

            // Set up an interface.
            CommandLineInterface anInterface = new CommandLineInterface();
            anInterface.setScanner(stub, Calculator.class);
        } catch (Exception e) {
            System.err.println("client.Client exception: " + e);
            e.printStackTrace();
        } finally {
            //Remove threadLocal to avoid problems in the case of thread reuse.
            if (stub != null) {
                stub.removeClientId();
            }
        }
    }
}
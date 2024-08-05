package service.impl;

import po.Operation;
import service.Calculator;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

import static util.GCDUtil.gcd;

public class CalculatorImpl implements Calculator {
    final private Stack<Integer> stack = new Stack<>();

    private String hostName;


    @Override
    public Stack<Integer> getStack() throws RemoteException {
        initHostName();
        return stack;
    }

    @Override
    public void pushValue(int val) throws RemoteException {
        initHostName();
        stack.push(val);
    }

    @Override
    public void pushOperation(String operator) throws RemoteException {
        if (stack.isEmpty()) {
            throw new RuntimeException("The stack is empty.");
        }
        Operation operation = Operation.valueOf(operator);
        switch (operation) {
            case Operation.min -> {
                Integer min = stack.stream().min(Integer::compareTo).get();
                stack.clear();
                stack.push(min);
            }
            case Operation.max -> {
                Integer max = stack.stream().max(Integer::compareTo).get();
                stack.clear();
                stack.push(max);
            }
            case Operation.gcd -> {
                int gcd = stack.pop();
                while (!stack.isEmpty()) {
                    gcd = gcd(gcd, stack.pop());
                }
                stack.push(gcd);
            }
            case Operation.lcm -> {
                Map<Integer, Long> map = stack.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
                Map.Entry<Integer, Long> entry = map.entrySet().stream().max(Map.Entry.comparingByValue()).get();
                stack.clear();
                stack.push(entry.getKey());
            }
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        }

        initHostName();
    }

    @Override
    public int pop() throws RemoteException {
        initHostName();
        if (stack.isEmpty()) {
            throw new RuntimeException("The stack is empty.");
        } else
            return stack.pop();
    }

    @Override
    public boolean isEmpty() throws RemoteException {
        initHostName();
        return stack.isEmpty();
    }

    @Override
    public int delayPop(int millis) throws InterruptedException, RemoteException {
        initHostName();
        Thread.sleep(millis);
        System.out.println("delayPop: waited for " + millis + "ms");
        return stack.pop();
    }

    /**
     * Init hostname if absent.
     */
    private void initHostName() {
        if (hostName == null) {
            try {
                hostName = RemoteServer.getClientHost();
            } catch (ServerNotActiveException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String getHostName() {
        return hostName;
    }
}

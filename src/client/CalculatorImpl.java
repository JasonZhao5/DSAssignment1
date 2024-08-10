package client;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


public class CalculatorImpl implements Calculator {
    private final ConcurrentHashMap<String, Stack<Integer>> stackMap = new ConcurrentHashMap<>();
    ThreadLocal<String> clientId = ThreadLocal.withInitial(() -> UUID.randomUUID().toString());
    private String hostName;


    @Override
    public Stack<Integer> getStack() throws RemoteException {
        return stackMap.get(clientId.get());
    }

    @Override
    public void pushValue(int val) throws RemoteException {
        stackMap.get(clientId.get()).push(val);
    }

    @Override
    public void pushOperation(String operator) throws RemoteException {
        Stack<Integer> stack = stackMap.get(clientId.get());
        if (stack.isEmpty()) {
            throw new RuntimeException("The stack is empty.");
        }
        Operation operation = Operation.valueOf(operator);
        switch (operation) {
            case min:
                Integer min = stack.stream().min(Integer::compareTo).get();
                stack.clear();
                stack.push(min);
                break;
            case max:
                Integer max = stack.stream().max(Integer::compareTo).get();
                stack.clear();
                stack.push(max);
                break;
            case gcd:
                int gcd = stack.pop();
                while (!stack.isEmpty()) {
                    gcd = GCDUtil.gcd(gcd, stack.pop());
                }
                stack.push(gcd);
                break;
            case lcm:
                Map<Integer, Long> map = stack.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
                Map.Entry<Integer, Long> entry = map.entrySet().stream().max(Map.Entry.comparingByValue()).get();
                stack.clear();
                stack.push(entry.getKey());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operation);
        }


    }

    @Override
    public int pop() throws RemoteException {
        Stack<Integer> stack = stackMap.get(clientId.get());
        if (stack.isEmpty()) {
            throw new RuntimeException("The stack is empty.");
        } else return stack.pop();
    }

    @Override
    public boolean isEmpty() throws RemoteException {
        return stackMap.get(clientId.get()).isEmpty();
    }

    @Override
    public int delayPop(int millis) throws InterruptedException, RemoteException {
        Thread.sleep(millis);
        System.out.println("delayPop: waited for " + millis + "ms");
        return stackMap.get(clientId.get()).pop();
    }

    /**
     * Init hostname if absent.
     */
    public void initClientId() throws RemoteException {
        this.clientId.get();
        stackMap.putIfAbsent(clientId.get(), new Stack<>());
    }

    @Override
    public void removeClientId() throws RemoteException {
        clientId.remove();
    }

    @Override
    public String getClientId() throws RemoteException {
        return clientId.get();
    }
}

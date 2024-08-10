package po;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Stack;

public interface Calculator extends Remote {


    /**
     * Take val and push it on to the top of the stack.
     *
     * @param val value to push
     */
    void pushValue(int val) throws RemoteException;

    /**
     * Method to get the stack of current Calculator.
     *
     * @return stack of current Calculator
     */
    Stack<Integer> getStack() throws RemoteException;

    /**
     * Push a String containing an operator ("min", "max", "lcm", "gcd") to the stack,
     * which will cause the server to pop all the values on the stack and:
     * <p>
     * for min - push the min value of all the popped values;
     * <p>
     * for max - push the max value of all the popped values;
     * <p>
     * for lcm - push the least common multiple of all the popped values;
     * <p>
     * for gcd - push the greatest common divisor of all the popped values.
     *
     * @param operator operator
     */
    void pushOperation(String operator) throws RemoteException;

    /**
     * Pop the top of the stack and return it to the client.
     *
     * @return top value of the stack
     */
    int pop() throws RemoteException;

    /**
     * Return true if the stack is empty, false otherwise.
     *
     * @return true empty or false for not
     */
    boolean isEmpty() throws RemoteException;

    /**
     * Wait millis milliseconds before carrying out the pop operation as above.
     *
     * @param millis time to wait
     * @return the top value of the stack
     * @throws InterruptedException wait() method may cause InterruptedException
     */
    int delayPop(int millis) throws InterruptedException, RemoteException;

    /**
     * Get the hostname of the client.
     *
     * @return hostname of the client
     */
    String getClientId() throws RemoteException;

    /**
     * Initial a client ID for the client.
     */
    void initClientId() throws RemoteException;

    /**
     * Remove the Client ID.
     */
    void removeClientId() throws RemoteException;
}

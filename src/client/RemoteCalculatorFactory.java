package client;

import java.rmi.Remote;

public class RemoteCalculatorFactory implements RemoteFactory {
    Calculator calculator = new CalculatorImpl();


    @Override
    public Remote createProduct() {
        return calculator;
    }
}

package Factory.impl;

import Factory.RemoteFactory;
import client.Calculator;
import client.CalculatorImpl;

import java.rmi.Remote;

public class RemoteCalculatorFactory implements RemoteFactory {
    Calculator calculator = new CalculatorImpl();


    @Override
    public Remote createProduct() {
        return calculator;
    }
}

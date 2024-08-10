package Factory.impl;

import Factory.RemoteFactory;
import po.Calculator;
import po.CalculatorImpl;

import java.rmi.Remote;

public class RemoteCalculatorFactory implements RemoteFactory {
    Calculator calculator = new CalculatorImpl();


    @Override
    public Remote createProduct() {
        return calculator;
    }
}

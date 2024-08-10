package Factory.impl;

import Factory.RemoteFactory;
import po.service.Calculator;
import po.service.impl.CalculatorImpl;

import java.rmi.Remote;

public class RemoteCalculatorFactory implements RemoteFactory {
    Calculator calculator = new CalculatorImpl();


    @Override
    public Remote createProduct() {
        return calculator;
    }
}

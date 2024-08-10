package main.Factory.impl;


import main.Factory.RemoteFactory;
import main.service.Calculator;
import main.service.impl.CalculatorImpl;

import java.rmi.Remote;

public class RemoteCalculatorFactory implements RemoteFactory {
    Calculator calculator = new CalculatorImpl();


    @Override
    public Remote createProduct() {
        return calculator;
    }
}

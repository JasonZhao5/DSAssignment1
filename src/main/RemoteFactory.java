package main;

import java.rmi.Remote;

public interface RemoteFactory {
    Remote createProduct();
}

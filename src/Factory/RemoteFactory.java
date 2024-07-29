package Factory;

import java.rmi.Remote;
import java.util.Stack;

public interface RemoteFactory {
    Remote createProduct();
}

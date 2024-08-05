package Factory;

import java.rmi.Remote;

public interface RemoteFactory {
    Remote createProduct();
}

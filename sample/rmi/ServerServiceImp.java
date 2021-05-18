package sample.rmi;

import java.rmi.RemoteException;

public class ServerServiceImp implements ServerService {
    public ServerServiceImp() {}
    
    @Override
    public String sayHello() {
        return "hello from server service";
    }

    @Override
    public int sum(int x, int y) throws RemoteException {
        return x + y;
    }
}

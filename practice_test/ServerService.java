package practice_test;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerService extends Remote {
    public boolean chooseMove(int x, int y, String player) throws RemoteException;

    public String isWin() throws RemoteException;
    
    public String display() throws RemoteException;

    public String getClientToken() throws RemoteException;

    public boolean authentication(String username, String password) throws RemoteException;
    public boolean createNewAccount(String username, String password) throws RemoteException;
}

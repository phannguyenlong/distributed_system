package hw4_rmi_server.hw_11_05;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerService extends Remote {
    public List<Media> getMedia(String type) throws RemoteException;

    public void createMedia(String type, String name, String author) throws RemoteException;
}

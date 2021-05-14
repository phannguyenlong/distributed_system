package practice_test;

import java.rmi.RemoteException;

public class ServerImp implements ServerService {
    private Game game;
    private int numerOfClient = 0;
    private int clientNumber;
    private String[] clientSymbol= {"X", "O"};

    public ServerImp() {
        game = new Game();
    }

    @Override
    public boolean chooseMove(int x, int y, String player) throws RemoteException {
        return game.setMove(x, y, player);
    }

    @Override
    public String isWin() {
        return game.checkWin();
    }

    @Override
    public String display() throws RemoteException {
        return game.toString();
    }

    @Override
    public String getClientToken() throws RemoteException {
        clientNumber = numerOfClient;
        numerOfClient++;
        return clientSymbol[clientNumber];
    }
}

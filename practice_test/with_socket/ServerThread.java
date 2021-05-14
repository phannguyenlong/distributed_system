package practice_test.with_socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ServerThread implements Runnable {
    ServerImp obj = null;
    Socket client = null;

    public ServerThread(ServerImp obj, Socket client) {
        this.obj = obj;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());
            out.writeUTF("Input username: ");
            String username = in.readUTF();
            out.writeUTF("Input password: ");
            String password = in.readUTF();
            if (!obj.authentication(username, password)) {
                out.writeUTF("you dont have account");

                out.writeUTF("=========Createing new account=========\nInput username: ");
                String user = in.readUTF();
                out.writeUTF("Input password: ");
                String pass = in.readUTF();
                if (!obj.createNewAccount(user, pass)) {
                    out.writeUTF("Account exist. Try again");
                }
                out.writeUTF("Account created. Please login again");
                client.close();
            }

            String clientSymbol = obj.getClientToken();
            System.out.println(clientSymbol);
            
            String message = obj.display();
            boolean isCanMove = true;
            while (obj.isWin() == null) {
                message = !isCanMove ? "Already move Or you must wait for your openent\n" : "";
                message += obj.display() + "\n" + "Player " + clientSymbol + " choose your move[1 - 3]: \n";
                out.writeUTF(message);
                isCanMove = true;
                String input = in.readUTF();
                int x = Integer.valueOf(input.split(" ")[0]);
                int y = Integer.valueOf(input.split(" ")[1]);
                if (!obj.chooseMove(x, y, clientSymbol))
                    isCanMove = false;
            }
            if (obj.isWin().equals("draw"))
                out.writeUTF("Game over. Draw");
            else
                out.writeUTF("Game Over. Player " + obj.isWin() + " win!" );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

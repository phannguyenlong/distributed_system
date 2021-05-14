package practice_test.with_socket;

public class Game {
    private String[][] match = { { "     ", "     ", "     " }, { "     ", "     ", "     " }, { "     ", "     ", "     " } };
    private String nextPlayer = "X";
    private String winner = null;
    private int numberOfMove = 0;

    public Game() {
    }
    
    public boolean setMove(int x, int y, String player) {
        String input = "  " + player + "  ";
        
        if (!player.equals(nextPlayer)) {
            return false;
        }

        if (match[x-1][y-1].equals("  X  ") || match[x-1][y-1].equals("  O  "))
            return false;

        match[x - 1][y - 1] = input;

        if (match[x-1][0].equals(input) && match[x-1][1].equals(input) && match[x-1][2].equals(input)) // check x row
            winner = player;
        if (match[1][y-1].equals(input) && match[1][y-1].equals(input) && match[2][y-1].equals(input)) // check y column
            winner = player;
        if (x == y || x == (3 - y)) { // check diagonal
            if (match[0][0].equals(input) && match[2][2].equals(input))
                winner = player;
            if (match[0][2].equals(input) && match[2][0].equals(input))
                winner = player;
        }

        if (nextPlayer.equals("X"))
            nextPlayer = "O";
        else if (nextPlayer.equals("O"))
            nextPlayer = "X";

        System.out.println(nextPlayer);
        numberOfMove++;
        return true;
    }

    public String checkWin() {
        if (numberOfMove == 9)
            return "draw";
        return winner;
    }

    @Override
    public String toString() {
        String display = "";
        for (String[] row : match) {
            for (String cell : row) {
                display += "|" + cell ;
            }
            display += "|\n-------------------\n";
        }

        return display;
    }
}

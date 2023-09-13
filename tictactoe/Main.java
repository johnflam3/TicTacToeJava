package tictactoe;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char [][] grid = new char[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j <3; j++) {
                grid[i][j] = ' ';
            }
        }
        printGrid(grid);

        char currentPlayer = 'X'; // Player X always starts

        while (endGame(grid)) {
            enterChar(grid, currentPlayer);
            printGrid(grid);
            String result = analyzeGame(grid);
            System.out.println(result);

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Changing players after a move
        }
        System.exit(0);
    }

    public static void printGrid(char [][] grid) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void enterChar(char[][] grid, char player) {
        Scanner s = new Scanner(System.in);
        int x, y;
        boolean validInput = false;

        while (!validInput) {
            try {
                x = s.nextInt();
                y = s.nextInt();
                if (x < 1 || x > 3 || y < 1 || y > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (grid[x - 1][y - 1] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    grid[x - 1][y - 1] = player;
                    validInput = true; // Correct input, we can exit the loop
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    public static boolean winChecker(char[][] grid, char player) {
        for(int i = 0; i < 3; i++) {
            if(grid[i][0] == player && grid[i][1] == player && grid[i][2] == player)
                return true;
            else if(grid[0][i] == player && grid[1][i] == player && grid[2][i] == player)
                return true;
            else if(grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) {
                return true;
            }
            else if(grid[2][0] == player && grid[1][1] == player && grid[0][2] == player) {
                return true;
            }
        }
        return false;
    }

    public static int countX(char grid[][]) {
        int xCounter = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(grid[i][j] == 'X') {
                    xCounter++;
                }
            }
        }
        return xCounter;
    }

    public static int countO(char grid[][]) {
        int oCounter = 0;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(grid[i][j] == 'O') {
                    oCounter++;
                }
            }
        }
        return oCounter;
    }

    public static String analyzeGame(char[][] grid) {
        int xCount = countX(grid);
        int oCount = countO(grid);

        boolean xWins = winChecker(grid, 'X');
        boolean oWins = winChecker(grid, 'O');

        if ((xWins && oWins) || Math.abs(xCount - oCount) >= 2) {
            return "Impossible";
        } else if (xWins) {
            return "X wins";
        } else if (oWins) {
            return "O wins";
        } else if (countX(grid) + countO(grid) == 9) {
            return "Draw";
        } else {
            return "Game not finished";
        }
    }

    public static boolean endGame(char[][] grid) {
        return !(winChecker(grid, 'X') || winChecker(grid, 'O') || countX(grid) + countO(grid) == 9);
    }
}

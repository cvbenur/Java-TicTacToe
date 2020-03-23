package com.company;

import java.util.*;

public class TicTacToe {

    // Attributes
    private static int[][] grid = new int[3][3];    // Grid
    private static int player;                      // Player number
    private static String cir = "(_)";              // Symbol for player 1
    private static String tri = "/_\\";             // Symbol for player 2
    private static String nth = "   ";              // Empty square




    // Methods

    // Displaying the grid
    private static void printGrid(int[][] grid) {

        System.out.println();

        for (int[] i : grid) {

            System.out.println("+-----+-----+-----+");

            System.out.printf("|");
            for (int j : i) {

                if (j == 1)
                    System.out.printf(" %s |", cir);
                else if (j == 2)
                    System.out.printf(" %s |", tri);
                else
                    System.out.printf(" %s |", nth);
            }
            System.out.printf("\n");
        }

        System.out.println("+-----+-----+-----+\n");
    }

    // Initializing the grid for the start of the game (filling it with 0s)
    private static void initGrid(int[][] grid) {
        for (int[] i  : grid) {

            for (int j : i)
                i[j] = 0;
        }
    }

    // Displaying the player whose turn it is to play
    private static void printTurnInfo(int player) {
        if (player == 1)
            System.out.println(cir + " plays.");
        else
            System.out.println(tri + " plays.");
    }

    // Reading the x coordinate from the player
    private static int readX(Scanner kybd) {
        int choice=0;
        boolean valid=false;


        do {
            System.out.printf("Type in the x coordinate (1-3) : ");
            choice = kybd.nextInt();
            kybd.nextLine();

            if (choice < 1 || choice > 3)
                System.out.printf("Value invalid. Please try again.\n");
            else
                valid = true;

        } while (!valid);


        return choice-1;
    }

    // Reading the y coordinate from the player
    private static int readY(Scanner kybd) {
        int choice=0;
        boolean valid=false;


        do {
            System.out.printf("Type in the y coordinate (1-3) : ");
            choice = kybd.nextInt();
            kybd.nextLine();

            if (choice < 1 || choice > 3)
                System.out.printf("Value invalid. Please try again.\n");
            else
                valid = true;

        } while (!valid);


        return choice-1;
    }

    // Updating the grid with the coordinates input by the player
    private static boolean updateGrid(int x, int y, int player) {
        if (grid[x][y] == 0) {
            grid[x][y] = player;

            return true;
        }

        return false;
    }

    // Updating the player number
    private static int updatePlayer(boolean endOfGame, int player) {
        if (!endOfGame) {
            if (player == 1)
                return 2;

            return 1;
        }

        return player;
    }

    // Detecting whether the game-end conditions are verified
    private static boolean detectEndOfGame(int player) {

        if (grid[1][1] == player) {
            if (grid[0][0] == player && grid[2][2] == player)
                return true;

            if (grid[0][1] == player && grid[2][1] == player)
                return true;

            if (grid[0][2] == player && grid[2][0] == player)
                return true;

            if (grid[1][0] == player && grid[1][2] == player)
                return true;
        }

        if (grid[0][0] == player) {
            if (grid[0][1] == player && grid[0][2] == player)
                return true;

            if (grid[1][0] == player && grid[2][0] == player)
                return true;
        }

        if (grid[2][2] == player) {
            if (grid[0][2] == player && grid[1][2] == player)
                return true;

            return grid[2][0] == player && grid[2][1] == player;
        }

        return false;
    }

    // Printing the winner's symbol
    private static void printWinner(int player) {
        if (player == 1)
            System.out.printf(cir + " wins !");
        else
            System.out.printf(tri + " wins !");
    }




    // Main method
    public static void main(String[] args) {

        Scanner kybd = new Scanner(System.in);
        boolean endOfGame;


        initGrid(grid);

        System.out.println("Start of game.\nCircle goes first.");

        printGrid(grid);


        player = 1;
        do {

            printTurnInfo(player);


            while (!updateGrid(readX(kybd), readY(kybd), player))
                System.out.printf("Coordinates invalid. Please try again.\n");


            printGrid(grid);


            endOfGame = detectEndOfGame(player);


            player = updatePlayer(endOfGame, player);

        } while (!endOfGame);


        printWinner(player);


        System.exit(0);
    }
}

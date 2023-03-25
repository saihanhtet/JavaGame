package battleship;

import java.util.*;

public class Main {
    private static final int NUM_ROWS = 10;
    private static final int NUM_COLS = 10;
    private static int playerShips;
    private static int computerShips;
    private static int playerPoint = 0;
    private static int computerPoint = 0;
    private static int PlayerWin;
    private static int ComputerWin;
    private static  int matches = 0;
    private static final String[][] GAME_BOARD = new String[NUM_ROWS][NUM_COLS];
    private static final List<String> missed_coordinates = new ArrayList<>();
    private static final Scanner player_input = new Scanner(System.in);

    // main method
    public static void main(String[] args) {
        System.out.println("*****************\tWelcome from the BattleShip Game\t*****************");
        System.out.println("The Board is Empty right now.");

        String response;
        do {
            Main.matches++;
            Main.playerShips = 5;
            Main.computerShips = 5;
            // remove all items from the array
            for (String[] strings : GAME_BOARD) {
                Arrays.fill(strings, null);
            }

            // Step 1 - show board is used to show the board on console
            showBoard();

            // step 2 - Deploy the player's ships
            int[][] playerShipsPlaces = deployPlayerShips(playerShips);
            showBoard(); // re-show the map that player ships take place

            // step 3 - Deploy the player's ships
            deployComputerShips(computerShips, playerShipsPlaces);
            showBoard(); // re-show the map that player ships and computer ship take place

            // step 4 - Battle
            System.out.println("Start fighting...");
            do {
                battle();
                System.out.println();

            } while (playerShips != 0 && computerShips != 0);

            // step 5 - showResult
            showResult();
            System.out.println();

            // step 6 - Re-play? ask if player wants to play again
            System.out.println("Do you want to play again? (Y/N)");
            response = player_input.nextLine().toLowerCase();

        } while (response.equalsIgnoreCase("y"));

    }

    private static void showResult() {
        if (playerPoint > computerShips){
            System.out.println("You Win!!!");
            Main.PlayerWin++;
        } else{
            System.out.println("You Loss!!!");
            Main.ComputerWin++;
        }
        System.out.println("Player Win Count: " + PlayerWin+ " | Player Loss Count: "+(matches-PlayerWin));
        System.out.println("Player's Point: " + playerPoint);
        System.out.println("Player's Ships Left: " + playerShips);
        System.out.println();
        System.out.println("Computer Win Count: " + ComputerWin+ " | Computer Loss Count: "+(matches-ComputerWin));
        System.out.println("Computer's Point: " + computerPoint);
        System.out.println("Computer's Ships Left: " + computerShips);
        System.out.println();
        System.out.println("Missed Coordinates: " + missed_coordinates);
    }

    private static void battle() {
        String msg = playerTurn();
        System.out.println(msg);
        showBoard();
        String c_msg = computerTurn();
        System.out.println(c_msg);
        showBoard();
    }

    private static String computerTurn() {
        System.out.println("COMPUTER TURN !!!");
        Random rand = new Random();
        int x = rand.nextInt(10);
        int y = rand.nextInt(10);
        String coordinate = GAME_BOARD[x][y];
        if (Objects.equals(coordinate, "@")){
            Main.playerShips--;
            computerPoint++;
            GAME_BOARD[x][y] = "X";
            return "Computer : hit 1 point earned!";
        } else if (Objects.equals(coordinate, "C")) {
            Main.computerShips--;
            GAME_BOARD[x][y] = "!";
            return "Computer : hit own ship decrease 1 ship!";
        } else{
            GAME_BOARD[x][y] = "-";
            missed_coordinates.add(Arrays.toString(new int[]{x, y}));
            return "Computer: didn't hit";
        }
    }

    private static String playerTurn() {
        System.out.println("PLAYER TURN !!!");
        // Prompt the input box to shoot the enemy ship and validate input
        String targetLocation;
        do {
            System.out.print("Enter the (target) location with comma : "); // eg. 1,2
            targetLocation = player_input.nextLine().trim();
        } while (targetLocation.isEmpty()
                || !targetLocation.matches("\\d+\\s*,\\s*\\d+"));

        String[] values = targetLocation.split(",");

        int x = Integer.parseInt(values[0]);
        int y = Integer.parseInt(values[1]);
        String coordinate = GAME_BOARD[x][y];
        // attack check
        if (Objects.equals(coordinate, "C")){
            Main.computerShips--;
            playerPoint++;
            GAME_BOARD[x][y] = "!";
            return "Player: hit 1 point earned!";
        } else if (Objects.equals(coordinate, "@")) {
            Main.playerShips--;
            GAME_BOARD[x][y] = "X";
            return "Player: hit own ship decrease 1 ship!";
        } else{
            GAME_BOARD[x][y] = "-";
            missed_coordinates.add(Arrays.toString(new int[]{x, y}));
            return "Player: didn't hit";
        }
    }

    private static void deployComputerShips(int computerShips, int[][] playerShipsPlaces) {
        System.out.println("Deploying computer ships");

        // Get the list of player ship coordinates
        List<Integer> excludedShips = new ArrayList<>();
        for (int[] playerShipPlace : playerShipsPlaces) {
            int x = playerShipPlace[0];
            int y = playerShipPlace[1];
            int algorithm = x * 10 + y;
            excludedShips.add(algorithm);
        }
        System.out.println(excludedShips);
        // Generate computer ships that don't overlap with player ship coordinates
        Random rand = new Random();
        int[][] shipCoordinates = new int[computerShips][2];
        for (int ship=0; ship<computerShips; ship++){
            int x, y, algorithm;
            do {
                x = rand.nextInt(10);
                y = rand.nextInt(10);
                algorithm = x * 10 + y;
            } while (excludedShips.contains(algorithm));

            shipCoordinates[ship] = new int[]{x,y};
            GAME_BOARD[x][y] = "C"; // mark the computer ship on the game board
        }
    }

    private static int[][] deployPlayerShips(int playerShips) {
        System.out.println("Enter the coordinate that your ship take place eg.(row,col)");

        int[][] shipCoordinates = new int[playerShips][2];
        for (int deployShip = 0; deployShip < playerShips; deployShip++){

            // Prompt the player for ship coordinates and validate input
            String coordinate;
            do {
                System.out.printf("Enter the location to deploy ship number %s : ",deployShip+1);
                coordinate = player_input.nextLine().trim();
            } while (coordinate.isEmpty() || !coordinate.matches("\\d+\\s*,\\s*\\d+"));

            String[] values = coordinate.split(",");
            int x = Integer.parseInt(values[0]);
            int y = Integer.parseInt(values[1]);

            // Check if the ship coordinates are valid and do not overlap with previous ships
            boolean valid = checkShipPlace(values, shipCoordinates);
            if (!valid) {
                deployShip--; // Re-deploy the previous ship
                continue;
            }
            int[] coordinates = { x, y };
            shipCoordinates[deployShip] = coordinates;
            GAME_BOARD[coordinates[0]][coordinates[1]] = "@";
            System.out.printf("Your ship number %s is placed at %s %n",deployShip+1, Arrays.toString(coordinates));

        }
        return shipCoordinates;
    }

    private static boolean checkShipPlace(String[] values,int[][] placedShips) {
        int x = Integer.parseInt(values[0]);
        int y = Integer.parseInt(values[1]);
        boolean status;
        if ((x < 0 || x > NUM_ROWS) && (y < 0 || y > NUM_COLS)){
            System.out.println("Please enter the value between 0-9");
            status = false;
        } else if ((x>=0 && x<NUM_ROWS) && (y>=0 && y<NUM_COLS) && (GAME_BOARD[x][y]==null)){
            status = true;
        } else if ((x>=0 && x<NUM_ROWS) && (y>= 0 && y<NUM_COLS) && (Objects.equals(GAME_BOARD[x][y], "@"))) {
            int[] intValue = {x, y};
            int index = getIndexForExactRow(placedShips,intValue);
            System.out.printf("You can't enter this location which of your ship No. %s placed. %n",index+1);
            status = false;
        } else{
            System.out.println("Invalid ship location. Please try again!!! only 0 to 9 available!");
            status = false;
        }
        System.out.println();
        return status;
    }

    private static int getIndexForExactRow(int[][] array, int[] searchRow) {
        for (int i = 0; i < array.length; i++) {
            if (Arrays.equals(array[i], searchRow)) {
                return i;
            }
        }
        return -1; // return -1 if the row is not found
    }

    private static void showBoard() {
        System.out.print("   ");
        for (int row=0; row<NUM_ROWS; row++) System.out.print(" "+row); // show x number row
        System.out.println();

        // create map
        for (int row=0; row<NUM_ROWS; row++){
            // place the y number column
            System.out.printf("%2d| ", row);
            for (int col=0; col<NUM_COLS; col++){
                if (col == NUM_COLS -1) {
                    // place the y number after the board last item
                    String msg = (GAME_BOARD[row][col]==null)? " ": GAME_BOARD[row][col];
                    System.out.print(msg + " |" + row);
                } else if (GAME_BOARD[row][col   ] == null) {
                    // if there is no item in board this position show space in ocean board
                    System.out.print("  ");
                } else{
                    // if there is player or computer ship exist show on board
                    System.out.print(GAME_BOARD[row][col] + " ");
                }
            }
            System.out.println();
        }
        System.out.print("   ");
        for (int row=0; row<NUM_ROWS; row++) System.out.print(" "+row); // show the last x number row
        System.out.println();
    }
}

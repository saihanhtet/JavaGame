package battleship;

import java.util.*;

public class BattleShip {
    // global variable
    public static int numRows = 10,numCols = 10;
    public static int playerShips,computerShips;
    public static String[][] board = new String[numRows][numCols];
    public static List<String> missed = new ArrayList<>();

    public static Scanner input = new Scanner(System.in);
    private static int playerPoint;
    private static int computerPoint;

    public static void main(String[] args) {
        System.out.println("*****************\tWelcome\t*****************");
        System.out.println("Right now see is empty");

        BattleShip.playerShips = 5;
        BattleShip.computerShips = 5;

        // step 1 - create the map
        createMap();

        // step 2 - deploy player's ship
        int[][] playerShipPlaces = deployPlayerShip(BattleShip.playerShips);
        createMap(); // re-show the map that player ships take place

        // step 3 - deploy computer's ship
        deployComputerShip(BattleShip.computerShips, playerShipPlaces);
        createMap(); // re-show the map that player ships and computer ship take place

        // step 4 - Battle
        do {
            Battle();
        } while (BattleShip.playerShips!=0 && BattleShip.computerShips!=0);

        GameOver();
    }

    public static String PlayerTurn(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the coordinate with comma : ");
        String targetLocation = input.nextLine();
        String[] values = targetLocation.split(",");
        int x = Integer.parseInt(values[0]);
        int y = Integer.parseInt(values[1]);
        String coordinate = board[x][y];
        if (Objects.equals(coordinate, "C")){
            BattleShip.computerShips--;
            playerPoint++;
            board[x][y] = "!";
            return "Player: hit 1 point earned!";
        } else if (Objects.equals(coordinate, "@")) {
            BattleShip.playerShips--;
            board[x][y] = "X";
            return "Player: hit own ship decrease 1 ship!";
        } else{
            board[x][y] = "-";
            missed.add(Arrays.toString(new int[]{x, y}));
            return "Player: didn't hit";
        }
    }
    public static String ComputerTurn(){
        Random rand = new Random();
        int x = rand.nextInt(10);
        int y = rand.nextInt(10);
        String coordinate = board[x][y];
        if (Objects.equals(coordinate, "@")){
            BattleShip.playerShips--;
            computerPoint++;
            board[x][y] = "X";
            return "Computer : hit 1 point earned!";
        } else if (Objects.equals(coordinate, "C")) {
            BattleShip.computerShips--;
            board[x][y] = "!";
            return "Computer : hit own ship decrease 1 ship!";
        } else{
            board[x][y] = "-";
            missed.add(Arrays.toString(new int[]{x, y}));
            return "Computer: didn't hit";
        }
    }

    private static void GameOver() {
        System.out.println("Player's Point : "+playerPoint);
        System.out.println("Player's Ship Left : "+playerShips);
        System.out.println();
        System.out.println("Computer's Point : "+computerPoint);
        System.out.println("Computer's Ship Left : "+computerShips);
        System.out.println();
        System.out.println("Missed Coordinate : "+missed);
    }

    private static void Battle() {
        String msg = PlayerTurn();
        System.out.println(msg);
        createMap();
        String c_msg = ComputerTurn();
        System.out.println(c_msg);
        createMap();

    }

    private static void deployComputerShip(int shipCount,int[][] playerShipPlaces) {
        // we want to exclude the player ship place in list to generate the computer ship
        System.out.println("Deploying computer ships");
        ArrayList<Integer> excludedShips = new ArrayList<Integer>();
        for (int[] playerShipPlace: playerShipPlaces){
            // making algorithm to check if the player ship place unique
            int x = playerShipPlace[0];
            int y = playerShipPlace[1];
            int algorithm = x * 10 + y; // 2 * 10 + 3 = 23
            excludedShips.add(algorithm);
        }
        // generate the computer ship which does not include the player ship coordinates
        Random rand = new Random();
        int[][] shipCoordinates = new int[shipCount][2];
        for (int ship=0; ship<shipCount; ship++){
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            // if computer place and player ship equal re-generate
            int algorithm = x * 10 + y;
            while (excludedShips.contains(algorithm)){
                x = rand.nextInt(10);
                y = rand.nextInt(10);
                algorithm = x * 10 + y;
            }
            int[] coordinates = new int[2];
            coordinates[0] = x;
            coordinates[1] = y;
            // add the coordinate to the all ship list
            shipCoordinates[ship] = coordinates;
            // add the player ship symbol to the board
            board[coordinates[0]][coordinates[1]] = "C";
        }
    }

    private static int[][] deployPlayerShip(int shipCount) {
        System.out.println("Enter coordinate eg. row,col : ");
        int[][] shipCoordinates = new int[shipCount][2];
        for (int ship=0; ship < shipCount; ship++){
            System.out.printf("Enter the (row,col) location %n of place u want to place ur No.%s ship : ", ship+1);
            String coordinate = input.nextLine();
            String[] values = coordinate.split(",");
            boolean status = CheckShipPlaces(values);
            if (!status){
                // decrease the ship and re-loop the looping if the status is false
                ship--;
            }
            else{
                int[] coordinates = new int[2];
                coordinates[0] = Integer.parseInt(values[0]);
                coordinates[1] = Integer.parseInt(values[1]);
                // add the coordinate to the all ship list
                shipCoordinates[ship] = coordinates;
                // add the player ship symbol to the board
                board[coordinates[0]][coordinates[1]] = "@";
                System.out.printf("Your ship number %s is placed at %s %n",ship+1, Arrays.toString(coordinates));
            }
        }
        return shipCoordinates;
    }

    private static boolean CheckShipPlaces(String[] values) {
        int x = Integer.parseInt(values[0]);
        int y = Integer.parseInt(values[1]);

        if ((x<=0 || x>= numRows) && (y <= 0 || y>=numCols)) {
            System.out.println("You have to enter only between 0 to 9 include.");
            return false;
        } else if ((x>=0 && x<=numRows) && (y>=0 && y<=numCols) && (board[x][y]==null)){
            return true;
        } else if ((x>=0 && x<=numRows) && (y>= 0 && y<=numCols) && (Objects.equals(board[x][y], "@"))) {
            System.out.println("You can't enter this location which of your ship placed.");
            return false;
        } else{
            return false;
        }
    }

    public static void createMap(){
        // show x number row
        System.out.print("   ");
        for (int row=0; row<numRows; row++) System.out.print(" "+row);
        System.out.println();
        // create map
        for (int row=0; row<numRows; row++){
            // place the y number column
            System.out.printf("%2d| ", row);
            for (int col=0; col<numCols; col++){
                if (col == numCols -1) {
                    // place the y number after the board last item
                    String msg = (board[row][col]==null)? " ": board[row][col];
                    System.out.print(msg + " |" + row);
                } else if (board[row][col   ] == null) {
                    // if there is no item in board this position show space in ocean board
                    System.out.print("  ");
                } else{
                    // if there is player or computer ship exist show on board
                    System.out.print(board[row][col] + " ");
                }
            }
            System.out.println();
        }
        // show the last x number row
        System.out.print("   ");
        for (int row=0; row<numRows; row++) System.out.print(" "+row);
        System.out.println();
        System.out.println("Created map");
    }
}

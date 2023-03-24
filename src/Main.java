package battleship;

import java.util.*;

public class Main {
    public static int numRows = 10;
    public static int numColumns = 10;
    public static int playerShips;
    public static int computerShips;
    public static int playerPoint = 0;
    public static int computerPoint = 0;
    public static String[][] board = new String[numRows][numColumns];
    public static List<String> missed = new ArrayList<>();


    public static int[][] DeployPlayerShip(int ShipsCount){
        System.out.println("Enter the coordinate eg.(row,col) = 2,4 by comma ");
        int[][] shipCoordinates = new int[ShipsCount][2];
        Scanner input = new Scanner(System.in);
        for (int ship=0;ship<Main.playerShips;ship++){
            System.out.printf("Enter the (x,y) location of ur ship No.%s: ",ship+1);
            String coordinate = input.nextLine();
            String[] values = coordinate.split(",");
            int x = Integer.parseInt(values[0]);
            int y = Integer.parseInt(values[1]);
            int[] coordinates = new int[2];
            coordinates[0] = x;
            coordinates[1] = y;
            shipCoordinates[ship] = coordinates;
            System.out.println(Arrays.toString(coordinates));
        }
        // shipCoordinates = new int[][]{{0, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}};
        System.out.println(Arrays.deepToString(shipCoordinates));
        System.out.println("Start deploying the ships from the coordinates you provided!");
        for (int[] ship : shipCoordinates){
            board[ship[0]][ship[1]] = "@";
        }
        return shipCoordinates;
    }
    public static void DeployComputerShip(int ShipsCount,int[][] PlayerShipPlaces){
        System.out.println("Deploying the computer ships");
        ArrayList<Integer> excludedValues = new ArrayList<Integer>();
        for (int[] playerShipPlace : PlayerShipPlaces) {
            int x = playerShipPlace[0];
            int y = playerShipPlace[1];
            int value = x * 10 + y; // combine x and y into a single value
            excludedValues.add(value);
        }

        Random rand = new Random();
        int[][] shipCoordinates = new int[ShipsCount][2];
        for (int ship=0; ship<Main.computerShips; ship++){
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            int value = x * 10 + y; // to check whether computer random place in player ship place
            while (excludedValues.contains(value)) {
                x = rand.nextInt(10);
                y = rand.nextInt(10);
                value = x * 10 + y;
            }
            int[] coordinates = new int[2];
            coordinates[0] = x;
            coordinates[1] = y;
            shipCoordinates[ship] = coordinates;
        }
        for (int[] ship : shipCoordinates){
            board[ship[0]][ship[1]] = "C";
        }
    }

    public static String createMap(){
        System.out.println("Creating map.");
        System.out.println();
        System.out.print("   ");
        for (int rowId=0; rowId<numRows;rowId++){
            System.out.print(" "+rowId);
        }
        System.out.println();
        for (int row=0;row<numRows;row++){
            System.out.printf("%2d| ", row);
            for (int col = 0; col < numColumns; col++) {
                if (col == numColumns -1){
                    String msg = (board[row][col]==null)? " ": board[row][col];
                    System.out.print(msg+" |"+row);
                }else if (board[row][col] == null) {
                    System.out.print("  "); // two spaces
                }else{
                    System.out.print(board[row][col] + " ");
                }
            }
            System.out.println();
        }
        System.out.print("   ");
        for (int rowId=0; rowId<numRows;rowId++){
            System.out.print(" "+rowId);
        }
        System.out.println();
        System.out.println();
        return "Created map";
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
            computerShips--;
            playerPoint++;
            board[x][y] = "!";
            return "Player: hit 1 point earned!";
        } else if (Objects.equals(coordinate, "@")) {
            playerShips--;
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
            playerShips--;
            computerPoint++;
            board[x][y] = "X";
            return "Computer : hit 1 point earned!";
        } else if (Objects.equals(coordinate, "C")) {
            computerShips--;
            board[x][y] = "!";
            return "Computer : hit own ship decrease 1 ship!";
        } else{
            board[x][y] = "-";
            missed.add(Arrays.toString(new int[]{x, y}));
            return "Computer: didn't hit";
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Battleship game.");
        System.out.println("----Map is empty----");
        Scanner input = new Scanner(System.in);
        System.out.print("Created map? y/n: ");
        String check = input.next();
        String message = (check.equals("y")||check.equals("yes")) ? createMap() : "Exit";
        System.out.println(message);

        Main.playerShips = Main.computerShips = 5;

        int[][] playerShipOnBoard = DeployPlayerShip(Main.playerShips);
        createMap();
        DeployComputerShip(Main.computerShips,playerShipOnBoard);
        createMap();

        System.out.println("Start fighting....");
        boolean flag = true;

        do {
            String msg = PlayerTurn();
            System.out.println(msg);
            createMap();
            String comp = ComputerTurn();
            System.out.println(comp);
            createMap();

            if (playerShips==0 || computerShips==0){
                flag=false;
            }

        } while (flag);

        if (playerShips>computerShips){
            System.out.println("You Win!!!");
        } else{
            System.out.println("You Lose!!!");
        }
        System.out.println("Player's Point : "+playerPoint);
        System.out.println("Player's Ship Left : "+playerShips);
        System.out.println();
        System.out.println("Computer's Point : "+computerPoint);
        System.out.println("Computer's Ship Left : "+computerShips);
        System.out.println();
        System.out.println("Missed Coordinate : "+missed);
    }
}








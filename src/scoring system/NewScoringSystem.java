package assessment2;

import java.util.*;

public class NewScoringSystem {
    // declare the class level input
    private static final Scanner scan = new Scanner(System.in);
    // declare the drawList and draw status
    private static List<String> drawList = new ArrayList<String>();
    private static Boolean isDraw = false;

    public static void welcomeMsg() {
        System.out.println("\n***** College Tournament Scoring System ******");
        System.out.println(
                "\nTournament Scoring System Rules:" +
                        "\n\nNormal Team Scoring Rules:\n(a) For Normal Teams and Individuals Participants:\n" +
                        "Rank 1 gives 20 points "
        );
        System.out.println("Rank 2 gives 10 points \nRank 3 gives 5 points");
        System.out.println("Rank 4 and lower will not receive any points\n");
        System.out.println("(b) For Special Teams and Individuals");
        System.out.println(
                "Rank 1 gives 100 points \nRank 2 Gives 80 points \nRank 3 Gives 60 points"
        );
        System.out.println("Rank 4 or lower will not give any points");
        System.out.println("\nGeneral Rules:");
        System.out.println("***************");
        System.out.println("=> 5 Events are set for Normal Teams and Individuals");
        System.out.println(
                "=> Only 1 event is allowed for Special Teams and Individuals "
        );
        System.out.println(
                "=> There can only be 5 Participants in both Normal and Special team"
        );
        System.out.println(
                "=> Normal Teams and Individual Participants will participate in 5 Events"
        );
        System.out.println(
                "=> Special Teams and Individual Participants will participate in only 1 Event\n"
        );
    }

    // get the user input with type class like integer string and float
    public static <Type> Type getInput(Class<Type> type, String prompt, String error) {
        boolean isInputValid = false;
        Type response = null;
        while (!isInputValid) {
            System.out.print(prompt);
            //scan.nextLine(); // consume any leftover input in the buffer
            if (scan.hasNextLine()) {
                String input = scan.nextLine();
                try {
                    if (type == Integer.class) {
                        response = type.cast(Integer.parseInt(input));
                    } else if (type == Double.class) {
                        response = type.cast(Double.parseDouble(input));
                    } else {
                        response = type.cast(input);
                    }
                    isInputValid = true;
                } catch (NumberFormatException e) {
                    System.out.println(error);
                }
            }
        }
        return response;
    }

    public static String getNonEmptyInput(String prompt) {
        String input = "";
        boolean promptPrinted = false;
        do {
            if (!promptPrinted) {
                System.out.print(prompt);
                promptPrinted = true;
            }
            input = scan.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    public static void main(String[] args) {
        welcomeMsg();
        char choice;
        do{
            // display menu option to user
            System.out.println(
                    "Please Enter the following: \n 1 for Normal Teams \n 2 for Normal Individuals \n 3 for Special Teams \n 4 for Special Individuals \n");
            // get the user input and used getInput() method to check whether the input is integer or not
            int option = getInput(Integer.class,"Enter the number from above: ","Please enter Integer only");
            switch (option) {
                case 1 -> {
                    System.out.println("-----Teams of College------");
                    System.out.println("Enter the no of Teams Entering 5 EVENTS");
                    // call the match function
                    match(false, true);
                }
                case 2 -> {
                    System.out.println("-----College Individual Participants-----");
                    System.out.println(
                            "Enter the number of individuals participating 5 EVENTS LIMITED SPACE OF 20"
                    );
                    // call the match function
                    match(false, false);
                }
                case 3 -> {
                    System.out.println(
                            "Special Teams and Individuals Represent Teams and Individuals entering only 1 event"
                    );
                    System.out.println("-----Special_Teams-----");
                    System.out.println("Enter number of Teams Entering only 1 EVENT");
                    // call the match function
                    match(true, true);
                }
                case 4 -> {
                    System.out.println("-----Special_Individuals-----");
                    System.out.println(
                            "Enter Number of Individuals only Entering 1 event"
                    );
                    // call the match function
                    match(true, false);
                }
                default -> System.out.println("Please enter 1 or 2 or 3 or 4 only");
            }
            
            // ask the user to want to play again or not
            System.out.println("Do you wish to continue (y / n)");
            choice = scan.next().toLowerCase().charAt(0);
        } while (choice == 'y');
    }

    public static void match(Boolean Special, Boolean Team){
        // get the number from user
        int Number = getInput(Integer.class,"> ","Please enter Integer only !!!");
        // check whether the match is special or normal
        if (!Team){
            // code for individual
            individualMatch(Special,Number);
        } else{
            // code for team
            teamMatch(Special, Number);
        }
    }

    public static void teamMatch(Boolean Special, int noTeam){
        // declare the team variables
        String[] name = new String[noTeam];
        int[] score = new int[noTeam];
        String[] participant = new String[noTeam * 5];
        String[] event;
        int[] points = new int[4];
        Arrays.fill(points, 0);

        // check whether the team match is normal or special for rules
        if (!Special) {
            // normal team rules
            event = new String[5];
            points[0] = 20;
            points[1] = 10;
            points[2] = 5;
        } else {
            // special team rules
            event = new String[1];
            points[0] = 100;
            points[1] = 80;
            points[2] = 60;
        }

        // get event names
        for (int i = 0; i < event.length; i++) {
            String msg = "Enter Event Name " + (i + 1) + " for the teams : ";
            event[i] = getNonEmptyInput(msg);
        }
        // assign the participants into team
        for (int i = 0; i < noTeam; i++) {
            // for participant names for the teams
            for (int a = 0; a < 5; a++) {
                int index = i * 5 + a;
                String msg = "Enter Participant name " + (a + 1) + " for team " + (i + 1)+" : ";
                participant[index] = getNonEmptyInput(msg);
            }
        }

        // get the name of team and rank
        for (int i = 0; i < noTeam; i++) {
            String msg = "Enter the Name of team " + (i + 1)+ " : ";
            name[i] = getNonEmptyInput(msg);
            for (int a = 0; a < event.length; a++) {
                System.out.println("Enter rank of the team: " + name[i] + " on the event " + (a + 1) + ": " + event[a]);
                int tRank = getInput(Integer.class,"> ","Please enter the integer only.");
                int rank = 0;
                // for scoring system for the teams
                switch (tRank) {
                    case 3 -> rank = points[2];
                    case 2 -> rank = points[1];
                    case 1 -> rank = points[0];
                    default -> {
                        rank = points[3];
                        System.out.println("This team will not be scored any points");
                    }
                }
                // assign the score
                score[i] += rank;
                System.out.println(rank + " points scored for this event");

            }
        };
        String TeamStyle = (Special) ? "Special" : "Normal";
        drawList.clear();
        printResultBoard(
                TeamStyle,
                true,
                name,
                score,
                event,
                participant,
                noTeam
        );
        rematch(Special, true);

    }
    public static void individualMatch(Boolean Special, int noPart){
        String[] name = new String[noPart];
        int[] score = new int[noPart];
        String[] event;
        int[] points = new int[4];
        Arrays.fill(points, 0);
        // check whether the individual match is normal or special for rules
        if (!Special){
            // normal individual rules
            event = new String[5]; // assign event as 5
            points[0] = 20;
            points[1] = 10;
            points[2] = 5;
        } else{
            // special individual rules
            event = new String[1]; // assign event as 1
            points[0] = 100;
            points[1] = 80;
            points[2] = 60;
        }
        // get the events of individuals are entering
        for (int i = 0; i < event.length; i++) {
            String msg = "Enter Name of the event " +(i + 1) + " that the individuals are entering : ";
            event[i] = getNonEmptyInput(msg);
        }
        // get the name of participants and ranks
        for (int i = 0; i < noPart  ; i++) {
            String msg = "Enter name of Individual " + (i + 1) + " : ";
            name[i] = getNonEmptyInput(msg);

            for (int a = 0; a < event.length; a++) {
                System.out.println(
                        "Enter rank of the individual: " +
                                name[i] +
                                " on the event" +
                                (a + 1) +
                                ": " +
                                event[a]
                );
                int PartRank = getInput(Integer.class,"> ","Please enter the integer only.");
                int pRank = 0;
                // for scoring system for the teams
                switch (PartRank) {
                    case 3 -> pRank = points[2];
                    case 2 -> pRank = points[1];
                    case 1 -> pRank = points[0];
                    default -> {
                        pRank = points[3];
                        System.out.println("This team will not be scored any points");
                    }
                }
                score[i] += pRank;
                System.out.println(pRank + " points scored for this event");
            }
        }
        String TeamStyle = (Special) ? "Special" : "Normal";
        drawList.clear();
        printResultBoard(
                TeamStyle,
                false,
                name,
                score,
                event,
                null,
                noPart
        );
        rematch(Special, false);
    }

    public static void printResultBoard(
            String matchType,
            boolean isTeamMatch,
            String[] teamNames,
            int[] teamScores,
            String[] events,
            String[] teamParticipants,
            int numParticipants
    ) {
        String separator = "-".repeat(80);

        if (isTeamMatch) {
            System.out.printf(
                    "-- %s Team Information:-- %n",
                    (matchType.substring(0, 1).toUpperCase() + matchType.substring(1))
            );
            System.out.println("Number of Teams Registered    : " + numParticipants);
            System.out.printf("Number of Events Participated : %s %n", events.length);
            System.out.println("Events List for Teams : " + Arrays.asList(events));
            System.out.println("Score Points Won:");
            System.out.println("-----------------");
            System.out.println();
            System.out.printf(
                    "All %s Teams Scores : %s%n",
                    matchType,
                    Arrays.toString(teamScores)
            );
            System.out.println(separator);

            System.out.printf(
                    "\t %s Team\t\t      Participants\t\t       Score %n",
                    matchType
            );
            System.out.println(separator);

            int index = 0;
            int winnerTeamScore = teamScores[0];
            List<String> winnerTeams = new ArrayList<String>();

            for (int i = 0; i < numParticipants; i++) {
                if (teamScores[i] > winnerTeamScore) {
                    winnerTeamScore = teamScores[i];
                }

                for (int x = 0; x < 5; x++) {
                    System.out.println(
                            "\t Team: " +
                                    teamNames[i] +
                                    "\t " +
                                    "Participants: " +
                                    teamParticipants[index] +
                                    "\t Team Score: " +
                                    teamScores[i]
                    );
                    index++;
                }
                System.out.println(separator);
            }
            System.out.println(separator);

            for (int i = 0; i < numParticipants; i++) {
                if (teamScores[i] == winnerTeamScore) {
                    winnerTeams.add(teamNames[i]);
                }
            }
            calculateWinner(winnerTeams, matchType, isTeamMatch, winnerTeamScore);
            System.out.println(separator);
            System.out.println("********************");
        } else {
            int eventNo = (Objects.equals(matchType, "Special")) ? 1 : 5;
            System.out.println("No. of Participants: " + numParticipants);
            System.out.println("Events Participated : "+eventNo);
            System.out.println(
                    "Events List for Individuals : " + Arrays.asList(events)
            );
            System.out.println("Score Points won:");
            System.out.println("All Individual Scores:" + Arrays.toString(teamScores));
            System.out.println(separator);
            System.out.println("\tIndividual Name \t   Score");
            System.out.println(separator);

            int individualWinnerScore = teamScores[0];
            List<String> individualWinners = new ArrayList<String>();

            for (int i = 0; i < numParticipants; i++) {
                if (teamScores[i] > individualWinnerScore) {
                    individualWinnerScore = teamScores[i];
                }
                System.out.println(
                        " \tIndividual Name: " + teamNames[i] + "\t Score: " + teamScores[i]
                );
            }
            System.out.println(separator);
            for (int i = 0; i < numParticipants; i++) {
                if (teamScores[i] == individualWinnerScore) {
                    individualWinners.add(teamNames[i]);
                }
            }
            calculateWinner(individualWinners, matchType, false, individualWinnerScore);
            System.out.println(separator);
            System.out.println("********************");
        }
    }

    public static void rematch(boolean Special, boolean team){
        if (isDraw) {
            System.out.println("\nDraw!");
            System.out.println("The following participants have drawn:"+ drawList);

            System.out.println("\nDo you want to have a rematch? (y/n)");
            char choice = scan.next().toLowerCase().charAt(0);
            if (choice == 'y') {
                System.out.println("\nRematch!");
                // call the individual match method again with the same arguments
                if (!team){
                    System.out.println("Rematch for "+ drawList.size()+" participants!!!");
                    individualMatch(Special, drawList.size());
                } else{
                    System.out.println("Rematch for "+ drawList.size()+" team!!!");
                    teamMatch(Special, drawList.size());
                }
            }
        } else {
            // clear the draw list
            drawList.clear();
        }
    }

    public static void calculateWinner(
            List<String> winnerTeams,
            String type,
            Boolean team,
            int winner_team_score
    ) {
        if (team) {
            System.out.printf(
                    "Congratulations!! %s Team",
                    (type.substring(0, 1).toUpperCase() + type.substring(1))
            );

        } else {
            System.out.printf(
                    "Congratulations!! %s Individual Participant",
                    (type.substring(0, 1).toUpperCase() + type.substring(1))
            );
        }
        if (winnerTeams.size() == 1) {
            System.out.print(" " + winnerTeams.get(0));
            drawList.clear();
            System.out.print(
                    " have won with a score of " + winner_team_score + "."
            );
            isDraw = false;
        } else {
            for (int i = 0; i < winnerTeams.size(); i++) {
                if (i == winnerTeams.size() - 1) {
                    System.out.print(" and " + winnerTeams.get(i));
                } else if (i == 0) {
                    System.out.print("s " + winnerTeams.get(i));
                } else {
                    System.out.print(", " + winnerTeams.get(i));
                }
            }
            drawList.addAll(winnerTeams);
            isDraw = true;
            System.out.print(
                    " have draw with a score of " + winner_team_score + "."
            );
        }
        System.out.println();
    }
}

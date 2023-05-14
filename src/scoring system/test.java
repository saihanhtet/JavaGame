import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class test {

  private static List<String> drawList = new ArrayList<String>();

  private static final Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    welcomeMsg();
    char choice;
    do {
      String msg = (drawList.size() > 0)
        ? "******** How Do you Like to Re-match " + drawList + " ************ "
        : "******** MENU ************";

      System.out.println(msg);

      System.out.println(
        "Please Enter the following: \n 1 for Normal Teams \n 2 for Normal Individuals"
      );
      System.out.println(" 3 for Special Teams \n 4 for Special Individuals");
      int option = getIntInput("Please enter the menu option from above only.");

      switch (option) {
        case 1:
          System.out.println("-----Teams of College------");
          System.out.println("Enter the no of Teams Entering 5 EVENTS");
          int teamNo = (drawList.size() > 0)
            ? drawList.size()
            : getIntInput("Please enter the integer only.");
          match("team", "normal", teamNo);
          break;
        case 2:
          System.out.println("-----College Individual Participants-----");
          System.out.println(
            "Enter the number of individuals participating 5 EVENTS" +
            " LIMITED SPACE OF 20"
          );
          int PartNo = (drawList.size() > 0)
            ? drawList.size()
            : getIntInput("Please enter the integer only.");
          match("individual", "normal", PartNo);
          break;
        case 3:
          System.out.println(
            "Special Teams and Individuals Represent Teams and Individuals entering only 1 event"
          );

          System.out.println("-----Special_Teams-----");
          System.out.println("Enter number of Teams Entering only 1 EVENT");
          int SpecTeamNo = (drawList.size() > 0)
            ? drawList.size()
            : getIntInput("Please enter the integer only.");
          match("team", "special", SpecTeamNo);
          break;
        case 4:
          System.out.println("-----Special_Individuals-----");
          System.out.println(
            "Enter Number of Individuals only Entering 1 event "
          );
          int SpecPartNo = (drawList.size() > 0)
            ? drawList.size()
            : getIntInput("Please enter the integer only.");
          match("individual", "special", SpecPartNo);
          break;
        default:
          System.out.println(" Please enter 1 or 2 or 3 or 4 only ");
          break;
      }
      if (drawList.size() > 0) {
        System.out.println("Do you wish to rematch (y / n)" + drawList);
        choice = scan.next().toLowerCase().charAt(0);
      } else {
        System.out.println("Do you wish to continue (y / n)");
        choice = scan.next().toLowerCase().charAt(0);
      }
    } while (choice == 'y');
    scan.close();
    System.out.println("Thanks for Participation! See you Again, Thank you!");
    System.out.println(
      "----------------------------------------------------------------------------\n"
    );
  }

  public static void calculateWinner(
    List<String> winnerTeams,
    String type,
    Boolean team,
    int winner_team_score
  ) {
    if ((type == "normal" || type == "special") && (team == true)) {
      System.out.printf(
        "Congratulations!! %s Team",
        (type.substring(0, 1).toUpperCase() + type.substring(1))
      );
      if (winnerTeams.size() == 1) {
        System.out.print(" " + winnerTeams.get(0));
        drawList.clear();
        System.out.print(
          " have won with a score of " + winner_team_score + "."
        );
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
        System.out.print(
          " have draw with a score of " + winner_team_score + "."
        );
      }
      System.out.println();
    } else {
      System.out.printf(
        "Conngratulations!! %s Individual Participant",
        (type.substring(0, 1).toUpperCase() + type.substring(1))
      );
      if (winnerTeams.size() == 1) {
        System.out.print(" " + winnerTeams.get(0));
        drawList.clear();
        System.out.print(" has won with a score of " + winner_team_score + ".");
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
        System.out.println(
          " have draw with a score of " + winner_team_score + "."
        );
      }
      System.out.println();
    }
  }

  public static void resultBoard(
    String matchType,
    Boolean team,
    String[] names,
    int[] scores,
    String[] events,
    String[] teamPart,
    int indexNo
  ) {
    String separator = "-".repeat(80);
    if (team == true) {
      System.out.printf(
        "-- %s Team Information:-- %n",
        (matchType.substring(0, 1).toUpperCase() + matchType.substring(1))
      );
      System.out.println("Number of Teams Registered    : " + indexNo);
      System.out.printf("Number of Events Participated : %s %n", events.length);
      System.out.println("Events List for Teams : " + Arrays.asList(events));
      System.out.println("Score Points Won:");
      System.out.println("-----------------");
      System.out.println();
      System.out.printf(
        "All %s Teams Scores : %s%n",
        matchType,
        Arrays.toString(scores)
      );
      System.out.println(separator);

      System.out.printf(
        "\t %s Team\t\t      Participants\t\t       Score %n",
        matchType
      );
      System.out.println(separator);

      int y = 0;
      int winner_team_score = scores[0];
      List<String> winnerTeams = new ArrayList<String>();

      for (int i = 0; i < indexNo; i++) {
        if (scores[i] > winner_team_score) {
          winner_team_score = scores[i];
        }

        for (int x = 0; x < 5; x++) {
          System.out.println(
            "\t Team: " +
            names[i] +
            "\t " +
            "Participants: " +
            teamPart[y] +
            "\t Team Score: " +
            scores[i]
          );
          y++;
        }
        System.out.println(separator);
      }
      System.out.println(separator);

      for (int i = 0; i < indexNo; i++) {
        if (scores[i] == winner_team_score) {
          winnerTeams.add(names[i]);
        }
      }
      calculateWinner(winnerTeams, matchType, team, winner_team_score);
      System.out.println(separator);
      System.out.println("********************");
    } else {
      System.out.println("No. of Participants: " + indexNo);
      System.out.println("Events Participated : 5");
      System.out.println(
        "Events List for Individuals : " + Arrays.asList(events)
      );
      System.out.println("Score Points won:");
      System.out.println("All Individual Scores:" + Arrays.toString(scores));
      System.out.println(separator);
      System.out.println("\tIndividual Name \t   Score");
      System.out.println(separator);

      int pwinner_score = scores[0];
      List<String> winners = new ArrayList<String>();

      for (int i = 0; i < indexNo; i++) {
        if (scores[i] > pwinner_score) {
          pwinner_score = scores[i];
        }
        System.out.println(
          " \tIndividual Name: " + names[i] + "\t Score: " + scores[i]
        );
      }
      System.out.println(separator);
      for (int i = 0; i < indexNo; i++) {
        if (scores[i] == pwinner_score) {
          winners.add(names[i]);
        }
      }
      calculateWinner(winners, matchType, false, pwinner_score);
      System.out.println(separator);
      System.out.println("********************");
    }
  }

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

  public static int getIntInput(String error) {
    int input = 0;
    boolean isInputValid = false;
    while (!isInputValid) {
      if (scan.hasNextInt()) {
        input = scan.nextInt();
        isInputValid = true;
      } else {
        System.out.println(error);
        scan.next();
      }
    }
    return input;
  }

  public static void match(String matchType, String matchStyle, int indexNo) {
    if (indexNo == 0) {
      System.out.println("Oops!! There are no Event!!");
    } else {
      int[] points = { 0, 0, 0, 0 };

      if (matchType == "team") {
        String[] teamName = new String[indexNo];
        int[] teamScore = new int[indexNo];
        String[] teamPart = new String[indexNo * 5];
        String[] Tevent;
        if (matchStyle == "special") {
          Tevent = new String[1];
          points[0] = 100;
          points[1] = 80;
          points[2] = 60;
          points[3] = 0;
        } else {
          Tevent = new String[5];
          points[0] = 20;
          points[1] = 10;
          points[2] = 5;
          points[3] = 0;
        }

        // get event names
        for (int i = 0; i < Tevent.length; i++) {
          System.out.println("Enter Event Name " + (i + 1) + " for the teams");
          Tevent[i] = scan.next();
        }

        // get the participant infomations
        int z = 0;
        for (int i = 0; i < indexNo; i++) {
          // for participant names for the teams
          for (int a = 0; a < 5; a++) {
            System.out.println(
              "Enter Participant name " + (a + 1) + " for team " + (i + 1)
            );
            teamPart[z] = scan.next();
            z++;
          }
        }
        // for team name and the rank of the teams
        for (int i = 0; i < indexNo; i++) {
          System.out.println("Enter the Name of team " + (i + 1));
          teamName[i] = scan.next();
          for (int a = 0; a < Tevent.length; a++) {
            System.out.println(
              "Enter rank of the team: " +
              teamName[i] +
              " on the event " +
              (a + 1) +
              ": " +
              Tevent[a]
            );
            int teamRank = getIntInput("Please enter the integer only.");
            int tRank = 0;
            // for scoring system for the teams
            switch (teamRank) {
              case 3:
                tRank = points[2];
                break;
              case 2:
                tRank = points[1];
                break;
              case 1:
                tRank = points[0];
                break;
              default:
                tRank = points[3];
                System.out.println("This team will not be scored any points");
                break;
            }
            teamScore[i] += tRank;
            System.out.println(tRank + " points scored for this event");
            if (scan.hasNextLine()) {
              scan.nextLine();
            }
          }
        }
        resultBoard(
          matchStyle,
          true,
          teamName,
          teamScore,
          Tevent,
          teamPart,
          indexNo
        );
      } else {
        String[] PartName = new String[indexNo];
        int[] PartScore = new int[indexNo];
        String[] Pevent;

        if (matchStyle == "special") {
          Pevent = new String[1];
          points[0] = 100;
          points[1] = 80;
          points[2] = 60;
          points[3] = 0;
        } else {
          Pevent = new String[5];
          points[0] = 20;
          points[1] = 10;
          points[2] = 5;
          points[3] = 0;
        }

        for (int i = 0; i < Pevent.length; i++) {
          System.out.println(
            "Enter Name of the event " +
            (i + 1) +
            " that the individuals are entering"
          );
          Pevent[i] = scan.next();
        }
        // name and rank of the individuals
        for (int i = 0; i < indexNo; i++) {
          System.out.println("Enter name of Individual " + (i + 1));
          PartName[i] = scan.next();

          for (int a = 0; a < Pevent.length; a++) {
            System.out.println(
              "Enter rank of the individual: " +
              PartName[i] +
              " on the event" +
              (a + 1) +
              ": " +
              Pevent[a]
            );
            int PartRank = getIntInput("Please enter the integer only.");
            int pRank = 0;
            // for scoring system for the teams
            switch (PartRank) {
              case 3:
                pRank = points[2];
                break;
              case 2:
                pRank = points[1];
                break;
              case 1:
                pRank = points[0];
                break;
              default:
                pRank = points[3];
                System.out.println("This team will not be scored any points");
                break;
            }
            PartScore[i] += pRank;
            System.out.println(pRank + " points scored for this event");
            if (scan.hasNextLine()) {
              scan.nextLine();
            }
          }
        }
        resultBoard(
          matchStyle,
          false,
          PartName,
          PartScore,
          Pevent,
          null,
          indexNo
        );
      }
    }
  }
}

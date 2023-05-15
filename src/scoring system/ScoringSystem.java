package assessment2.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ScoringSystem {

  // Define scanner as a class-level variable
  private static final Scanner scanner = new Scanner(System.in);

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
    } else {
      System.out.printf(
        "Congratulations!! %s Individual Participant",
        (type.substring(0, 1).toUpperCase() + type.substring(1))
      );
    }
    if (winnerTeams.size() == 1) {
      System.out.print(" " + winnerTeams.get(0));
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
      System.out.println(
              " have draw with a score of " + winner_team_score + "."
      );
    }
    System.out.println();
  }


  public static int getIntInput(String error) {
    int input = 0;
    boolean isInputValid = false;
    while (!isInputValid) {
      if (scanner.hasNextLine()) {
        String temp = scanner.nextLine();
        input = Integer.parseInt(temp);
        isInputValid = true;
      } else {
        System.out.println(error);
        scanner.next();
      }
    }
    return input;
  }

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    // Setting up of the rules to the User / Client to provide appropriate inputs for the code
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
    char choice;
    do {
      System.out.println("******** MENU ************");
      System.out.println(
        "Please Enter the following: \n 1 for Normal Teams \n 2 for Normal Individuals"
      );
      System.out.println(
        " 3 for Special Teams \n 4 for Special Individuals \n 5 to see case 5"
      );
      int option = getIntInput("Please enter the menu option from above only.");

      switch (option) {
        case 1:
          // Start of the teams and number of teams

          System.out.println("-----Teams of College------");
          System.out.println("Enter the no of Teams Entering 5 EVENTS");
          int teamNo = getIntInput("Please enter the integer only.");
          //Declaring Single Dimension Array
          String[] teamName = new String[teamNo];
          int[] teamScore = new int[teamNo];

          String[] Tevent = new String[5];
          String[] teamPart = new String[teamNo * 5];
          int teamRank;
          int eventNo = 5;

          // To check for number of teams
          // skip all if team size 0
          if (teamNo == 0) {
            System.out.println("Oops!! There are no Event!!");
          } else {
            // for event names
            for (int i = 0; i < 5; i++) {
              System.out.println(
                "Enter Event Name " + (i + 1) + " for the teams"
              );
              Tevent[i] = scan.nextLine();
            }
            int z = 0;
            for (int i = 0; i < teamNo; i++) {
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
            for (int i = 0; i < teamNo; i++) {
              System.out.println("Enter the Name of team " + (i + 1));
              teamName[i] = scan.next();
              for (int a = 0; a < eventNo; a++) {
                System.out.println(
                  "Enter rank of the team: " +
                  teamName[i] +
                  " on the event " +
                  (a + 1) +
                  ": " +
                  Tevent[a]
                );
                teamRank = getIntInput("Please enter the integer only.");
                int tRank = 0;
                // for scoring system for the teams
                switch (teamRank) {
                  case 3:
                    tRank = 5;
                    break;
                  case 2:
                    tRank = 10;
                    break;
                  case 1:
                    tRank = 20;
                    break;
                }
                if (teamRank == 0 || teamRank >= 4) {
                  System.out.println("This team will not be scored any points");
                } else {
                  teamScore[i] += tRank;
                  System.out.println(tRank + " points scored for this event");
                }
                if (scan.hasNextLine()) {
                  scan.nextLine();
                }
              }
            }
          } // end of else
          // Output for all Teams with their respective events and scores
          if (teamNo == 0) {
            System.out.println("Oops!! There are no teams!!");
          } else {
            System.out.println("-- Normal Team Information:--");
            System.out.println("Number of Teams Registered    : " + teamNo);
            System.out.println("Number of Events Participated : 5");
            System.out.println(
              "Events List for Teams : " + Arrays.asList(Tevent)
            );
            System.out.println("Score Points Won:");
            System.out.println("-----------------");
            System.out.println();
            System.out.println(
              "All Teams Scores : " + Arrays.toString(teamScore)
            );

            System.out.println(
              "----------------------------------------------------------------------------"
            );
            System.out.println(
              "\t  Team\t\t      Participants\t\t       Score"
            );
            System.out.println(
              "----------------------------------------------------------------------------"
            );

            int y = 0;
            int winner_team_score = teamScore[0];
            List<String> winnerTeams = new ArrayList<String>();

            for (int i = 0; i < teamNo; i++) {
              if (teamScore[i] > winner_team_score) {
                winner_team_score = teamScore[i];
              }

              for (int x = 0; x < 5; x++) {
                System.out.println(
                  "\t Team: " +
                  teamName[i] +
                  "\t " +
                  "Participants: " +
                  teamPart[y] +
                  "\t Team Score: " +
                  teamScore[i]
                );
                y++;
              }
              System.out.println(
                "----------------------------------------------------------------------------\n"
              );
            }

            for (int i = 0; i < teamNo; i++) {
              if (teamScore[i] == winner_team_score) {
                winnerTeams.add(teamName[i]);
              }
            }
            calculateWinner(winnerTeams, "normal", true, winner_team_score);
          }

          break;
        // the start of individual participants
        // number of individuals
        case 2:
          System.out.println("-----College Individual Participants-----");
          int PartNo;
          System.out.println(
            "Enter the number of individuals participating 5 EVENTS" +
            " LIMITED SPACE OF 20"
          );
          PartNo = getIntInput("Please enter the integer only.");
          String[] PartName = new String[PartNo];

          int[] PartScore = new int[PartNo];

          String[] Pevent = new String[5];
          int PartRank;
          // condition checking
          // skip all code for individual if 0

          if (PartNo == 0) {} else {
            // for event name for the individuals
            System.out.println("Enter the 5 event names for participants ");
            for (int i = 0; i < 5; i++) {
              System.out.println(
                "Enter Name of the event " +
                (i + 1) +
                " that the individuals are entering"
              );
              Pevent[i] = scan.next();
            }
            // name and rank of the individuals
            for (int i = 0; i < PartNo; i++) {
              System.out.println("Enter name of Individual " + (i + 1));
              PartName[i] = scan.next();

              for (int a = 0; a < 5; a++) {
                System.out.println(
                  "Enter rank of the individual: " +
                  PartName[i] +
                  " on the event" +
                  (a + 1) +
                  ": " +
                  Pevent[a]
                );
                PartRank = getIntInput("Please enter the integer only.");
                int pRank = 0;
                // start of scoring system for the individuals
                switch (PartRank) {
                  case 3:
                    pRank = 5;
                    break;
                  case 2:
                    pRank = 10;
                    break;
                  case 1:
                    pRank = 20;
                    break;
                }
                if (PartRank == 0 || PartRank >= 4) {
                  System.out.println("This team will not be scored any points");
                } else {
                  PartScore[i] += pRank;
                  System.out.println(pRank + " points scored for this event");
                }
                if (scan.hasNextLine()) {
                  scan.nextLine();
                }
              }
            }
          } // end of else
          // Output for all Individuals with their respective events and scores
          if (PartNo == 0) {
            System.out.println("Oops! There are no participants\n");
          } else {
            System.out.println("No. of Participants: " + PartNo);
            System.out.println("Events Participated : 5");
            System.out.println(
              "Events List for Individuals : " + Arrays.asList(Pevent)
            );
            System.out.println("Score Points won:");
            System.out.println(
              "All Individual Scores:" + Arrays.toString(PartScore)
            );
            System.out.println(
              "----------------------------------------------------------------------------"
            );
            System.out.println("\tIndividual Name \t   Score");
            System.out.println(
              "----------------------------------------------------------------------------"
            );

            int pwinner_score = PartScore[0];
            List<String> winners = new ArrayList<String>();

            for (int i = 0; i < PartNo; i++) {
              if (PartScore[i] > pwinner_score) {
                pwinner_score = PartScore[i];
              }
              System.out.println(
                " \tIndividual Name: " +
                PartName[i] +
                "\t Score: " +
                PartScore[i]
              );
            }
            System.out.println(
              "----------------------------------------------------------------------------"
            );
            for (int i = 0; i < PartNo; i++) {
              if (PartScore[i] == pwinner_score) {
                winners.add(PartName[i]);
              }
            }
            calculateWinner(winners, "normal", false, pwinner_score);
            System.out.println(
              "----------------------------------------------------------------------------"
            );
            System.out.println("********************");
          }

          break;
        case 3:
          // Starting of special teams
          // for number of special teams

          System.out.println(
            "Special Teams and Individuals Represent Teams and Individuals entering only 1 event"
          );

          System.out.println("-----Special_Teams-----");
          System.out.println("Enter number of Teams Entering only 1 EVENT");
          int SpecTeamNo = getIntInput("Please enter the integer only.");
          String[] SpecTeamName = new String[SpecTeamNo];
          String[] STevent = new String[1];

          int[] SpecTeamScore = new int[SpecTeamNo];
          String[] SteamPart = new String[SpecTeamNo * 5];
          int sTeamRank;
          // condition checking for number of special teams
          //skip if 0

          if (SpecTeamNo == 0) {} else {
            // event for special team
            for (int i = 0; i < 1; i++) {
              System.out.println(
                "Enter Event Name " + (i + 1) + " for the teams"
              );
              STevent[i] = scan.next();
            }
            // participant name for special team
            int z1 = 0;
            for (int a = 0; a < SpecTeamNo; a++) {
              for (int i = 0; i < 5; i++) {
                System.out.println(
                  "Enter Participant name " + (i + 1) + " for team " + (a + 1)
                );
                SteamPart[z1] = scan.next();
                z1++;
              }
            }

            // name and rank of special teams

            for (int i = 0; i < SpecTeamNo; i++) {
              System.out.println("Enter Name of team " + (i + 1));
              SpecTeamName[i] = scan.next();

              for (int a = 0; a < 1; a++) {
                System.out.println(
                  "Enter rank of the team: " +
                  SpecTeamName[i] +
                  " on the event: " +
                  STevent[0]
                );
                sTeamRank = getIntInput("Please enter the integer only.");
                int stRank = 0;
                // scoring system for special team
                switch (sTeamRank) {
                  case 3:
                    stRank = 60;
                    break;
                  case 2:
                    stRank = 80;
                    break;
                  case 1:
                    stRank = 100;
                    break;
                }
                if (sTeamRank == 0 || sTeamRank >= 4) {
                  System.out.println("This team will not be scored any points");
                } else {
                  SpecTeamScore[i] += stRank;
                  System.out.println(stRank + " points scored for this event");
                }
                if (scan.hasNextLine()) {
                  scan.nextLine();
                }
              }
            }
          }
          // Output for all Special Teams with their respective events and scores
          if (SpecTeamNo == 0) {
            System.out.println("There are no Special Teams");
          } else {
            System.out.println("The Number of Special Teams " + SpecTeamNo);
            System.out.println("Events Participated : 1");
            System.out.println(
              "Events List for Teams: " + Arrays.asList(STevent)
            );
            System.out.println("Scores Won:");
            System.out.println(
              "All Special Team Scores: " + Arrays.toString(SpecTeamScore)
            );
            System.out.println(
              "----------------------------------------------------------------------------"
            );
            System.out.println(
              "\t Special Team\t  Individual Participants\t    Score"
            );
            System.out.println(
              "----------------------------------------------------------------------------"
            );
            int y1 = 0;
            int Swinner_team_score = SpecTeamScore[0];
            List<String> SwinnerTeams = new ArrayList<String>();

            for (int i = 0; i < SpecTeamNo; i++) {
              if (SpecTeamScore[i] > Swinner_team_score) {
                Swinner_team_score = SpecTeamScore[i];
              }
              for (int x = 0; x < 5; x++) {
                System.out.println(
                  "\tTeam Name: " +
                  SpecTeamName[i] +
                  "\t Team Participants: " +
                  SteamPart[y1] +
                  "\t  Team Score: " +
                  SpecTeamScore[i]
                );
                y1++;
              }
              System.out.println(
                "----------------------------------------------------------------------------"
              );
            }
            System.out.println(
              "----------------------------------------------------------------------------"
            );
            for (int i = 0; i < SpecTeamNo; i++) {
              if (SpecTeamScore[i] == Swinner_team_score) {
                SwinnerTeams.add(SpecTeamName[i]);
              }
            }
            calculateWinner(SwinnerTeams, "special", true, Swinner_team_score);
          }

          break;
        case 4:
          // the start of special individuals
          // number of special individuals

          System.out.println("-----Special_Individuals-----");

          int SpecPartNo;
          System.out.println(
            "Enter Number of Individuals only Entering 1 event "
          );
          SpecPartNo = getIntInput("Please enter the integer only.");
          String[] SpecPartName = new String[SpecPartNo];
          String[] SPevent = new String[1];

          int[] SpecPartScore = new int[SpecPartNo];
          //condition checking number of special individuals
          //skip all codes for special individuals if 0

          if (SpecPartNo == 0) {} else {
            // event for the special individuals
            for (int i = 0; i < 1; i++) {
              System.out.println(
                "Enter Event Name " + (i + 1) + " for the individuals"
              );
              SPevent[i] = scan.nextLine();
            }

            // name and rank input of special individuals

            for (int i = 0; i < SpecPartNo; i++) {
              System.out.println("Enter Name of individual " + (i + 1));
              SpecPartName[i] = scan.next();

              for (int a = 0; a < 1; a++) {
                System.out.println(
                  "Enter rank of the individual " +
                  SpecPartName[i] +
                  " on the event:" +
                  SPevent[0]
                );
                int sPartRank = getIntInput("Please enter the integer only.");
                int spRank = 0;
                // scoring system for the individuals
                switch (sPartRank) {
                  case 3:
                    spRank = 60;
                    break;
                  case 2:
                    spRank = 80;
                    break;
                  case 1:
                    spRank = 100;
                    break;
                }
                if (sPartRank == 0 || sPartRank >= 4) {
                  System.out.println(
                    "This individual will not be scored any points"
                  );
                } else {
                  SpecPartScore[i] += spRank;
                  System.out.println(spRank + " points scored for this event");
                }
                if (scan.hasNextLine()) {
                  scan.nextLine();
                }
              }
            }
          } // end of else
          // Output for all Special Individuals with their respective events and scores
          if (SpecPartNo == 0) {
            System.out.println("There are  no Special Individuals");
          } else {
            System.out.println("Number of Special Individuals " + SpecPartNo);
            System.out.println("Events Participated : 1");
            System.out.println(
              "Events List for Teams : " + Arrays.asList(SPevent)
            );
            System.out.println("Scores Won: ");
            System.out.println(
              "All Special Individuals Scores: " +
              Arrays.toString(SpecPartScore)
            );
            System.out.println(
              "----------------------------------------------------------------------------"
            );
            System.out.println("\tSpecial Individual\t    Score");
            System.out.println(
              "----------------------------------------------------------------------------"
            );
            int spwinner_score = SpecPartScore[0];
            List<String> winners = new ArrayList<String>();

            for (int i = 0; i < SpecPartNo; i++) {
              if (SpecPartScore[i] > spwinner_score) {
                spwinner_score = SpecPartScore[i];
              }
              System.out.println(
                "\tIndividual Name: " +
                SpecPartName[i] +
                " \t Score: " +
                SpecPartScore[i]
              );
            }
            System.out.println(
              "----------------------------------------------------------------------------"
            );
            for (int i = 0; i < SpecPartNo; i++) {
              if (SpecPartScore[i] == spwinner_score) {
                winners.add(SpecPartName[i]);
              }
            }
            calculateWinner(winners, "special", false, spwinner_score);
            System.out.println(
              "----------------------------------------------------------------------------"
            );
            System.out.println("********************");
          }
          break;
        default:
          System.out.println(" Please enter 1 or 2 or 3 or 4 only ");
          break;
      } // switch end

      System.out.println("Do you wish to continue (y / n)");
      choice = scan.next().toLowerCase().charAt(0);
    } while (choice == 'y');
    scan.close();
    System.out.println("Thanks for Participation! See you Again, Thank you!");
    System.out.println(
      "----------------------------------------------------------------------------\n"
    );
  } // main end
} // class end
//Flowchart
//algor
//code

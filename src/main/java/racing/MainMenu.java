package racing;

import dao.*;
import entity.Horse;
import entity.Player;
import entity.PlayerBet;
import entity.RacingMap;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static racing.Solution.curDate;
import static racing.Solution.curIppo;

public  class MainMenu {

    public static void mainMenu() throws InputMismatchException {
        Scanner in  = new Scanner(System.in);
        int choice;
        try {
            do {
                System.out.println("            1. Statistic data");
                System.out.println("            2. Bets set");
                System.out.println("            3. Racing admninistration");
                System.out.println("            4. Exit");

                System.out.println("            --------------------\n"+"Make your choice ...");
                choice = in.nextInt();

                switch (choice){
                    case 1:
                        menu1();
                        break;
                    case 2:
                        menu2();
                        break;
                    case 3:
                        menu3();
                        break;
                    default:
                        break;
                }
            } while (choice != 4);
        } catch (InputMismatchException e) {
            System.out.println(e);;
        }
    }

    public static void menu1(){
        /**            1. Statistic data                     */
        ViewStats viewStats;
        Scanner in  = new Scanner(System.in);
        int choice;
        do {
            System.out.println("            1. Look at the nearest racing");
            System.out.println("            2. Horses winners");
            System.out.println("            3. Jokeys winners");
            System.out.println("            4. Exit");

            System.out.println("            --------------------\n"+"Make your choice ...");
            choice = in.nextInt();

            switch (choice){
                case 1:
                    RacingMapDAO racingMapDAO = new RacingMapDAO();
                    RacingMap racingMap = new RacingMap();
                    racingMap.setId_ippodrom(curIppo);
                    racingMap.setDate_ride(curDate);
                    racingMapDAO.getRacingMaps(racingMap);
                    break;
                case 2:
                    viewStats = new ViewStats();
                    viewStats.viewHorseWinner();
                    break;
                case 3:
                    viewStats = new ViewStats();
                    viewStats.viewJokeyWinner();
                    break;
                default:
                    break;
            }
        } while (choice != 4);
    }

    public static void menu2(){
        /**              2. Bets set                   ***/
        Player player = null;
        ViewStats viewStats;
        Scanner in  = new Scanner(System.in);
        int choice;
        do {
            System.out.println("            1. Loook at types of bet");
            System.out.println("            2. Login");
            System.out.println("            3. Registration");
            System.out.println("            4. Setting bet");
            System.out.println("            5. My bets");
            System.out.println("            6. ChangeMyInfo");
            System.out.println("            7. Exit");

            System.out.println("            --------------------\n"+"Make your choice ...");
            choice = in.nextInt();

            switch (choice){
                case 1:
                    viewStats = new ViewStats();
                    viewStats.viewTypeBets();
                    break;
                case 2:
                    PlayerActivity.registration();
                    break;
                case 3:
                    PlayerActivity.firstRegistration();
                    break;
                case 4:
                    PlayerActivity.setBet(Solution.player);
                    break;
                case 5:
                    PlayerBet playerBet = new PlayerBet();
                    playerBet.setId(Solution.player.getId());
                    playerBet.setDateRide(Date.valueOf("2021-01-08"));

                    PlayerBetDAO playerBetDAO = new PlayerBetDAO();
                    playerBetDAO.getPlayerBets(playerBet);
                    break;
                case 6:
                    PlayerActivity.changeMyInfo();
                    break;
                default:
                    break;
            }
        } while (choice != 7);
    }

    public static void menu3(){
        /**                  Racing admninistration            **/
        Horse horse = new Horse();
        HorseDAO horseDAO = new HorseDAO();
        int choice;

        Scanner in  = new Scanner(System.in);
        do {
            System.out.println("            1. Add horse");
            System.out.println("            2. Update horse");
            System.out.println("            3. Delete horse");
            System.out.println("            4. Add Racing Map");
            System.out.println("            5. Update Racing Map");
            System.out.println("            6. Exit");

            System.out.println("            --------------------\n"+"Make your choice ...");
            choice = in.nextInt();

            StudDAO studDAO = new StudDAO();
            horse = new Horse();
            String misc;
            switch (choice){
                case 1:
                    misc = in.nextLine();
                    System.out.println("            Input horse name: ");
                    horse.setName(in.nextLine());
                    System.out.println("            Input horse birth day [2021-01-08]: ");
                    horse.setBirth(Date.valueOf(in.nextLine()));
                    System.out.println("            Input horse sex [stallion/mare]: ");
                    horse.setSex(in.nextLine());

                    studDAO.getStudes();

                    System.out.println("            Input ID of stud [N]: ");
                    horse.setIdStud(in.nextInt());

                    horseDAO = new HorseDAO();
                    horseDAO.save(horse);
                    horse = horseDAO.lookFor(horse);
                    break;
                case 2:
                    misc = in.nextLine();
                    System.out.println("            Input horse name: ");
                    horse.setName(in.nextLine());
                    System.out.println("            Input horse birth day [2021-01-08]: ");
                    horse.setBirth(Date.valueOf(in.nextLine()));
                    System.out.println("            Input horse sex [stallion/mare]: ");
                    horse.setSex(in.nextLine());

                    studDAO.getStudes();

                    System.out.println("            Input ID of stud [N]: ");
                    horse.setIdStud(in.nextInt());

                    horse = horseDAO.lookFor(horse);

                    misc = in.nextLine();
                    System.out.println("            Input horse NEW name: ");
                    horse.setName(in.nextLine());
                    System.out.println("            Input horse NEW birth day [2021-01-08]: ");
                    horse.setBirth(Date.valueOf(in.nextLine()));
                    System.out.println("            Input horse NEW sex [stallion/mare]: ");
                    horse.setSex(in.nextLine());

                    studDAO.getStudes();

                    System.out.println("            Input NEW ID of stud [N]: ");
                    horse.setIdStud(in.nextInt());

                    horseDAO.update(horse);
                    break;
                case 3:
                    misc = in.nextLine();
                    System.out.println("            Input horse name: ");
                    horse.setName(in.nextLine());
                    System.out.println("            Input horse birth day [2021-01-08]: ");
                    horse.setBirth(Date.valueOf(in.nextLine()));
                    System.out.println("            Input horse sex [stallion/mare]: ");
                    horse.setSex(in.nextLine());

                    horse = horseDAO.lookFor(horse);

                    horseDAO.remove(horse);
                    break;
                case 4:
                    System.out.println("Add Racing Map. = UNDER CONSTRACTION =");
                    break;
                case 5:
                    System.out.println("Update Racing Map. = UNDER CONSTRACTION =");
                    break;
                default:
                    break;
            }
        } while (choice != 6);
    }

}

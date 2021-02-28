package racing;

import dao.ViewStats;
import entity.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

import static racing.Solution.curDate;
import static racing.Solution.curIppo;

public  class MainMenu {

    public static void mainMenu() throws InputMismatchException {
        Scanner in  = new Scanner(System.in);
        int choice;
        try {
            do {
                System.out.println("1. Statistic data");
                System.out.println("2. Bets set");
                System.out.println("3. Racing admninistration");
                System.out.println("4. Exit");

                System.out.println("--------------------\n"+"Make your choice ...");
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
        Scanner in  = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1. Look at the nearest racing");
            System.out.println("2. Horses winners");
            System.out.println("3. Jokeys winners");
            System.out.println("4. Coaches winners");
            System.out.println("5. Studes winners");
            System.out.println("6. Exit");

            System.out.println("--------------------\n"+"Make your choice ...");
            choice = in.nextInt();

            switch (choice){
                case 1:
                    PlayerActivity.showRacingMap(curIppo, curDate);
                    break;
                case 2:
                    System.out.println("Horses winners");
                    break;
                case 3:
                    ViewStats viewStats = new ViewStats();
                    viewStats.viewJokeyWinner();
                    break;
                case 4:
                    System.out.println("Coaches winners");
                    break;
                case 5:
                    System.out.println("Studes winners");
                    break;
                default:
                    break;
            }
        } while (choice != 6);
    }

    public static void menu2(){
        /**              2. Bets set                   ***/
        Player player = null;
        Scanner in  = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1. Loook at types of bet");
            System.out.println("2. Registration");
            System.out.println("3. Setting bet");
            System.out.println("4. My bets");
            System.out.println("5. ChangeMyInfo");
            System.out.println("6. Exit");

            System.out.println("--------------------\n"+"Make your choice ...");
            choice = in.nextInt();

            switch (choice){
                case 1:
                    System.out.println("Loook at types of bet");
                    break;
                case 2:
                    PlayerActivity.registration();
                    break;
                case 3:
                    PlayerActivity.setBet(player);
                    break;
                case 4:
                    System.out.println("My bets");
                    break;
                case 5:
                    System.out.println("ChangeMyInfo");
                    PlayerActivity.changeMyInfo();
                    break;
                default:
                    break;
            }
        } while (choice != 6);
    }

    public static void menu3(){
        /**                  Racing admninistration            **/
        Scanner in  = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1. Add horse");
            System.out.println("2. Update horse");
            System.out.println("3. Add Racing Map");
            System.out.println("4. Update Racing Map");
            System.out.println("5. Exit");

            System.out.println("--------------------\n"+"Make your choice ...");
            choice = in.nextInt();

            switch (choice){
                case 1:
                    System.out.println("Add horse");
                    break;
                case 2:
                    System.out.println("Update horse");
                    break;
                case 3:
                    System.out.println("Add Racing Map");
                    break;
                case 4:
                    System.out.println("Update Racing Map");
                    break;
                default:
                    break;
            }
        } while (choice != 5);
    }

}

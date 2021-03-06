package racing;

import dao.PlayerBetDAO;
import dao.PlayerDAO;
import dao.RacingMapDAO;
import entity.PlayerBet;
import entity.Player;
import entity.RacingMap;

import static racing.Solution.*;

import java.sql.Date;
import java.util.Scanner;

public class PlayerActivity {

    public PlayerActivity() {
    }

    public static Player login(){

        PlayerDAO playerDAO = new PlayerDAO();
        Scanner in  = new Scanner(System.in);

        System.out.println("You need to login:");
        System.out.println("------------------");

        System.out.println("Input login:");
        String login = in.nextLine();
        System.out.println("Input password:");
        String password =  in.nextLine();

        Solution.player = playerDAO.lookFor(login, password);
        if (Solution.player != null){
            System.out.println("You are logged !");
            System.out.println("----------------");
        }
        return Solution.player;
    }


    public static Player registration(){
        Player playerIn;

        PlayerDAO playerDAO = new PlayerDAO();
        playerIn    = new Player();
        Scanner in  = new Scanner(System.in);

        Player player;
        player = login();
        if(player == null){
            System.out.println("Player not found. Would you register? [Y/N]");
            if ("Y".equals(in.nextLine())){
                firstRegistration();
            }
        }
        return player;
    }
    public static Player firstRegistration(){
        Player playerIn;
        Player playe;
        PlayerDAO playerDAO = new PlayerDAO();
        playerIn    = new Player();
        Scanner in  = new Scanner(System.in);

        System.out.println("Input First Name:");
        playerIn.setFirstName (in.nextLine());
        System.out.println("Input Last Name:");
        playerIn.setLastName  (in.nextLine());
        System.out.println("Input Login:");
        playerIn.setLogin (in.nextLine());
        System.out.println("Input Password:");
        playerIn.setPassword(in.nextLine());

        player = playerDAO.lookFor(playerIn);
        if (player == null){
            player = playerDAO.save(playerIn);
            player = playerDAO.lookFor(player);
        } else
        {
            System.out.println("You are already registered ! You should login");
        }
        return player;
    }


    public static Player changeMyInfo(){
        Player playerIn;
        PlayerDAO playerDAO = new PlayerDAO();
        playerIn    = new Player();

        Scanner in  = new Scanner(System.in);

        Player player;
        player = playerDAO.lookFor(Solution.player);

        if(player == null) {
            System.out.println("Player not found.");
        }
        else {
            System.out.println("Would you change info? [Y/N]");
            if ("Y".equals(in.nextLine())){
                /** Change everything except id */
                System.out.println("Input First Name:");
                playerIn.setFirstName (in.nextLine());
                System.out.println("Input Last Name:");
                playerIn.setLastName  (in.nextLine());
                System.out.println("Input login:");
                playerIn.setLogin     (in.nextLine());
                System.out.println("Input password:");
                playerIn.setPassword  (in.nextLine());

                playerIn.setId(player.getId());
                player = playerDAO.update(playerIn);
                Solution.player = playerDAO.lookFor(player);
            }
        }
        return Solution.player;
    }

    public static PlayerBetDAO setBet(Player player){
        /** player makes bet*/

        login();

        Scanner in = new Scanner(System.in);
        /** Look at the map of ride*/
        RacingMapDAO racingMapDAO = new RacingMapDAO();
        RacingMap racingMap = new RacingMap();
        racingMap.setId_ippodrom(curIppo);
        racingMap.setDate_ride(curDate);
        racingMapDAO.getRacingMaps(racingMap);

        PlayerBetDAO playerBetDAO = new PlayerBetDAO();
        PlayerBet playerBet = new PlayerBet();

        /** инициализируем значения перед поиском его ставок*/
        playerBet.setId(Solution.player.getId());
        playerBet.setDateRide(Date.valueOf("2021-01-08"));
        /** Let's look at bets made by player before*/
        playerBetDAO.getPlayerBets(playerBet);

        //String misc = in.nextLine();
        System.out.println("            Input ID of ippodrome [N]: ");
        playerBet.setIdIppodrom(Integer.valueOf(in.nextLine()));

        System.out.println("            Input Date of ride [2021-01-08]: ");
        playerBet.setDateRide(Date.valueOf(in.nextLine()));

        System.out.println("            Input number of ride [N]: ");
        playerBet.setNumRide(in.nextInt());

        System.out.println("            Input ID of horse [N]: ");
        playerBet.setIdHorse(in.nextInt());

        System.out.println("            Input ID of bet [N]: ");
        playerBet.setIdTypeBet(in.nextInt());

        /** Let's look at bet made by player before*/
        if (playerBetDAO.getPlayerBet(playerBet) == null){
            /** Change values of Bet and insert new if not found */
            //playerBet.setIdTypeBet(3);
            System.out.println("            Input your BET (roubles)[N]: ");
            playerBet.setBet(in.nextInt());
            playerBetDAO.save(playerBet);
        }
        return playerBetDAO;
    }

    public static PlayerBetDAO ChangeBet(Player player){
        /** player makes bet*/

        /** Look at the map of ride*/
        RacingMapDAO racingMapDAO = new RacingMapDAO();
        RacingMap racingMap = new RacingMap();
        racingMap.setId_ippodrom(curIppo);
        racingMap.setDate_ride(curDate);
        racingMapDAO.getRacingMaps(racingMap);

        PlayerBetDAO playerBetDAO = new PlayerBetDAO();
        PlayerBet playerBet = new PlayerBet();

        /** инициализируем значения перед поиском его ставок*/
        playerBet.setId(Solution.player.getId());
        playerBet.setDateRide(Date.valueOf("2021-01-08"));
        /** Let's look at bets made by player before*/
        playerBetDAO.getPlayerBets(playerBet);

        playerBet.setIdIppodrom(1);
        playerBet.setDateRide(Date.valueOf("2021-01-08"));
        playerBet.setNumRide(1);
        playerBet.setIdHorse(2);
        playerBet.setIdTypeBet(5);

        /** Let's look at bet made by player before*/
        if (playerBetDAO.getPlayerBet(playerBet) != null){
            /** Change values of Bet and update if found */
            playerBet.setIdTypeBet(5);
            playerBet.setBet(900);
            playerBetDAO.update(playerBet);
        }
        return playerBetDAO;
    }


    /** playerBets = playerBetDAO.getPlayerBets(playerBet);*/

    /**
     * racingMap.setId_ippodrom(id_ippodrom);
     racingMap.setDate_ride(date);
     racingMaps = racingMapDAO.getRacingMaps(racingMap);*/

    public static PlayerBet removeBet(PlayerBet playerBet){
        PlayerBetDAO playerBetDAO = new PlayerBetDAO();
        Scanner in = new Scanner(System.in);
            System.out.println("Are you real would remove this bet? [Y/N]");
            if ("Y".equals(in.nextLine())) {
                playerBetDAO.remove(playerBet);
                playerBetDAO.getPlayerBets(playerBet);
            }
        return playerBet;
    }

}

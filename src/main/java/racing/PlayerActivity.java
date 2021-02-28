package racing;

import dao.PlayerBetDAO;
import dao.PlayerDAO;
import dao.RacingMapDAO;
import entity.PlayerBet;
import entity.Player;
import entity.RacingMap;

import static racing.Solution.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerActivity {

    public PlayerActivity() {
    }

    public static Player registration(){
        Player playerIn;

        PlayerDAO playerDAO = new PlayerDAO();
        playerIn    = new Player();
        Scanner in  = new Scanner(System.in);

        System.out.println("You need to login:");
        System.out.println("------------------");

        System.out.println("Input login:");
        playerIn.setLogin     (in.nextLine());
        System.out.println("Input password:");
        playerIn.setPassword  (in.nextLine());

        Player player;
        player = playerDAO.lookFor(playerIn.getLogin(), playerIn.getPassword());

        if(player.getId() == 0){
            System.out.println("Player not found. Would you register? [Y/N]");
            if ("Y".equals(in.nextLine())){
                System.out.println("Input First Name:");
                playerIn.setFirstName (in.nextLine());
                System.out.println("Input Last Name:");
                playerIn.setLastName  (in.nextLine());
                player = playerDAO.save(playerIn);
            }
        }
        return player;
    }

    public static Player changeMyInfo(){
        Player playerIn;
        PlayerDAO playerDAO = new PlayerDAO();
        playerIn    = new Player();

        Scanner in  = new Scanner(System.in);

        System.out.println("Input login:");
        playerIn.setLogin     (in.nextLine());
        System.out.println("Input password:");
        playerIn.setPassword  (in.nextLine());

        Player player;
        player = playerDAO.lookFor(playerIn.getLogin(), playerIn.getPassword());

        if(player.getId() == 0) {
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
            }
        }
        return player;
    }

    public static PlayerBetDAO setBet(Player player){
        /** player makes bet*/

        /** Look at the map of ride*/
        showRacingMap(curIppo, curDate);

        PlayerBetDAO playerBetDAO = new PlayerBetDAO();
        PlayerBet playerBet = new PlayerBet();

        /** инициализируем значения перед поиском его ставок*/
        playerBet.setId(player.getId());
        playerBet.setDateRide(Date.valueOf("2021-01-08"));
        /** Let's look at bets made by player before*/
        showBetsOfPlayer(playerBet);

        playerBet.setIdIppodrom(1);
        playerBet.setDateRide(Date.valueOf("2021-01-08"));
        playerBet.setNumRide(1);
        playerBet.setIdHorse(2);
        playerBet.setIdTypeBet(5);

        /** Let's look at bet made by player before*/
        if (playerBetDAO.getPlayerBet(playerBet) == null){
            /** Change values of Bet and insert new if not found */
            playerBet.setIdTypeBet(2);
            playerBet.setBet(900);
            playerBetDAO.save(playerBet);
        } else{
            /** Change values of Bet and update if found */
            playerBet.setIdTypeBet(5);
            playerBet.setBet(900);
            playerBetDAO.update(playerBet);
        }
        return playerBetDAO;
    }

    public static void showRacingMap(int id_ippodrom, Date date){
        /** show map racing: num, horses, jokeys etc*/
        RacingMapDAO racingMapDAO = new RacingMapDAO();
        RacingMap racingMap = new RacingMap();
        List<RacingMap> racingMaps;// = new ArrayList<RacingMap>();

        racingMap.setId_ippodrom(id_ippodrom);
        racingMap.setDate_ride(date);
        racingMaps = racingMapDAO.getRacingMaps(racingMap);

        for (RacingMap r: racingMaps
        ) {
            System.out.println(r);
        }
    }

    public static void showBetsOfPlayer(PlayerBet playerBet){
        /** show bets of player*/
        PlayerBetDAO playerBetDAO = new PlayerBetDAO();
        List<PlayerBet> playerBets; // = new ArrayList<PlayerBet>();

        playerBets = playerBetDAO.getPlayerBets(playerBet);

        for (PlayerBet bp: playerBets
        ) {
            System.out.println(bp);
        }
    }

    public static PlayerBet removeBet(PlayerBet playerBet){
        PlayerBetDAO playerBetDAO = new PlayerBetDAO();
        Scanner in = new Scanner(System.in);
            System.out.println("Are you real would remove this bet? [Y/N]");
            if ("Y".equals(in.nextLine())) {
                playerBetDAO.remove(playerBet);
                showBetsOfPlayer(playerBet);
            }
        return playerBet;
    }
}

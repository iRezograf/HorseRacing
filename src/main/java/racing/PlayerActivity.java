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

    public Player registration(){
        /*String firstName,
                             String lastName,
                             String login,
                             String password*/
        PlayerDAO playerDAO = new PlayerDAO();
        Player player = null;
        Player playerIn = null;

        Scanner in  = new Scanner(System.in);

        player      = new Player();
        playerIn    = new Player();

        System.out.println("You need to login:");
        System.out.println("------------------");
        //System.out.println("Input First Name:");
        //playerIn.setFirstName (in.nextLine());
        //System.out.println("Input Last Name:");
        //playerIn.setLastName  (in.nextLine());
        System.out.println("Input login:");
        playerIn.setLogin     (in.nextLine());
        System.out.println("Input password:");
        playerIn.setPassword  (in.nextLine());

        player = playerDAO.lookFor(playerIn.getLogin(), playerIn.getPassword());

        if(player.getId() == 0){
            System.out.println("Player not found. Would you register? [Y/N]");
            if ("Y".equals(in.nextLine())){
                player = playerDAO.save(playerIn);
            }
        }
        in.close();
        return player;
    }

    public Player changeMyInfo(){
        PlayerDAO playerDAO = new PlayerDAO();
        Player player = null;
        Player playerIn = null;

        Scanner in  = new Scanner(System.in);

        player      = new Player();
        playerIn    = new Player();

        System.out.println("Input First Name:");
        playerIn.setFirstName (in.nextLine());
        System.out.println("Input Last Name:");
        playerIn.setLastName  (in.nextLine());
        System.out.println("Input login:");
        playerIn.setLogin     (in.nextLine());
        System.out.println("Input password:");
        playerIn.setPassword  (in.nextLine());

        player = playerDAO.lookFor(playerIn);
        System.out.println(player);

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
                player = playerDAO.update(playerIn);
            }
        }
        in.close();
        return player;
    }

    public PlayerBetDAO setBet(Player player){
        /** player makes bet*/

        /** Look at the map of ride*/
        ShowRacingMap(curIppo, curDate);

        PlayerBetDAO playerBetDAO = new PlayerBetDAO();
        PlayerBet playerBet = new PlayerBet();
        System.out.println(player);

        /** инициализируем значения перед поиском его ставок*/
        playerBet.setId(player.getId());
        playerBet.setDateRide(Date.valueOf("2021-01-08"));
        /** Let's look at bets made by player before*/
        ShowBetsOfPlayer(playerBet);

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
            /*Change*/
        }

        return playerBetDAO;
    }

    public void ShowRacingMap(int id_ippodrom, Date date){
        /** show map racing: num, horses, jokeys etc*/
        RacingMapDAO racingMapDAO = new RacingMapDAO();
        List<RacingMap> racingMaps = new ArrayList<RacingMap>();
        racingMaps = racingMapDAO.getRacingMaps(curIppo, curDate);

        for (RacingMap r: racingMaps
        ) {
            System.out.println(r);
        }
    }

    public void ShowBetsOfPlayer(PlayerBet playerBet){
        /** show bets of player*/
        PlayerBetDAO playerBetDAO = new PlayerBetDAO();
        List<PlayerBet> playerBets = new ArrayList<PlayerBet>();

        playerBets = playerBetDAO.getPlayerBets(playerBet);

        for (PlayerBet bp: playerBets
        ) {
            System.out.println(bp);
        }
    }
}

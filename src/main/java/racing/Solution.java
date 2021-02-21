package racing;

import dao.JokeyDAO;
import dao.PlayerBetDAO;
import dao.PlayerDAO;
import dao.RacingMapDAO;
import entity.Player;
import entity.PlayerBet;
import entity.RacingMap;

import java.sql.*;
import java.util.Scanner;

public class Solution {
    public final static String URL = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacing";
    // <version>9.2.0.jre15</version>
    public final static String USER = "RRA";
    public final static String PASSWORD = "rra";
    public static Connection con = null;
    public static int curIppo = 1;
    public static Date curDate = Date.valueOf("2021-01-08");
    //public static Scanner in;
    /*public static void InitParam(){
        Scanner ins  = new Scanner(System.in);
        System.out.println("Init start value");
        System.out.println("ippodrom:");
        curIppo =  ins.nextInt();
        System.out.println("date in format [2021-01-08]:");
        curDate = Date.valueOf(ins.next());
        ins.nextLine();
        ins.close();
    }*/

    public static void main(String[] args) throws  Exception{
        try {
            con = DriverManager.getConnection(URL, USER,PASSWORD);

            Scanner in  = new Scanner(System.in);
            /** BEGIN:
             * Tetsing Player: insert, update, remove, changeMyInfo, Registration */
            /*PlayerActivity playerActivity = new PlayerActivity();
            Player player;
            player = playerActivity.registration();
            if (player != null){
                System.out.printf("You are registered " + player);
            }else
                System.out.println("You are not registered");
            player = playerActivity.changeMyInfo();
            PlayerDAO playerDAO = new PlayerDAO();
            playerDAO.remove(player);*/
            /** Tetsing Player: insert, update, remove, changeMyInfo, Registration
             * END*/

            /** BEGIN:
             * Tetsing SetBet */
            PlayerActivity playerActivity = new PlayerActivity();
            Player player = playerActivity.registration();
            playerActivity.setBet(player);
            /** Tetsing SetBet
             * END*/

            /** BEGIN
             * Testing removeBet */
            /*PlayerBetDAO playerBetDAO = new PlayerBetDAO();
            PlayerBet playerBet = new PlayerBet();
            *//** инициализируем значения перед удалением*//*
            playerBet.setId(player.getId());
            playerBet.setIdIppodrom(1);
            playerBet.setDateRide(Date.valueOf("2021-01-08"));
            playerBet.setNumRide(1);
            playerBet.setIdHorse(2);
            playerBet.setIdTypeBet(5);

            System.out.println(playerBet);
            playerActivity.removeBet(playerBet);*/
            /** Testing removeBet
             * END */

            /** BEGIN
             * Testing racingMap delete */
            /*RacingMap racingMap = new RacingMap();
            racingMap.setId_ippodrom(2);
            racingMap.setDate_ride(Date.valueOf("2021-01-08"));
            racingMap.setNum_ride(1);
            racingMap.setId_horse(1);

            RacingMapDAO racingMapDAO = new RacingMapDAO();
            racingMapDAO.remove(racingMap);*/
             /** Testing racingMap delete
              END*/

            /** BEGIN
             * Testing racingMap update */
            /*RacingMap racingMap = new RacingMap();
            racingMap.setId_ippodrom(2);
            racingMap.setDate_ride(Date.valueOf("2021-01-08"));
            racingMap.setNum_ride(1);
            racingMap.setId_horse(1);
            racingMap.setId_jokey(1);
            racingMap.setId_coach(1);
            racingMap.setDistance(1999);
            racingMap.setLast_ride(Date.valueOf("2021-02-20"));

            RacingMapDAO racingMapDAO = new RacingMapDAO();
            racingMapDAO.update(racingMap);
            System.out.println(racingMapDAO.getRacingMaps(racingMap));
*/            /** Testing racingMap update
             END*/

            //PlayerDAO playerDAO = new PlayerDAO();
            //player = playerActivity.changeMyInfo();
            /** print and test that update was succefull*/
            //System.out.printf("You are updated to: " + playerDAO.lookFor(player));


            //JokeyDAO jokeyDAO = new JokeyDAO();
            /** output one jokey*/

            /*
            System.out.println(jokeyDAO.get(1));
            System.out.println("-------------------------------");
            */

            /** output all jokeys*/
            /*
            for (Jokey j: jokeyDAO.getJokeys()
                 ) {
                System.out.println(j);
            }
            */
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                if (con != null) con.close();
        }
    }

}

package racing;

import entity.Player;

import java.sql.*;

public class Solution {
    public final static String URL = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacing";
    // <version>9.2.0.jre15</version>
    public final static String USER = "RRA";
    public final static String PASSWORD = "rra";
    public static Connection con = null;
    public static int curIppo = 1;
    public static Date curDate = Date.valueOf("2021-01-08");

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

            PlayerActivity playerActivity = new PlayerActivity();
            Player player = null;
            player = playerActivity.registration();
            if (player != null){
                System.out.printf("You are registered " + player);
            }else
                System.out.println("You are not registered");

            playerActivity.setBet(player);

            //playerActivity.setBet(player);

            //PlayerDAO playerDAO = new PlayerDAO();
            //player = playerActivity.changeMyInfo();
            /** print and test that update was succefull*/
            //System.out.printf("You are updated to: " + playerDAO.lookFor(player));

            //PlayerDAO playerDAO = new PlayerDAO();
            /** output one player */
            //System.out.println(playerDAO.get(1));

            /** output all players */
            //for ( Player p: playerDAO.getPlayers()
            //     ) {
                //System.out.println(p);
            //}
            /** output one bets of one player id */
            //PlayerBetDAO playerBetDAO = new PlayerBetDAO();
            //    System.out.println(playerBetDAO.getBetsOfPlayerOnDateNumHorseBet(
            //            41,
            //            Date.valueOf("2021-01-08"),
            //            1,
            //            1,
            //            1));

            /** output all bets of player id */
            //playerBetDAO = new PlayerBetDAO();
            //for (PlayerBet  p:
            //     playerBetDAO.getBetsOfPlayerOnDate(Date.valueOf("2021-01-08"), 41)
            //) {
            //    System.out.println(p);
            //}
            /*
            JokeyDAO jokeyDAO = new JokeyDAO();
            */
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

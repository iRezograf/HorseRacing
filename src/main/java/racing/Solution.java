package racing;

import dao.PlayerBetDAO;
import dao.PlayerDAO;
import entity.BetsOfPlayer;
import entity.Player;

import java.sql.*;

public class Solution {
    public final static String URL = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacing";
    // <version>9.2.0.jre15</version>
    public final static String USER = "RRA";
    public final static String PASSWORD = "rra";
    public static Connection con = null;

    public static void main(String[] args) throws  Exception{
        try {
            con = DriverManager.getConnection(URL, USER,PASSWORD);


            PlayerActivity playerActivity = new PlayerActivity();
            Player player = null;
            //player = playerActivity.registration();
            //if (player != null){
            //    System.out.printf("You are registered: " + player);
            //}else
            //    System.out.println("You are not registered");

            PlayerDAO playerDAO = new PlayerDAO();
            player = playerActivity.changeMyInfo();
                    //playerDAO.update(player, (long) player.getId());
            /** print and test that update was succefull*/
            System.out.printf("You are updated to: " + playerDAO.lookFor(player));

            //PlayerDAO playerDAO = new PlayerDAO();
            /** output one player */
            //System.out.println(playerDAO.get((long) 1));

            /** output all players */
            //for ( Player p: playerDAO.getPlayers()
            //     ) {
                //System.out.println(p);
            //}
            /** output one bets of one player id */
            //PlayerBetDAO playerBetDAO = new PlayerBetDAO();
            //    System.out.println(playerBetDAO.getBetsOfPlayerOnDateNumHorseBet(
            //            (long)41,
            //            Date.valueOf("2021-01-08"),
            //            (long)1,
            //            (long)1,
            //            (long)1));

            /** output all bets of player id */
            //playerBetDAO = new PlayerBetDAO();
            //for (BetsOfPlayer  p:
            //     playerBetDAO.getBetsOfPlayerOnDate(Date.valueOf("2021-01-08"), (long) 41)
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

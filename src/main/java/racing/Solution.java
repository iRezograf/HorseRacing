package racing;

import dao.PlayerDAO;
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

            PlayerDAO playerDAO = new PlayerDAO();

            /** Вывод на экран одного игрока */
            System.out.println(playerDAO.get((long) 1));

            /** Вывод на экран всех игроков */
            for ( Player p: playerDAO.getPlayers()
                 ) {
                System.out.println(p);
            }

            /*
            JokeyDAO jokeyDAO = new JokeyDAO();
            */
            /** Вывод на экран одной строки с жокеем 3*//*

            System.out.println(jokeyDAO.get(1));
            System.out.println("-------------------------------");

            */
            /** Вывод на экран всех жоеев*//*

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

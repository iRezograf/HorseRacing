package entity;

import dao.JokeyDAO;
import dao.PlayerDAO;

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
            //con =DriverManager.getConnection(URL);
            /**
             * попытка подключиться к БД используя роли
             * con =DriverManager.getConnection(URL, "user_player","");
             * */

            PlayerDAO playerDAO = new PlayerDAO();
            System.out.println(playerDAO.get(1));

            for (BetsOfPlayer p: playerDAO.bets_of_player(42)
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

package racing;

import entity.Player;
import java.sql.*;

public class Solution {
    public final static String URL = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacing";
    public final static String USER = "RRA";
    public final static String PASSWORD = "rra";
    public static Connection con;
    static {
        try {
            con = DriverManager.getConnection(URL, USER,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static int curIppo = 1;
    public static Date curDate = Date.valueOf("2021-01-08");
    public static Player player = new Player();

    public static void main(String[] args) throws  Exception{
        try {
            con = DriverManager.getConnection(URL, USER,PASSWORD);

            MainMenu.mainMenu();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e);;
    }
        finally {
                if (con != null) con.close();
        }
    }

}


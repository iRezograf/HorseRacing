package db;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import dao.Jokey;
import dao.JokeyDAO;

import java.sql.*;

public class Solution {
    public final static String URL = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacing";
    // <version>9.2.0.jre15</version>
    public final static String USER = "RRA";
    public final static String PASSWORD = "rra";

    public static void main(String[] args) throws  Exception{
        JokeyDAO jokeyDAO = new JokeyDAO();
        /** Вывод на экран одной строки с жокеем 3*/
        System.out.println(jokeyDAO.get(3));
        System.out.println("-------------------------------");

        /** Вывод на экран всех жоеев*/
        for (Jokey j: jokeyDAO.getJokeys()
             ) {
            System.out.println(j);
        }

    }
}

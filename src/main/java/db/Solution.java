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
        //System.out.println("Hello, Maven!");
        /*String query = "SELECT id,name FROM ippo WHERE id > 0";
        Connection connection;

            Driver driver = new SQLServerDriver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            *//**st.execute("INSERT INTO HorseRacing.dbo.ippo ( name) VALUES ('bb')");*//*
            while (rs.next()){
                System.out.println(rs.getInt(1)+ " : "+rs.getString("name"));
            }
            rs.close();
            connection.close();*/
        JokeyDAO jokeyDAO = new JokeyDAO();
        jokeyDAO.get(3);
        for (Jokey j: jokeyDAO.getJokeys()
             ) {
            System.out.println(j);
        }

    }
}

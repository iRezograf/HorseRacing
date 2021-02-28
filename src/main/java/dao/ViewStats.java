package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static racing.Solution.con;

public  class ViewStats {
    public void viewJokeyWinner() {
        Statement statement;
        {
            try {
                statement = con.createStatement();
                ResultSet resultSet;
                String sql = "SELECT  TOP (10) PERCENT " +
                        "jokey.id AS jokey_id, " +
                        "jokey.name AS jokey, " +
                        "horse.name AS horse, " +
                        "ippo.name AS ippodrom, " +
                        "racing_map.date_ride, " +
                        "racing_map.distance, " + "" +
                        "racing_map.prize_place\n" +
                        "FROM            jokey INNER JOIN\n" +
                        "                racing_map ON jokey.id = racing_map.id_jokey INNER JOIN\n" +
                        "                horse ON racing_map.id_horse = horse.id INNER JOIN\n" +
                        "                ippo ON racing_map.id_ippo = ippo.id\n" +
                        "WHERE        (racing_map.prize_place = 1)\n" +
                        "ORDER BY jokey.id";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String jokeyName = resultSet.getString(2);
                    String horseName = resultSet.getString(3);
                    String ippoName = resultSet.getString(4);
                    Date date = resultSet.getDate(5);
                    int distance = resultSet.getInt(6);
                    int prizeP = resultSet.getInt(7);

                    System.out.println("ViewJokeyWinner: " + "\nid: " + id +
                            "\n jokeyName: " + jokeyName +
                            "\n horseName: " + horseName +
                            "\n ippoName: " + ippoName +
                            "\n date: " + date +
                            "\n distance:" + distance +
                            "\n prize place: " + prizeP +
                            "\n ");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

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

    public void viewHorseWinner() {
        Statement statement;
        {
            try {
                statement = con.createStatement();
                ResultSet resultSet;
                String sql = "SELECT  horse.id, \n" +
                        "        horse.name, \n" +
                        "        horse.birth, \n" +
                        "        horse.sex, \n" +
                        "        racing_map.id_ippo, \n" +
                        "        ippo.name AS Ippodrom, \n" +
                        "        stud.id AS id_stu, \n" +
                        "        stud.name AS Stud, \n" +
                        "        racing_map.date_ride, \n" +
                        "        racing_map.num_ride,\n" +
                        "        racing_map.prize_place\n" +
                        "FROM    horse INNER JOIN\n" +
                        "        racing_map ON horse.id = racing_map.id_horse INNER JOIN\n" +
                        "        stud ON horse.id_stud = stud.id INNER JOIN\n" +
                        "        ippo ON racing_map.id_ippo = ippo.id\n" +
                        "WHERE   (racing_map.prize_place = 1)";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String horseName = resultSet.getString(2);
                    Date date = resultSet.getDate(3);
                    String sex = resultSet.getString(4);
                    int ippo = resultSet.getInt(5);
                    String ippoName = resultSet.getString(6);
                    int stud = resultSet.getInt(7);
                    String studName = resultSet.getString(8);
                    Date dateRide = resultSet.getDate(9);
                    int numRide = resultSet.getInt(10);
                    int prizeP = resultSet.getInt(11);

                    System.out.println("ViewHorseWinner: " +
                            "\nhorseId: " + id +
                            "\n horseName: " + horseName +
                            "\n birthDay: " + date +
                            "\n sex: " + sex +
                            "\n ippoId: " + ippo +
                            "\n ippodrome: " + ippoName +
                            "\n studId: " + stud +
                            "\n studName: " + studName +
                            "\n dateRide: " + dateRide +
                            "\n numRide:" + numRide +
                            "\n prize place: " + prizeP +
                            "\n ");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void viewTypeBets() {
        Statement statement;
        {
            try {
                statement = con.createStatement();
                ResultSet resultSet;
                String sql = "SELECT id " +
                        "      ,type_bet " +
                        "      ,rate " +
                        "  FROM type_bet";
                resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String nameBet = resultSet.getString(2);
                    int rate = resultSet.getInt(3);

                    System.out.println("ViewTypeBets: " + "\nid: " + id +
                            "\n Type of Bet: " + nameBet +
                            "\n Rate: " + rate +
                            "\n ");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


}

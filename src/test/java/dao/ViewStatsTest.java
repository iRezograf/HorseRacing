package dao;

import entity.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import racing.Solution;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;
import static racing.Solution.con;

public class ViewStatsTest {
    Coach coach;
    Horse horse;
    Ippo ippo;
    Jokey jokey;
    Player player;

    PlayerBet playerBet;
    PlayerBet actualPlayerBet;

    RacingMap racingMap;
    Stud stud;
    TypeBet typeBet;

    List<PlayerBet> playerBets;

    CoachDAO coachDAO;
    HorseDAO horseDAO;
    IppoDAO ippoDAO;
    JokeyDAO jokeyDAO;
    PlayerDAO playerDAO;
    PlayerBetDAO playerBetDAO;
    RacingMapDAO racingMapDAO;
    StudDAO StudDAO;
    TypeBetDAO typeBetDAO;

    @BeforeMethod(groups = {"viewStats"})
    public void setUp() throws SQLException {
        String url = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacingTest";
        String user = "RRA";
        String password = "rra";
        Solution.con = DriverManager.getConnection(url, user, password);
        String sql =  "DELETE TOP(10) FROM horse";
        PreparedStatement ps = Solution.con.prepareStatement(sql);
        ps.executeUpdate();

        sql =  "DELETE TOP(10) FROM coach";
        ps = Solution.con.prepareStatement(sql);
        ps.executeUpdate();

        sql =  "DELETE TOP(10) FROM ippo";
        ps = Solution.con.prepareStatement(sql);
        ps.executeUpdate();

        sql =  "DELETE TOP(10) FROM jokey";
        ps = Solution.con.prepareStatement(sql);
        ps.executeUpdate();

        sql =  "DELETE TOP(10) FROM player";
        ps = Solution.con.prepareStatement(sql);
        ps.executeUpdate();

        sql =  "DELETE TOP(10) FROM player_bet";
        ps = Solution.con.prepareStatement(sql);
        ps.executeUpdate();

        sql =  "DELETE TOP(10) FROM racing_map";
        ps = Solution.con.prepareStatement(sql);
        ps.executeUpdate();

        sql =  "DELETE TOP(10) FROM stud";
        ps = Solution.con.prepareStatement(sql);
        ps.executeUpdate();

        sql =  "DELETE TOP(10) FROM type_bet";
        ps = Solution.con.prepareStatement(sql);
        ps.executeUpdate();

        player = new Player();
        player.setLogin("testLogin");
        player.setPassword("TestPassword");
        player.setFirstName("TestFirstName");
        player.setLastName("TestLastName");
        playerDAO = new PlayerDAO();
        playerDAO.save(player);
        /** player.id */
        player = playerDAO.lookFor("testLogin", "TestPassword");

        ippo = new Ippo();
        ippo.setName("TestIppodromeName");
        ippoDAO = new IppoDAO();
        ippoDAO.save(ippo);
        /** ippo.id */
        ippo = ippoDAO.lookFor(ippo);

        stud = new Stud();
        stud.setName("TestStudName");
        StudDAO = new StudDAO();
        StudDAO.save(stud);
        /** stud.id */
        stud = StudDAO.lookFor(stud);

        horse = new Horse();
        horse.setIdStud(stud.getId());
        horse.setName("TestHorseName");
        horse.setSex("Test:Жеребец");
        horse.setBirth(Date.valueOf("2021-01-08"));
        horseDAO = new HorseDAO();
        horse = (Horse) horseDAO.save(horse);
        /** horse.id*/
        horse = horseDAO.lookFor(horse);

        typeBet = new TypeBet();
        typeBet.setTypeBet("TestTypeBet");
        typeBet.setRate(99.9);
        typeBetDAO = new TypeBetDAO();
        typeBetDAO.save(typeBet);
        /** typeBet.id*/
        typeBet = typeBetDAO.lookFor(typeBet);

        jokey = new Jokey();
        jokey.setName("TestJokeName");
        jokeyDAO = new JokeyDAO();
        jokeyDAO.save(jokey);
        /** jokey.id*/
        jokey = jokeyDAO.lookFor(jokey);

        coach = new Coach();
        coach.setName("TestCoachName");
        coachDAO = new CoachDAO();
        coachDAO.save(coach);
        /** coach.id */
        coach = coachDAO.lookFor(coach);

        racingMap = new RacingMap();
        racingMap.setId_ippodrom(ippo.getId());
        racingMap.setDate_ride(Date.valueOf("2021-01-08"));
        racingMap.setNum_ride(1);
        racingMap.setId_horse(horse.getId());
        racingMap.setId_jokey(jokey.getId());
        racingMap.setId_coach(coach.getId());
        racingMapDAO = new RacingMapDAO();
        /** Create values for Primary key*/
        racingMapDAO.save(racingMap);

        actualPlayerBet = new PlayerBet();
        playerBet       = new PlayerBet();
        playerBet.setId(player.getId());
        playerBet.setIdIppodrom(ippo.getId());
        playerBet.setDateRide(Date.valueOf("2021-01-08"));
        playerBet.setNumRide(1);
        playerBet.setIdHorse(horse.getId());
        playerBet.setIdTypeBet(typeBet.getId());
        playerBet.setBet(999);
        playerBet.setPayout(1999);

        playerBetDAO = new PlayerBetDAO();
        playerBets = new ArrayList<>();
    }


    @AfterMethod(groups = {"viewStats"})
    public void tearDown() {
    }

    @Test(groups = {"viewStats"}, priority = 102)
    public void testViewJokeyWinnerNotFirstPlace() throws SQLException {
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
                    Assert.assertNotEquals(prizeP,2);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Test(groups = {"viewStats"}, priority = 103)
    public void testViewHorseWinnerNotFirstPlace() {
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
                    Assert.assertNotEquals(prizeP, 2);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

        @Test(groups = {"viewStats"}, priority = 104)
        public void testViewHorseWinnerHorseNameEqual() {
            Statement statement;
            String horseName = null;
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
                        horseName = resultSet.getString(2);
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
                Assert.assertEquals(horseName,horse.getName());
            }

    }

    @Test(groups = {"viewStats"}, priority = 105)
    public void testViewTypeBetsIsNotNull() {
        Statement statement;
        ResultSet resultSet = null;
        {
            try {
                statement = con.createStatement();
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
            Assert.assertNotNull(resultSet);
        }
    }

    @Test(groups = {"viewStats"}, priority = 105)
    public void testViewTypeBetsIsEqualRate() {
        Statement statement;
        double rate = 0;
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
                    rate = resultSet.getDouble(3);

                    System.out.println("ViewTypeBets: " + "\nid: " + id +
                            "\n Type of Bet: " + nameBet +
                            "\n Rate: " + rate +
                            "\n ");
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Assert.assertEquals(rate, typeBet.getRate());
        }
    }

}



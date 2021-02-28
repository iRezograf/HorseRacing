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


public class PlayerBetDAOTest {

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

    @BeforeMethod(groups = {"playerBet"})
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


    @Test(groups = {"playerBet"}, priority = 80)
    public void testSave() throws SQLException {
        playerBet = playerBetDAO.save(playerBet);
        Assert.assertEquals(playerBet.getPayout(), 1999, "Сумма платежа совпадает");
    }

    @Test(groups = {"playerBet"}, priority = 82)
    public void testGetPlayerBet() {
        actualPlayerBet = playerBetDAO.remove(playerBet);
        actualPlayerBet = playerBetDAO.save(playerBet);
        actualPlayerBet = playerBetDAO.getPlayerBet(actualPlayerBet);
        Assert.assertEquals(actualPlayerBet.getHorse(), horse.getName(),"Names Are Equals and is: "+ playerBet.getHorse());
    }


    @Test(groups = {"playerBet"}, priority = 84)
    public void testGetPlayerBets() {
        actualPlayerBet = playerBetDAO.remove(playerBet);
        actualPlayerBet = playerBetDAO.save(playerBet);
        actualPlayerBet = playerBetDAO.getPlayerBet(playerBet);
        playerBets = playerBetDAO.getPlayerBets(actualPlayerBet);
        actualPlayerBet = playerBets.get(0);
        Assert.assertEquals(actualPlayerBet.getHorse(), horse.getName(),"Names Are Equals and is: "+ playerBet.getHorse());
    }

    @Test(groups = {"playerBet"}, priority = 86)
    public void testUpdate() {
        actualPlayerBet = playerBetDAO.remove(playerBet);
        actualPlayerBet = playerBetDAO.save(playerBet);
        actualPlayerBet = playerBetDAO.getPlayerBet(playerBet);
        playerBet.setBet(100);
        playerBet.setPayout(1000);
        actualPlayerBet = playerBetDAO.update(playerBet);
        actualPlayerBet = playerBetDAO.getPlayerBet(playerBet);
        Assert.assertEquals(actualPlayerBet.getBet(), 100);
    }

    @Test(groups = {"playerBet"}, priority = 88)
    public void testRemove() {
        actualPlayerBet = playerBetDAO.remove(playerBet);
        actualPlayerBet = playerBetDAO.save(playerBet);
        actualPlayerBet = playerBetDAO.getPlayerBet(playerBet);
        playerBet.setBet(100);
        playerBet.setPayout(1000);
        actualPlayerBet = playerBetDAO.remove(playerBet);
        Assert.assertNull(actualPlayerBet);
    }

    @AfterMethod(groups = {"playerBet"})
    public void tearDown() {
        coach = null;
        horse = null;
        ippo = null;
        jokey = null;
        player = null;

        playerBet = null;
        actualPlayerBet = null;

        racingMap = null;
        stud = null;
        typeBet = null;

        playerBets = null;

        coachDAO = null;
        horseDAO = null;
        ippoDAO = null;
        jokeyDAO = null;
        playerDAO  = null;
        playerBetDAO = null;
        racingMapDAO  = null;
        StudDAO = null;
        typeBetDAO = null;
    }
}
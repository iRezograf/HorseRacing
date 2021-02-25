package dao;

import dao.interfaces.IStudDAO;
import entity.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import racing.Solution;

import java.sql.*;

import static org.testng.Assert.*;
import static racing.Solution.con;

public class PlayerBetDAOTest {

    private Player player;
    private Ippo ippo;
    private Horse horse;
    private Stud stud;
    private TypeBet typeBet;
    private PlayerBet playerBet;
    private Coach coach;
    private Jokey jokey;
    private RacingMap racingMap;

    private PlayerDAO playerDAO;
    private IppoDAO ippoDAO;
    private StudDAO StudDAO;
    private HorseDAO horseDAO;
    private TypeBetDAO typeBetDAO;
    private PlayerBetDAO playerBetDAO;
    private CoachDAO coachDAO;
    private JokeyDAO jokeyDAO;
    private RacingMapDAO racingMapDAO;
    private int id;

    @BeforeMethod
    public void setUp() throws SQLException {
        String url = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacingTest";
        String user = "RRA";
        String password = "rra";
        //Connection con = null;
        Solution solution = new Solution();
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
    }


    @Test(groups = {"playerBet"}, priority = 80)
    public void testSave() throws SQLException {
        setUp();
        playerBet = new PlayerBet();
        playerBet.setId(player.getId());
        playerBet.setIdIppodrom(ippo.getId());
        playerBet.setDateRide(Date.valueOf("2021-01-08"));
        playerBet.setNumRide(1);
        playerBet.setIdHorse(horse.getId());
        playerBet.setIdTypeBet(typeBet.getId());
        playerBet.setBet(999);
        playerBet.setPayout(1999);

        playerBetDAO = new PlayerBetDAO();
        playerBet = playerBetDAO.save(playerBet);
        Assert.assertEquals(playerBet.getPayout(), 1999, "Сумма платежа совпадает");
    }

    @Test(groups = {"playerBet"}, priority = 82)
    public void testGetPlayerBet() {
        playerBet = new PlayerBet();
        playerBet.setId(player.getId());
        playerBet.setFirstName(player.getFirstName());
        playerBet.setLastName(player.getLastName());
        playerBet.setIdIppodrom(ippo.getId());
        playerBet.setIppodromName(ippo.getName());
        playerBet.setDateRide(Date.valueOf("2021-01-08"));
        playerBet.setNumRide(1);
        playerBet.setIdHorse(horse.getId());
        playerBet.setHorse(horse.getName());
        playerBet.setIdTypeBet(typeBet.getId());
        playerBet.setTypeBet(typeBet.getTypeBet());
        playerBet.setBet(999);
        playerBet.setPayout(1999);

        playerBetDAO = new PlayerBetDAO();

        playerBet = playerBetDAO.save(playerBet);
        playerBet = playerBetDAO.getPlayerBet(playerBet);
        Assert.assertEquals(playerBet.getHorse(), horse.getName(),"Names Are Equals and is: "+ playerBet.getHorse());
    }


    @Test(groups = {"playerBet"}, priority = 84)
    public void testGetPlayerBets() {
    }

    @Test(groups = {"playerBet"}, priority = 86)
    public void testUpdate() {
    }

    @Test(groups = {"playerBet"}, priority = 88)
    public void testRemove() {
    }
    @AfterMethod
    public void tearDown() {
        player = null;
        ippo = null;
        horse = null;
        stud = null;
        typeBet = null;
        playerBet = null;
        coach = null;
        jokey = null;
        racingMap = null;

        playerDAO = null;
        ippoDAO = null;
        StudDAO = null;
        horseDAO = null;
        typeBetDAO = null;
        playerBetDAO = null;
        coachDAO = null;
        jokeyDAO = null;
        racingMapDAO = null;
    }

}
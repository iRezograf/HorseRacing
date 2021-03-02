package racing;

import dao.*;
import entity.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;
import static racing.Solution.*;

public class PlayerActivityTest {
    Coach coach;
    Horse horse;
    Ippo ippo;
    Jokey jokey;
    Player player;
    Player playerIn;

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

    @BeforeMethod(groups = {"playerActivity"})
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


        playerIn = new Player();
        player = new Player();
        Solution.player = new Player();
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


    @Test(groups = {"playerActivity"}, priority = 200)
    public void testLoginNotNull() {
/*
        player.setLogin("testLogin");
        player.setPassword("TestPassword");
        player.setFirstName("TestFirstName");
        player.setLastName("TestLastName");
*/

        Solution.player = playerDAO.lookFor("testLogin", "TestPassword");
        if (Solution.player != null){
            System.out.println("You are logged !");
            System.out.println("----------------");
        }

        Assert.assertNotNull(Solution.player);
    }

    @Test(groups = {"playerActivity"}, priority = 201)
    public void testLoginSetStaticPlayer() {
        PlayerDAO playerDAO = new PlayerDAO();
        Solution.player = playerDAO.lookFor("testLogin", "TestPassword");
        if (Solution.player != null){
            System.out.println("You are logged !");
            System.out.println("----------------");
        }
        System.out.println(Solution.player.getLogin());
        Assert.assertEquals(Solution.player.getLogin(),"testLogin");
    }

    @Test(groups = {"playerActivity"}, priority = 202)
    public void testLoginNotFound() {
        PlayerDAO playerDAO = new PlayerDAO();
        Solution.player = playerDAO.lookFor("testLogin NOT", "TestPassword");
        if (Solution.player != null){
            System.out.println("You are logged !");
            System.out.println("----------------");
        } else
        {
            System.out.println("You are NOT logged !");
            System.out.println("----------------");

        }
        Assert.assertNull(Solution.player);
    }

    @Test(groups = {"playerActivity"}, priority = 203)
    public void testFirstRegistration() {
        PlayerDAO playerDAO = new PlayerDAO();
        Player playerIn = new Player();
        Solution.player = new Player();
        playerIn.setLogin("testLogin");
        playerIn.setPassword("TestPassword");
        playerIn.setFirstName("TestFirstName");
        playerIn.setLastName("TestLastName");

        Solution.player = playerDAO.lookFor(playerIn);
        if (Solution.player == null){
            Solution.player = playerDAO.save(playerIn);
            Solution.player = playerDAO.lookFor(playerIn);
        } else
        {
            System.out.println("You are already registered ! You should login");
            Assert.assertNotNull(Solution.player);
        }
        Assert.assertEquals(Solution.player.getLogin(),playerIn.getLogin(),"registation successfull");
    }

    @Test(groups = {"playerActivity"}, priority = 207)
    public void testChangeMyInfo() {
        //PlayerDAO playerDAO = new PlayerDAO();
        //Player playerIn = new Player();
        //Solution.player = playerDAO.lookFor("testLogin", "TestPassword");
        playerIn.setId(Solution.player.getId());
        playerIn.setLogin("testLogin");
        playerIn.setPassword("TestPassword");
        playerIn.setFirstName("TestFirstNameChange");
        playerIn.setLastName("TestLastName");
        playerIn = playerDAO.update(playerIn);
        Solution.player = playerDAO.lookFor(playerIn);
        Assert.assertEquals(Solution.player.getFirstName(),playerIn.getFirstName());
    }

    @Test(groups = {"playerActivity"}, priority = 209)
    public void testSetBet() {
        //playerBet = new PlayerBet();
        //playerDAO = new PlayerDAO();
        //player = playerDAO.lookFor("testLogin", "TestPassword");

        playerBet.setId(Solution.player.getId());
        playerBet.setIdIppodrom(racingMap.getId_ippodrom());
        playerBet.setDateRide(racingMap.getDate_ride()); //Date.valueOf("2021-01-08"));
        playerBet.setNumRide(racingMap.getNum_ride());
        playerBet.setIdHorse(racingMap.getId_horse());
        playerBet.setIdTypeBet(typeBet.getId());

        /** Let's look at bet made by player before*/
        if (playerBetDAO.getPlayerBet(playerBet) == null){
            /** Change values of Bet and insert new if not found */
            playerBet.setIdTypeBet(typeBet.getId());
            playerBet.setBet(900);
            playerBetDAO.save(playerBet);
            playerBet = playerBetDAO.getPlayerBet(playerBet);
        } else {
            /** Change values of Bet and update if found */
            playerBet.setIdTypeBet(typeBet.getId());
            playerBet.setBet(1000);
            playerBetDAO.update(playerBet);
            playerBet = playerBetDAO.getPlayerBet(playerBet);
        }
        Assert.assertEquals(playerBet.getRate(), typeBet.getRate());
    }

    @Test(groups = {"playerActivity"}, priority = 210)
    public void testChangeBet() {
        player = playerDAO.lookFor("testLogin", "TestPassword");
        playerBet.setId(Solution.player.getId());
        playerBet.setIdIppodrom(racingMap.getId_ippodrom());
        playerBet.setDateRide(racingMap.getDate_ride()); //Date.valueOf("2021-01-08"));
        playerBet.setNumRide(racingMap.getNum_ride());
        playerBet.setIdHorse(racingMap.getId_horse());
        playerBet.setIdTypeBet(typeBet.getId());

        /** Let's look at bet made by player before*/
        if (playerBetDAO.getPlayerBet(playerBet) == null){
            /** Change values of Bet and insert new if not found */
            playerBet.setIdTypeBet(typeBet.getId());
            playerBet.setBet(900);
            playerBetDAO.save(playerBet);
            playerBet = playerBetDAO.getPlayerBet(playerBet);

            playerBet.setIdTypeBet(typeBet.getId());
            playerBet.setBet(1000);
            playerBetDAO.update(playerBet);
            playerBet = playerBetDAO.getPlayerBet(playerBet);
        }
        Assert.assertEquals(playerBet.getBet(), 1000);
    }


    @Test(groups = {"playerActivity"}, priority = 211)
    public void testShowRacingMap() {
    }

    @Test(groups = {"playerActivity"}, priority = 213)
    public void testShowBetsOfPlayer() {
    }

    @AfterMethod(groups = {"playerActivity"})
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
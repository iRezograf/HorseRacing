package dao;

import entity.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import racing.Solution;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class RacingMapDAOTest {
    Player player;
    Ippo ippo;
    Horse horse;
    Stud stud;
    TypeBet typeBet;
    PlayerBet playerBet;
    Coach coach;
    Jokey jokey;
    RacingMap racingMap;
    RacingMap actualRacingMap;
    List<RacingMap> racingMaps;

    PlayerDAO playerDAO;
    IppoDAO ippoDAO;
    StudDAO StudDAO;
    HorseDAO horseDAO;
    TypeBetDAO typeBetDAO;
    PlayerBetDAO playerBetDAO;
    CoachDAO coachDAO;
    JokeyDAO jokeyDAO;
    RacingMapDAO racingMapDAO;

    @BeforeMethod
    public void setUp() throws SQLException {
        String url = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacingTest";
        String user = "RRA";
        String password = "rra";
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


        actualRacingMap = new RacingMap();

/**
        racingMap = new RacingMap();
        racingMap.setId_ippodrom(ippo.getId());
        racingMap.setDate_ride(Date.valueOf("2021-01-08"));
        racingMap.setNum_ride(1);
        racingMap.setId_horse(horse.getId());
        racingMap.setId_jokey(jokey.getId());
        racingMap.setId_coach(coach.getId());

        racingMap.setIppodromName(ippo.getName());
        racingMap.setHorseName(horse.getName());
        racingMap.setBirth(horse.getBirth());
        racingMap.setSex(horse.getSex());
        racingMap.setJokeyName(jokey.getName());
        racingMap.setCoachName(coach.getName());
        racingMap.setLast_ride(horse.getBirth());
        System.out.println(racingMap);
        racingMapDAO = new RacingMapDAO();
*/
        //actualRacingMap = racingMapDAO.save(racingMap);

        racingMaps = new ArrayList<>();
    }


    @Test(groups = {"racingMap"}, priority = 92)
    public void testGetRacingMaps() {
        //actualRacingMap = racingMapDAO.save(racingMap);
        System.out.println(racingMap);
        racingMaps = racingMapDAO.getRacingMaps(racingMap);
        actualRacingMap = racingMaps.get(0);
        Assert.assertEquals(actualRacingMap.getHorseName(), horse.getName());
    }

    @Test(groups = {"racingMap"}, priority = 94)
    public void testSave() {
    }

    @Test(groups = {"racingMap"}, priority = 96)
    public void testRemove() {
    }

    @Test(groups = {"racingMap"}, priority = 98)
    public void testUpdate() {
    }
    @AfterMethod
    public void tearDown() {
    }
}
package dao;

import entity.Horse;
import entity.Ippo;
import entity.Stud;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import racing.Solution;

import java.sql.*;

import static org.testng.Assert.*;

public class HorseDAOTest {

    StudDAO studDAO;
    Stud stud;
    Stud actualStud;

    HorseDAO horseDAO;
    Horse horse;
    Horse actualHorse;

    @BeforeMethod(groups = {"horse"})
    public void setUp() throws SQLException {
        String url = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacingTest";
        String user = "RRA";
        String password = "rra";
        Solution solution = new Solution();
        Solution.con = DriverManager.getConnection(url, user, password);
        String sql =  "DELETE TOP(10) FROM horse";
        PreparedStatement ps = Solution.con.prepareStatement(sql);
        ps.executeUpdate();

        studDAO = new StudDAO();
        stud = new Stud();
        stud.setName("TestStudName");
        actualStud = new Stud();

        stud.setName("NameStud");
        studDAO.save(stud);
        actualStud = studDAO.lookFor(stud);

        horseDAO = new HorseDAO();
        horse = new Horse();
        horse.setName("HorseName");
        horse.setBirth(Date.valueOf("2021-01-08"));
        horse.setSex("Жеребец");
        horse.setIdStud(actualStud.getId());
        actualHorse = new Horse();
    }

    @Test(groups = {"horse"}, priority = 40)
    public void testSave()  {

        horse.setName("TestSave");
        actualHorse = (Horse) horseDAO.save(horse);
        Assert.assertEquals(actualHorse.getName(), "TestSave");
    }

    @Test (groups = {"horse"}, priority = 41)
    public void testLookFor() {
        horse.setName("Test_LookFor");

        actualHorse = (Horse) horseDAO.save(horse);
        actualHorse = horseDAO.lookFor(horse);
        Assert.assertEquals(actualHorse.getName(), "Test_LookFor");
    }

    @Test(groups = {"horse"}, priority = 42)
    public void testRemove() {
        horse.setName("TestRemove");
        actualHorse = (Horse) horseDAO.save(horse);
        actualHorse = horseDAO.lookFor(horse);
        actualHorse = (Horse) horseDAO.remove(horse);
        Assert.assertNull(actualHorse);
    }

    @Test(groups = {"horse"}, priority = 43)
    public void testUpdate() {
        horse.setName("TestUpdate_before");
        actualHorse = (Horse) horseDAO.save(horse);
        actualHorse = horseDAO.lookFor(horse);
        horse.setName("TestUpdate_After");
        actualHorse = (Horse) horseDAO.update(horse);
        Assert.assertEquals(actualHorse.getName(), "TestUpdate_After");
    }

    @Test(groups = {"horse"}, priority = 44)
    public void testGet() {
        //horse.setName("Test_get");
        actualHorse = (Horse) horseDAO.save(horse);
        actualHorse = horseDAO.lookFor(horse);
        actualHorse = horseDAO.get(actualHorse.getId());
        /**horseDAO.remove(horse);*/
        Assert.assertEquals(actualHorse.getName(), horse.getName());
    }

    @AfterMethod(groups = {"horse"})
    public void tearDown() {
        horse = null;
        actualHorse = null;
        horseDAO = null;
        Solution.con = null;
    }
}
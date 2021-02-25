package dao;

import entity.Horse;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import racing.Solution;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.testng.Assert.*;

public class HorseDAOTest {

    private HorseDAO horseDAO;
    private Horse horse;
    private Horse actualHorse;
    private int id;

    @BeforeMethod(groups = {"horse"})
    public void setUp() throws SQLException {
        String url = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacingTest";
        String user = "RRA";
        String password = "rra";
        Connection con;
        Solution solution = new Solution();
        Solution.con = DriverManager.getConnection(url, user, password);
        StudDAOTest studDAOTest = new StudDAOTest();
        studDAOTest.testSave();
        studDAOTest.testLookFor();
    }

    @Test(groups = {"horse"}, priority = 40)
    public void testSave()  {

        horseDAO = new HorseDAO();
        horse = new Horse();
        horse.setName("Test");
        horse.setBirth(Date.valueOf("2021-01-08"));
        horse.setSex("Жеребец");
        horse.setIdStud(1);
        actualHorse = (Horse) horseDAO.save(horse);
        Assert.assertEquals(actualHorse.getName(), "Test");
    }

    @Test (groups = {"horse"}, priority = 41)
    public void testLookFor() {
        horseDAO = new HorseDAO();
        horse = new Horse();
        horse.setName("Test");
        horse.setBirth(Date.valueOf("2021-01-08"));
        horse.setSex("Жеребец");
        actualHorse = horseDAO.lookFor(horse);
        id = actualHorse.getId();
        Assert.assertEquals(actualHorse.getName(), "Test");
    }

    @Test(groups = {"horse"}, priority = 42)
    public void testRemove() {
        horseDAO = new HorseDAO();
        horse = new Horse();
        horse.setName("Test");
        horse.setBirth(Date.valueOf("2021-01-08"));
        horse.setSex("Жеребец");
        horse.setIdStud(1);
        actualHorse = horseDAO.lookFor(horse);
        id = actualHorse.getId();
        horse.setId(id);
        actualHorse = (Horse) horseDAO.remove(horse);
        Assert.assertNull(actualHorse);
    }

    @Test(groups = {"horse"}, priority = 43)
    public void testUpdate() {
        horseDAO = new HorseDAO();
        horse = new Horse();
        horse.setName("Test");
        horse.setBirth(Date.valueOf("2021-01-08"));
        horse.setSex("Жеребец");
        horse.setIdStud(1);
        horseDAO.save(horse);
        actualHorse = horseDAO.lookFor(horse);
        id = actualHorse.getId();
        horse.setId(id);
        horse.setName("TestUpdate");
        actualHorse = (Horse) horseDAO.update(horse);
        Assert.assertEquals(actualHorse.getName(), "TestUpdate");
    }

    @Test(groups = {"horse"}, priority = 44)
    public void testGet() {
        horseDAO = new HorseDAO();
        actualHorse = horseDAO.get(id);
        /**horseDAO.remove(horse);*/
        Assert.assertEquals(actualHorse.getName(), "TestUpdate");
    }

    @AfterMethod(groups = {"horse"})
    public void tearDown() {
        horse = null;
        actualHorse = null;
        horseDAO = null;
        Solution.con = null;
    }
}
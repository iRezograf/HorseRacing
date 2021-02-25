package dao;

import entity.Coach;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import racing.Solution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.testng.Assert.*;

public class CoachDAOTest {
    private CoachDAO coachDAO;
    private Coach coach;
    private Coach actualCoach;
    private int id;
    @BeforeMethod(groups = {"coach"})
    public void setUp() throws SQLException {
        String url = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacingTest";
        String user = "RRA";
        String password = "rra";
        Connection con;
        Solution solution = new Solution();
        Solution.con = DriverManager.getConnection(url, user, password);
    }

    @Test(groups = {"coach"}, priority = 0)
    public void testSave()  {
        coachDAO = new CoachDAO();
        coach = new Coach();
        coach.setName("Test");
        actualCoach = (Coach) coachDAO.save(coach);
        Assert.assertEquals(actualCoach.getName(), "Test");
    }

    @Test (groups = {"coach"}, priority = 1)
    public void testLookFor() {
        coachDAO = new CoachDAO();
        coach = new Coach();
        coach.setName("Test");
        actualCoach = coachDAO.lookFor(coach);
        id = actualCoach.getId();
        Assert.assertEquals(actualCoach.getName(), "Test");
    }

    @Test(groups = {"coach"}, priority = 2)
    public void testRemove() {
        coachDAO = new CoachDAO();
        coach = new Coach();
        coach.setName("Test");
        actualCoach = coachDAO.lookFor(coach);
        id = actualCoach.getId();
        coach.setId(id);
        actualCoach = (Coach) coachDAO.remove(coach);
        Assert.assertNull(actualCoach);
    }

    @Test(groups = {"coach"}, priority = 3)
    public void testUpdate() {
        coachDAO = new CoachDAO();
        coach = new Coach();
        coach.setName("Test");
        coachDAO.save(coach);
        actualCoach = coachDAO.lookFor(coach);
        id = actualCoach.getId();
        coach.setId(id);
        coach.setName("TestUpdate");
        actualCoach = (Coach) coachDAO.update(coach);
        Assert.assertEquals(actualCoach.getName(), "TestUpdate");
    }

    @Test(groups = {"coach"}, priority = 4)
    public void testGet() {
        coachDAO = new CoachDAO();
        actualCoach = coachDAO.get(id);
        /**coachDAO.remove(coach);*/
        Assert.assertEquals(actualCoach.getName(), "TestUpdate");
    }

    @AfterMethod(groups = {"coach"})
    public void tearDown() {
        coach = null;
        actualCoach = null;
        coachDAO = null;
        Solution.con = null;
    }
}
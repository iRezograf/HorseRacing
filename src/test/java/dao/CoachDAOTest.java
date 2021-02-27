package dao;

import entity.Coach;
import entity.Horse;
import entity.Stud;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import racing.Solution;

import java.sql.*;

import static org.testng.Assert.*;

public class CoachDAOTest {
    CoachDAO coachDAO;
    Coach coach;
    Coach actualCoach;


    @BeforeMethod(groups = {"coach"})
    public void setUp() throws SQLException {
        String url = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacingTest";
        String user = "RRA";
        String password = "rra";
        Solution solution = new Solution();
        Solution.con = DriverManager.getConnection(url, user, password);
        String sql =  "DELETE TOP(10) FROM coach";
        PreparedStatement ps = Solution.con.prepareStatement(sql);
        ps.executeUpdate();

        coachDAO = new CoachDAO();
        coach = new Coach();
        coach.setName("CoachName");
        actualCoach = new Coach();

    }

    @Test(groups = {"coach"}, priority = 50)
    public void testSave()  {
        /** CoachName */
        actualCoach = (Coach) coachDAO.save(coach);
        Assert.assertEquals(actualCoach.getName(), "CoachName");
    }

    @Test (groups = {"coach"}, priority = 51)
    public void testLookFor() {
        actualCoach = (Coach) coachDAO.save(coach);
        actualCoach = coachDAO.lookFor(coach);
        Assert.assertEquals(actualCoach.getName(), "CoachName");
    }

    @Test(groups = {"coach"}, priority = 52)
    public void testRemove() {
        actualCoach = (Coach) coachDAO.save(coach);
        actualCoach = coachDAO.lookFor(coach);
        actualCoach = (Coach) coachDAO.remove(coach);
        Assert.assertNull(actualCoach);
    }

    @Test(groups = {"coach"}, priority = 53)
    public void testUpdate() {
        coachDAO.save(coach);
        actualCoach = coachDAO.lookFor(coach);
        coach.setName("TestNameUpdate");
        actualCoach = (Coach) coachDAO.update(coach);
        Assert.assertEquals(actualCoach.getName(), "TestNameUpdate");
    }

    @Test(groups = {"coach"}, priority = 54)
    public void testGet() {
        coachDAO.save(coach);
        actualCoach = coachDAO.lookFor(coach);
        actualCoach = (Coach) coachDAO.get(actualCoach.getId());
        Assert.assertEquals(actualCoach.getName(), coach.getName());
    }

    @AfterMethod(groups = {"coach"})
    public void tearDown() {
        coach = null;
        actualCoach = null;
        coachDAO = null;
        Solution.con = null;
    }
}
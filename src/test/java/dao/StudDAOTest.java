package dao;

import entity.Player;
import entity.Stud;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import racing.Solution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.testng.Assert.*;

public class StudDAOTest {

    StudDAO studDAO;
    Stud stud;
    Stud actualStud;

    @BeforeMethod(groups = {"stud"})
    public void setUp() throws SQLException {
        String url = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacingTest";
        String user = "RRA";
        String password = "rra";
        Solution solution = new Solution();
        Solution.con = DriverManager.getConnection(url, user, password);
        String sql =  "DELETE TOP(10) FROM stud";
        PreparedStatement ps = Solution.con.prepareStatement(sql);
        ps.executeUpdate();
        studDAO = new StudDAO();
        stud = new Stud();
        stud.setName("TestStudName");
        actualStud = new Stud();
    }

    @Test(groups = {"stud"}, priority = 30)
    public void testSave()  {

        actualStud = (Stud) studDAO.save(stud);
        Assert.assertEquals(actualStud.getName(), "TestStudName");
    }

    @Test (groups = {"stud"}, priority = 31)
    public void testLookFor() {
        stud.setName("Test_testLookFor");
        actualStud = (Stud) studDAO.save(stud);
        actualStud = studDAO.lookFor(actualStud);
        Assert.assertEquals(actualStud.getName(), "Test_testLookFor");
    }

    @Test(groups = {"stud"}, priority = 32)
    public void testRemove() {
        stud.setName("Test_testRemove");
        actualStud = (Stud) studDAO.save(stud);
        actualStud = studDAO.lookFor(actualStud);
        stud.setId(actualStud.getId());
        actualStud = (Stud) studDAO.remove(stud);
        Assert.assertNull(actualStud);
    }

    @Test(groups = {"stud"}, priority = 33)
    public void testUpdate() {
        stud.setName("Test_testUpdate");
        studDAO.save(stud);
        actualStud = studDAO.lookFor(stud);
        stud.setId(actualStud.getId());
        stud.setName("Test_testUpdate_TestUpdate");
        actualStud = (Stud) studDAO.update(stud);
        Assert.assertEquals(actualStud.getName(), "Test_testUpdate_TestUpdate");
    }

    @Test(groups = {"stud"}, priority = 34)
    public void testGet() {
        stud.setName("Test_testGet");
        studDAO.save(stud);
        actualStud = studDAO.lookFor(stud);
        actualStud = studDAO.get(actualStud.getId());
        Assert.assertEquals(actualStud.getName(), "Test_testGet");
    }

    @AfterMethod(groups = {"stud"})
    public void tearDown() {
        stud = null;
        actualStud = null;
        studDAO = null;
        Solution.con = null;
    }
}

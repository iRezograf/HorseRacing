package dao;

import entity.Stud;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import racing.Solution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.testng.Assert.*;

public class StudDAOTest {

    private StudDAO studDAO;
    private Stud stud;
    private Stud actualStud;
    private int id;

    @BeforeMethod(groups = {"stud"})
    public void setUp() throws SQLException {
        String url = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacingTest";
        String user = "RRA";
        String password = "rra";
        Connection con;
        Solution solution = new Solution();
        Solution.con = DriverManager.getConnection(url, user, password);
    }

    @Test(groups = {"stud"}, priority = 30)
    public void testSave()  {
        studDAO = new StudDAO();
        stud = new Stud();
        stud.setName("Test");
        actualStud = (Stud) studDAO.save(stud);
        Assert.assertEquals(actualStud.getName(), "Test");
    }

    @Test (groups = {"stud"}, priority = 31)
    public void testLookFor() {
        studDAO = new StudDAO();
        stud = new Stud();
        stud.setName("Test");
        actualStud = studDAO.lookFor(stud);
        id = actualStud.getId();
        Assert.assertEquals(actualStud.getName(), "Test");
    }

    @Test(groups = {"stud"}, priority = 32)
    public void testRemove() {
        studDAO = new StudDAO();
        stud = new Stud();
        stud.setName("Test");
        actualStud = studDAO.lookFor(stud);
        id = actualStud.getId();
        stud.setId(id);
        actualStud = (Stud) studDAO.remove(stud);
        Assert.assertNull(actualStud);
    }

    @Test(groups = {"stud"}, priority = 33)
    public void testUpdate() {
        studDAO = new StudDAO();
        stud = new Stud();
        stud.setName("Test");
        studDAO.save(stud);
        actualStud = studDAO.lookFor(stud);
        id = actualStud.getId();
        stud.setId(id);
        stud.setName("TestUpdate");
        actualStud = (Stud) studDAO.update(stud);
        Assert.assertEquals(actualStud.getName(), "TestUpdate");
    }

    @Test(groups = {"stud"}, priority = 34)
    public void testGet() {
        studDAO = new StudDAO();
        actualStud = studDAO.get(id);
        /**studDAO.remove(stud);*/
        Assert.assertEquals(actualStud.getName(), "TestUpdate");
    }

    @AfterMethod(groups = {"stud"})
    public void tearDown() {
        stud = null;
        actualStud = null;
        studDAO = null;
        Solution.con = null;
    }
}

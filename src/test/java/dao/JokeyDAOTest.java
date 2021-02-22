package dao;

import entity.Jokey;
import entity.Player;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import racing.Solution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import static org.testng.Assert.*;

public class JokeyDAOTest {
    private JokeyDAO jokeyDAO;
    private Jokey jokey;
    private Jokey actualJokey;
    private int id;
    @BeforeMethod(groups = {"jokey"})
    public void setUp() throws SQLException {
        String url = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacingTest";
        String user = "RRA";
        String password = "rra";
        Connection con;
        Solution solution = new Solution();
        Solution.con = DriverManager.getConnection(url, user, password);
    }

    @Test(groups = {"jokey"}, priority = 0)
    public void testSave()  {
        jokeyDAO = new JokeyDAO();
        jokey = new Jokey();
        jokey.setName("Test");
        actualJokey = jokeyDAO.save(jokey);
        Assert.assertEquals(actualJokey.getName(), "Test");
    }

    @Test (groups = {"jokey"}, priority = 1)
    public void testLookFor() {
        jokeyDAO = new JokeyDAO();
        jokey = new Jokey();
        jokey.setName("Test");
        actualJokey = jokeyDAO.lookFor(jokey);
        id = actualJokey.getId();
        Assert.assertEquals(actualJokey.getName(), "Test");
    }

    @Test(groups = {"jokey"}, priority = 2)
    public void testRemove() {
        jokeyDAO = new JokeyDAO();
        jokey = new Jokey();
        jokey.setName("Test");
        actualJokey = jokeyDAO.lookFor(jokey);
        id = actualJokey.getId();
        jokey.setId(id);
        actualJokey = jokeyDAO.remove(jokey);
        Assert.assertEquals(actualJokey.getId(), 0);
        Assert.assertEquals(actualJokey.getName(), "");
    }

    @Test(groups = {"jokey"}, priority = 3)
    public void testUpdate() {
        jokeyDAO = new JokeyDAO();
        jokey = new Jokey();
        jokey.setName("Test");
        jokeyDAO.save(jokey);
        actualJokey = jokeyDAO.lookFor(jokey);
        id = actualJokey.getId();
        jokey.setId(id);
        jokey.setName("TestUpdate");
        actualJokey = jokeyDAO.update(jokey);
        Assert.assertEquals(actualJokey.getName(), "TestUpdate");
    }

    @Test(groups = {"jokey"}, priority = 4)
    public void testGet() {
        jokeyDAO = new JokeyDAO();
        actualJokey = jokeyDAO.get(id);
        /**jokeyDAO.remove(jokey);*/
        Assert.assertEquals(actualJokey.getName(), "TestUpdate");
    }

    @AfterMethod(groups = {"jokey"})
    public void tearDown() {
        jokey = null;
        actualJokey = null;
        jokeyDAO = null;
        Solution.con = null;
    }

}
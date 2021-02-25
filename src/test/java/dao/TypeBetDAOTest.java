package dao;

import entity.TypeBet;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import racing.Solution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.testng.Assert.*;

public class TypeBetDAOTest {
    private TypeBetDAO typeBetDAO;
    private TypeBet typeBet;
    private TypeBet actualTypeBet;
    private int id;
    @BeforeMethod(groups = {"typeBet"})
    public void setUp() throws SQLException {
        String url = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacingTest";
        String user = "RRA";
        String password = "rra";
        Connection con;
        Solution solution = new Solution();
        Solution.con = DriverManager.getConnection(url, user, password);
    }

    @Test(groups = {"typeBet"}, priority = 70)
    public void testSaveString()  {
        typeBetDAO = new TypeBetDAO();
        typeBet = new TypeBet();
        typeBet.setTypeBet("Test");
        typeBet.setRate(99.9);
        actualTypeBet = (TypeBet) typeBetDAO.save(typeBet);
        Assert.assertEquals(actualTypeBet.getTypeBet(), "Test");
    }

    @Test(groups = {"typeBet"}, priority = 71)
    public void testSaveDouble()  {
        typeBetDAO = new TypeBetDAO();
        typeBet = new TypeBet();
        typeBet.setTypeBet("Test");
        typeBet.setRate(99.9);
        actualTypeBet = (TypeBet) typeBetDAO.save(typeBet);
        Assert.assertEquals(actualTypeBet.getRate(), 99.9);
    }

    @Test (groups = {"typeBet"}, priority = 72)
    public void testLookFor() {
        typeBetDAO = new TypeBetDAO();
        typeBet = new TypeBet();
        typeBet.setTypeBet("Test");
        typeBet.setRate(99.9);
        actualTypeBet = typeBetDAO.lookFor(typeBet);
        id = actualTypeBet.getId();
        Assert.assertEquals(actualTypeBet.getTypeBet(), "Test");
    }

    @Test(groups = {"typeBet"}, priority = 73)
    public void testRemove() {
        typeBetDAO = new TypeBetDAO();
        typeBet = new TypeBet();
        //typeBet.setTypeBet("Test");
        //actualTypeBet = typeBetDAO.lookFor(typeBet);
        //id = actualTypeBet.getId();
        typeBet.setId(id);
        actualTypeBet = (TypeBet) typeBetDAO.remove(typeBet);
        Assert.assertNull(actualTypeBet);
    }

    @Test(groups = {"typeBet"}, priority = 74)
    public void testUpdate() {
        typeBetDAO = new TypeBetDAO();
        typeBet = new TypeBet();
        typeBet.setTypeBet("Test");
        typeBet.setRate(99.9);
        typeBetDAO.save(typeBet);
        actualTypeBet = typeBetDAO.lookFor(typeBet);
        id = actualTypeBet.getId();
        typeBet.setId(id);
        typeBet.setTypeBet("TestUpdate");
        typeBet.setRate(00.1);
        actualTypeBet = (TypeBet) typeBetDAO.update(typeBet);
        Assert.assertEquals(actualTypeBet.getTypeBet(), "TestUpdate");
    }

    @Test(groups = {"typeBet"}, priority = 75)
    public void testGet() {
        typeBetDAO = new TypeBetDAO();
        actualTypeBet = typeBetDAO.get(id);
        /**typeBetDAO.remove(typeBet);*/
        Assert.assertEquals(actualTypeBet.getTypeBet(), "TestUpdate");
    }

    @AfterMethod(groups = {"typeBet"})
    public void tearDown() {
        typeBet = null;
        actualTypeBet = null;
        typeBetDAO = null;
        Solution.con = null;
    }
}
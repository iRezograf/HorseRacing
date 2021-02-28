package dao;

import entity.TypeBet;
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

public class TypeBetDAOTest {
    TypeBetDAO typeBetDAO;
    TypeBet typeBet;
    TypeBet actualTypeBet;

    @BeforeMethod(groups = {"typeBet"})
    public void setUp() throws SQLException {
        String url = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacingTest";
        String user = "RRA";
        String password = "rra";
        //Solution solution = new Solution();
        Solution.con = DriverManager.getConnection(url, user, password);

        String sql =  "DELETE TOP(10) FROM type_bet";
        PreparedStatement ps = Solution.con.prepareStatement(sql);
        ps.executeUpdate();

        typeBetDAO = new TypeBetDAO();
        typeBet = new TypeBet();
        typeBet.setTypeBet("TestTypeBetName");
        typeBet.setRate(99.9);
        actualTypeBet = new TypeBet();
    }

    @Test(groups = {"typeBet"}, priority = 70)
    public void testSaveString()  {

        actualTypeBet = (TypeBet) typeBetDAO.save(typeBet);
        Assert.assertEquals(actualTypeBet.getTypeBet(), "TestTypeBetName");
    }

    @Test(groups = {"typeBet"}, priority = 71)
    public void testSaveDouble()  {
        actualTypeBet = (TypeBet) typeBetDAO.save(typeBet);
        Assert.assertEquals(actualTypeBet.getRate(), 99.9);
    }

    @Test (groups = {"typeBet"}, priority = 72)
    public void testLookFor() {
        typeBet.setTypeBet("TestTypeBetNameLookFor");
        actualTypeBet = (TypeBet) typeBetDAO.save(typeBet);
        actualTypeBet = typeBetDAO.lookFor(typeBet);
        Assert.assertEquals(actualTypeBet.getTypeBet(), "TestTypeBetNameLookFor");
    }

    @Test(groups = {"typeBet"}, priority = 73)
    public void testRemove() {
        typeBet.setTypeBet("TestTypeBetNameRemove");
        actualTypeBet = (TypeBet) typeBetDAO.save(typeBet);
        actualTypeBet = typeBetDAO.lookFor(typeBet);
        actualTypeBet = (TypeBet) typeBetDAO.remove(typeBet);
        Assert.assertNull(actualTypeBet);
    }

    @Test(groups = {"typeBet"}, priority = 74)
    public void testUpdate() {
        typeBet.setTypeBet("TestTypeBetNameUpdateBefore");
        actualTypeBet = (TypeBet) typeBetDAO.save(typeBet);
        actualTypeBet = typeBetDAO.lookFor(typeBet);
        typeBet.setTypeBet("TestTypeBetNameUpdateAfter");
        actualTypeBet = (TypeBet) typeBetDAO.update(typeBet);
        Assert.assertEquals(actualTypeBet.getTypeBet(), "TestTypeBetNameUpdateAfter");
    }

    @Test(groups = {"typeBet"}, priority = 75)
    public void testGet() {
        typeBet.setTypeBet("TestTypeBet_get");
        actualTypeBet = (TypeBet) typeBetDAO.save(typeBet);
        actualTypeBet = typeBetDAO.lookFor(typeBet);
        actualTypeBet = typeBetDAO.get(actualTypeBet.getId());
        Assert.assertEquals(actualTypeBet.getTypeBet(), "TestTypeBet_get");
    }

    @AfterMethod(groups = {"typeBet"})
    public void tearDown() {
        typeBet = null;
        actualTypeBet = null;
        typeBetDAO = null;
        Solution.con = null;
    }
}
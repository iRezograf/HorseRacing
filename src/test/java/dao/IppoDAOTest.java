package dao;

import entity.Ippo;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import racing.Solution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.testng.Assert.*;

public class IppoDAOTest {
    private IppoDAO ippoDAO;
    private Ippo ippo;
    private Ippo actualIppo;
    private int id;
    
    @BeforeMethod(groups = {"ippo"})
    public void setUp() throws SQLException {
        String url = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacingTest";
        String user = "RRA";
        String password = "rra";
        Connection con;
        Solution solution = new Solution();
        Solution.con = DriverManager.getConnection(url, user, password);
    }

    @Test(groups = {"ippo"}, priority = 60)
    public void testSave()  {
        ippoDAO = new IppoDAO();
        ippo = new Ippo();
        ippo.setName("Test");
        actualIppo = (Ippo) ippoDAO.save(ippo);
        Assert.assertEquals(actualIppo.getName(), "Test");
    }

    @Test (groups = {"ippo"}, priority = 61)
    public void testLookFor() {
        ippoDAO = new IppoDAO();
        ippo = new Ippo();
        ippo.setName("Test");
        actualIppo = ippoDAO.lookFor(ippo);
        id = actualIppo.getId();
        Assert.assertEquals(actualIppo.getName(), "Test");
    }

    @Test(groups = {"ippo"}, priority = 62)
    public void testRemove() {
        ippoDAO = new IppoDAO();
        ippo = new Ippo();
        ippo.setName("Test");
        actualIppo = ippoDAO.lookFor(ippo);
        id = actualIppo.getId();
        ippo.setId(id);
        actualIppo = (Ippo) ippoDAO.remove(ippo);
        Assert.assertNull(actualIppo);
    }

    @Test(groups = {"ippo"}, priority = 63)
    public void testUpdate() {
        ippoDAO = new IppoDAO();
        ippo = new Ippo();
        ippo.setName("Test");
        ippoDAO.save(ippo);
        actualIppo = ippoDAO.lookFor(ippo);
        id = actualIppo.getId();
        ippo.setId(id);
        ippo.setName("TestUpdate");
        actualIppo = (Ippo) ippoDAO.update(ippo);
        Assert.assertEquals(actualIppo.getName(), "TestUpdate");
    }

    @Test(groups = {"ippo"}, priority = 64)
    public void testGet() {
        ippoDAO = new IppoDAO();
        actualIppo = ippoDAO.get(id);
        /**ippoDAO.remove(ippo);*/
        Assert.assertEquals(actualIppo.getName(), "TestUpdate");
    }

    @AfterMethod(groups = {"ippo"})
    public void tearDown() {
        ippo = null;
        actualIppo = null;
        ippoDAO = null;
        Solution.con = null;
    }
}
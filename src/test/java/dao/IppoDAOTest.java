package dao;

import entity.Ippo;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import racing.Solution;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IppoDAOTest {
    IppoDAO ippoDAO;
    Ippo ippo;
    Ippo actualIppo;

    @BeforeMethod(groups = {"ippo"})
    public void setUp() throws SQLException {
        String url = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacingTest";
        String user = "RRA";
        String password = "rra";
        Solution.con = DriverManager.getConnection(url, user, password);
        String sql =  "DELETE TOP(10) FROM ippo";
        PreparedStatement ps = Solution.con.prepareStatement(sql);
        ps.executeUpdate();
        ippoDAO = new IppoDAO();
        ippo = new Ippo();
        ippo.setName("Test_ippoName");
        actualIppo = new Ippo();
    }

    @Test(groups = {"ippo"}, priority = 60)
    public void testSave()  {
        ippo.setName("Test_ippoName");
        actualIppo = (Ippo) ippoDAO.save(ippo);
        Assert.assertEquals(actualIppo.getName(), "Test_ippoName");
    }

    @Test (groups = {"ippo"}, priority = 61)
    public void testLookFor() {
        ippo.setName("Test_ippoName_LookFor");
        actualIppo = (Ippo) ippoDAO.save(ippo);
        actualIppo = ippoDAO.lookFor(ippo);
        Assert.assertEquals(actualIppo.getName(), "Test_ippoName_LookFor");
    }

    @Test(groups = {"ippo"}, priority = 62)
    public void testRemove() {
        ippo.setName("Test_ippoName_Remove");
        actualIppo = (Ippo) ippoDAO.save(ippo);
        actualIppo = ippoDAO.lookFor(ippo);
        actualIppo = (Ippo) ippoDAO.remove(actualIppo);
        Assert.assertNull(actualIppo);
    }

    @Test(groups = {"ippo"}, priority = 63)
    public void testUpdate() {
        ippo.setName("Test_ippoName_Update_before");
        actualIppo = (Ippo) ippoDAO.save(ippo);
        actualIppo = ippoDAO.lookFor(ippo);
        actualIppo.setName("Test_ippoName_Update_After");
        actualIppo = (Ippo) ippoDAO.update(ippo);
        Assert.assertEquals(actualIppo.getName(), "Test_ippoName_Update_After");
    }

    @Test(groups = {"ippo"}, priority = 64)
    public void testGet() {
        ippo.setName("Test_Get");
        actualIppo = (Ippo) ippoDAO.save(ippo);
        actualIppo = ippoDAO.lookFor(ippo);
        actualIppo = ippoDAO.get(actualIppo.getId());
        Assert.assertEquals(actualIppo.getName(), "Test_Get");
    }

    @AfterMethod(groups = {"ippo"})
    public void tearDown() {
        ippo = null;
        actualIppo = null;
        ippoDAO = null;
        Solution.con = null;
    }
}
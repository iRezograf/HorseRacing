package dao;

import entity.Jokey;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import racing.Solution;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class JokeyDAOTest {
    JokeyDAO jokeyDAO;
    Jokey jokey;
    Jokey actualJokey;

    @BeforeMethod(groups = {"jokey"})
    public void setUp() throws SQLException {
        String url = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacingTest";
        String user = "RRA";
        String password = "rra";

        Solution.con = DriverManager.getConnection(url, user, password);
        String sql =  "DELETE TOP(10) FROM jokey";
        PreparedStatement ps = Solution.con.prepareStatement(sql);
        ps.executeUpdate();
        jokeyDAO = new JokeyDAO();
        jokey = new Jokey();
        actualJokey = new Jokey();
        jokey.setName("TestName");
    }

    @Test(groups = {"jokey"}, priority = 0)
    public void testSave()  {
        /** "TestName" */
        actualJokey = jokeyDAO.save(jokey);
        actualJokey = jokeyDAO.lookFor(jokey);

        Assert.assertEquals(actualJokey.getName(), "TestName");
    }

    @Test (groups = {"jokey"}, priority = 1)
    public void testLookFor() {
        actualJokey = jokeyDAO.save(jokey);
        actualJokey = jokeyDAO.lookFor(jokey);
        Assert.assertNotNull(actualJokey.getId());
    }

    @Test(groups = {"jokey"}, priority = 2)
    public void testRemove() {
        actualJokey = jokeyDAO.save(jokey);
        actualJokey = jokeyDAO.lookFor(jokey);
        jokey.setId(actualJokey.getId());

        actualJokey = jokeyDAO.remove(jokey);
        Assert.assertNull(actualJokey);
    }

    @Test(groups = {"jokey"}, priority = 3)
    public void testUpdate() {
        actualJokey = jokeyDAO.save(jokey);
        actualJokey = jokeyDAO.lookFor(jokey);
        jokey.setId(actualJokey.getId());
        jokey.setName("TestUpdate");

        actualJokey = jokeyDAO.update(jokey);
        Assert.assertEquals(actualJokey.getName(), "TestUpdate");
    }

    @Test(groups = {"jokey"}, priority = 4)
    public void testGet() {
        jokey.setName("TestGet");
        actualJokey = jokeyDAO.save(jokey);
        actualJokey = jokeyDAO.lookFor(jokey);
        actualJokey = jokeyDAO.get(actualJokey.getId());
        /**jokeyDAO.remove(jokey);*/
        Assert.assertEquals(actualJokey.getName(), "TestGet");
    }

    @AfterMethod(groups = {"jokey"})
    public void tearDown() {
        jokey = null;
        actualJokey = null;
        jokeyDAO = null;
        Solution.con = null;
    }
}
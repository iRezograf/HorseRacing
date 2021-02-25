package dao;

import entity.Jokey;
import entity.Player;
import org.testng.Assert;
import org.testng.annotations.*;
import racing.Solution;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.testng.Assert.*;

public class PlayerDAOTest {
    private Player player;
    private Player actual;
    private PlayerDAO playerDAO;
    private int id;

    @BeforeMethod(groups = {"player"})
    public void beforeClass() throws SQLException {
        String url = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacingTest";
        String user = "RRA";
        String password = "rra";
        Connection con;
        Solution solution = new Solution();
        Solution.con = DriverManager.getConnection(url, user, password);
    }

    @Test(groups = {"player"}, priority = 5)
    public void testSave() {
        playerDAO = new PlayerDAO();
        player = new Player();
        player.setLogin("PlayerLogin");
        player.setPassword("PlayerPassword");
        actual = playerDAO.save(player);
        Assert.assertEquals(actual.getLogin(), "PlayerLogin");
    }

    @Test(groups = {"player"}, priority = 6)
    public void testTestLookFor() {
        playerDAO = new PlayerDAO();
        player = new Player();
        actual = playerDAO.lookFor("PlayerLogin", "PlayerPassword");
        id = actual.getId();
        Assert.assertEquals(actual.getLogin(), "PlayerLogin");
    }

    @Test(groups = {"player"}, priority = 7)
    public void testRemove() {
        playerDAO = new PlayerDAO();
        player = new Player();
        player.setId(id);
        actual = playerDAO.remove(player);
        Assert.assertNull(actual);
    }

    @Test(groups = {"player"}, priority = 8)
    public void testLookFor() {
        playerDAO = new PlayerDAO();
        player = new Player();
        player.setFirstName("FirstName");
        player.setLastName("LastName");
        player.setLogin("PlayerLogin");
        player.setPassword("PlayerPassword");
        /** playerDAO.save(player) already tested and OK, if we are here
         * we can use it*/
        playerDAO.save(player);
        actual = playerDAO.lookFor(player);
        id = actual.getId();
        Assert.assertEquals(actual.getFirstName(), "FirstName");
    }


    @Test(groups = {"player"}, priority = 9)
    public void testUpdate() {
        playerDAO = new PlayerDAO();
        player = new Player();
        player.setId(id);
        player.setFirstName("FirstNameUpdated");
        player.setLastName("LastNameUpdated");
        player.setLogin("PlayerLoginUpdated");
        player.setPassword("PlayerPasswordUpdated");
        actual = playerDAO.update(player);
        Assert.assertEquals(actual.getLastName(), "LastNameUpdated");
    }

    @Test(groups = {"player"}, priority = 10)
    public void testGet() {
        playerDAO = new PlayerDAO();
        player = new Player();
        actual = playerDAO.get(id);
        /** playerDAO.remove(player); */

        /** I know it is not recomendet, but interested whole set below at one*/
        Assert.assertEquals(actual.getFirstName(), "FirstNameUpdated");
        Assert.assertEquals(actual.getLastName(), "LastNameUpdated");
        Assert.assertEquals(actual.getLogin(), "PlayerLoginUpdated");
        Assert.assertEquals(actual.getPassword(), "PlayerPasswordUpdated");
    }

    @AfterMethod(groups = {"player"})
    public void tearDown() {
        player = null;
        actual = null;
        playerDAO = null;
        Solution.con = null;
    }
}
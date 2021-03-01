package dao;

import entity.Player;
import org.testng.Assert;
import org.testng.annotations.*;
import racing.Solution;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerDAOTest {
    Player player;
    Player actualPlayer;
    PlayerDAO playerDAO;

    @BeforeMethod(groups = {"player"})
    public void beforeClass() throws SQLException {
        String url = "jdbc:sqlserver://RRA-W10\\SQLEXPRESS;database=HorseRacingTest";
        String user = "RRA";
        String password = "rra";
        Solution.con = DriverManager.getConnection(url, user, password);

        String sql =  "DELETE TOP(10) FROM player";
        PreparedStatement ps = Solution.con.prepareStatement(sql);
        ps.executeUpdate();
        playerDAO = new PlayerDAO();
        player = new Player();
        player.setLogin("PlayerLogin");
        player.setPassword("PlayerPassword");
        actualPlayer = new Player();
    }

    @Test(groups = {"player"}, priority = 10)
    public void testSave() {
        player.setLogin("PlayerLogin");
        player.setPassword("PlayerPassword");
        actualPlayer = playerDAO.save(player);
        actualPlayer = playerDAO.lookFor("PlayerLogin", "PlayerPassword");
        Assert.assertEquals(actualPlayer.getLogin(), "PlayerLogin");
    }

    @Test(groups = {"player"}, priority = 11)
    public void testTestLookFor() {
        actualPlayer = playerDAO.save(player);
        actualPlayer = playerDAO.lookFor("PlayerLogin", "PlayerPassword");
        Assert.assertNotNull(actualPlayer);
    }

    @Test(groups = {"player"}, priority = 12)
    public void testRemove() {
        actualPlayer = playerDAO.save(player);
        actualPlayer = playerDAO.lookFor("PlayerLogin", "PlayerPassword");
        player.setId(actualPlayer.getId());
        actualPlayer = playerDAO.remove(player);
        Assert.assertNull(actualPlayer);
    }

    @Test(groups = {"player"}, priority = 13)
    public void testLookFor() {
        player.setFirstName("FirstName");
        player.setLastName("LastName");
        player.setLogin("PlayerLogin");
        player.setPassword("PlayerPassword");
        playerDAO.save(player);
        actualPlayer = playerDAO.lookFor(player);
        Assert.assertEquals(actualPlayer.getFirstName(), "FirstName");
    }

    @Test(groups = {"player"}, priority = 14)
    public void testUpdate() {
        player.setFirstName("FirstName");
        player.setLastName("LastName");
        player.setLogin("PlayerLogin");
        player.setPassword("PlayerPassword");
        playerDAO.save(player);
        actualPlayer = playerDAO.lookFor(player);

        player.setFirstName("FirstNameUpdated");
        player.setLastName("LastNameUpdated");
        player.setLogin("PlayerLoginUpdated");
        player.setPassword("PlayerPasswordUpdated");
        actualPlayer = playerDAO.update(player);
        Assert.assertEquals(actualPlayer.getLastName(), "LastNameUpdated");
    }

    @Test(groups = {"player"}, priority = 15)
    public void testGet() {
        player.setFirstName("FirstName");
        player.setLastName("LastName");
        player.setLogin("PlayerLogin");
        player.setPassword("PlayerPassword");
        playerDAO.save(player);
        actualPlayer = playerDAO.lookFor(player);
        actualPlayer = playerDAO.get(actualPlayer.getId());

        Assert.assertEquals(actualPlayer.getFirstName(), "FirstName");
        Assert.assertEquals(actualPlayer.getLastName(), "LastName");
        Assert.assertEquals(actualPlayer.getLogin(), "PlayerLogin");
        Assert.assertEquals(actualPlayer.getPassword(), "PlayerPassword");
    }

    @Test(groups = {"player"}, priority = 16)
    public void testLookForIsNotNull() {
        player.setFirstName("FirstName");
        player.setLastName("LastName");
        player.setLogin("PlayerLogin");
        player.setPassword("PlayerPassword");

        playerDAO.save(player);
        actualPlayer = null;
        actualPlayer = playerDAO.lookFor(player);
        Assert.assertNotNull(actualPlayer.getFirstName());
    }
    @Test(groups = {"player"}, priority = 17)
    public void testTestLookForBadPassword() {
        actualPlayer = playerDAO.save(player);
        actualPlayer = playerDAO.lookFor("PlayerLogin_", "PlayerPassword");
        Assert.assertNull(actualPlayer);
    }

    @AfterMethod(groups = {"player"})
    public void tearDown() {
        player = null;
        actualPlayer = null;
        playerDAO = null;
        Solution.con = null;
    }
}
package dao;

import dao.interfaces.IPlayerDAO;
import entity.Player;
import org.testng.annotations.Test;

/**static дописываем, чтобы увидеть и статические переменные из Solution*/
import static racing.Solution.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO implements IPlayerDAO {

    @Override
    public Player save(Player player) {
        /** insert player */
        PreparedStatement ps = null;
        String sql = "INSERT INTO player (first_n, last_n, login, password)" +
                " VALUES (?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, player.getFirstName());
            ps.setString(2, player.getLastName());
            ps.setString(3, player.getLogin());
            ps.setString(4, player.getPassword());
            //ps.executeUpdate();
            //ResultSet resultSet = ps.getResultSet();
            int rows = ps.executeUpdate();
            if (rows > 0 ){
                System.out.println(player + "\n player is inserted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return player;
    }

    @Override
    public Player remove(Player player) {
        /** delete */
        //Player player = new Player();
        PreparedStatement ps = null;
        String sql =    "DELETE FROM [dbo].[player]\n" +
                        "WHERE [id] = ?";
        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, player.getId());
            //ps.executeUpdate();
            //ResultSet resultSet = pst.getResultSet();
            int rows = ps.executeUpdate();
            if (rows > 0 ){
                System.out.println(player + "\n player is removed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return player;
    }

    @Override
    public Player update(Player player) {
        /** update */
        PreparedStatement ps = null;
        String sql =    "UPDATE [dbo].[player] \n" +
                        "SET [first_n] = ? \n" +
                        ",[last_n] = ? \n" +
                        ",[login] = ? \n" +
                        ",[password] = ? \n" +
                        "WHERE [id] = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString   (1, player.getFirstName());
            ps.setString   (2, player.getLastName());
            ps.setString   (3, player.getLogin());
            ps.setString   (4, player.getPassword());
            ps.setInt      (5, player.getId());

            //ps.executeUpdate();
            //ResultSet resultSet = pst.getResultSet();
            int rows = ps.executeUpdate();
            if (rows > 0 ){
                System.out.println(player + "\n player is updated");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return player;
    }

    @Override
    public List<Player> getPlayers() {
        Player player = null;
        Statement ps = null;
        String sql = "SELECT [id]\n" +
                "      ,[first_n]\n" +
                "      ,[last_n]\n" +
                "      ,[login]\n" +
                "      ,[password]\n" +
                "  FROM [player]";
        List<Player> players = new ArrayList<Player>();
        try {
            ps = con.createStatement();
            ResultSet resultSet = ps.executeQuery(sql);
            while (resultSet.next()) {
                player = new Player();
                player.setId(resultSet.getInt("id"));
                player.setFirstName(resultSet.getString(2));
                player.setLastName(resultSet.getString(3));
                player.setLogin(resultSet.getString(4));
                player.setPassword(resultSet.getString(5));
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return players;
    }

    @Override
    public Player get(int id) {
        String sql = "SELECT [id]\n" +
                "      ,[first_n]\n" +
                "      ,[last_n]\n" +
                "      ,[login]\n" +
                "      ,[password]\n" +
                "  FROM [dbo].[player] WHERE [id] = ?";
        PreparedStatement ps = null;
        Player player = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            rs.next();
            player = new Player();
            player.setId(rs.getInt(1));
            player.setFirstName(rs.getString(2));
            player.setLastName(rs.getString(3));
            player.setLogin(rs.getString(4));
            player.setPassword(rs.getString(5));
            System.out.println(player);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return player;
    }

    public Player lookFor(String login, String password) {
        /*Look for player in table player. If found return player,
         * if don't found return null.
         * For registration  */
        String sql =    "SELECT [id]\n" +
                        ",[first_n]\n" +
                        ",[last_n]\n" +
                        ",[login]\n" +
                        ",[password]\n" +
                        "FROM [dbo].[player]\n " +
                        "WHERE [login] = ?\n" +
                        "AND [password] = ?";
        PreparedStatement ps = null;
        Player player = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, password);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            player = new Player();
            if (rs.next()) {
                player.setId(rs.getInt(1));
                player.setFirstName(rs.getString(2));
                player.setLastName(rs.getString(3));
                player.setLogin(rs.getString(4));
                player.setPassword(rs.getString(5));
            } else {
                player.setId(0);
            }
            System.out.println(player);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return player;
    }
    public Player lookFor(Player player) {
        /*Look for player in table player. If found return player,
         * if don't found return null.
         * For registration  */
        String sql = "SELECT [id]\n" +
                    ",[first_n]\n" +
                    ",[last_n]\n" +
                    ",[login]\n" +
                    ",[password]\n" +
                    "FROM [dbo].[player] " +
                    "WHERE  [first_n] = ?\n" +
                    "AND    [last_n] = ?\n" +
                    "AND    [login] = ?\n" +
                    "AND    [password] = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            if (!player.getFirstName().isEmpty())
            ps.setString(1, player.getFirstName());
            ps.setString(2, player.getLastName());
            ps.setString(3, player.getLogin());
            ps.setString(4, player.getPassword());
            ps.executeQuery();

            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                player.setId(rs.getInt(1));
            }
            System.out.println(player);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return player;
    }
}

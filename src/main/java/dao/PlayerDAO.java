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
        PreparedStatement pst = null;
        String sql = "INSERT INTO player (first_n, last_n, login, password)" +
                " VALUES (?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, player.getFirstName());
            pst.setString(2, player.getLastName());
            pst.setString(3, player.getLogin());
            pst.setString(4, player.getPassword());
            pst.executeUpdate();
            ResultSet resultSet = pst.getResultSet();
            /*resultSet.next();*/
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                    con.commit();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return player;
    }

    @Override
    public Player remove(int id) {
        /** delete */
        Player player = new Player();
        PreparedStatement pst = null;
        String sql =    "DELETE FROM [dbo].[player]\n" +
                        "WHERE [id] = ?";
        try {
            pst = con.prepareStatement(sql);
            player = this.get(id);

            pst.setInt(1, player.getId());
            pst.executeUpdate();
            ResultSet resultSet = pst.getResultSet();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return player;
    }

    @Override
    public Player update(Player player, int id) {
        /** update */
        PreparedStatement pst = null;
        String sql =    "UPDATE [dbo].[player] \n" +
                        "SET [first_n] = ? \n" +
                        ",[last_n] = ? \n" +
                        ",[login] = ? \n" +
                        ",[password] = ? \n" +
                        "WHERE [id] = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString   (1, player.getFirstName());
            pst.setString   (2, player.getLastName());
            pst.setString   (3, player.getLogin());
            pst.setString   (4, player.getPassword());
            pst.setInt      (5, id);

            pst.executeUpdate();
            ResultSet resultSet = pst.getResultSet();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                    con.commit();
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
        Statement st = null;
        String sql = "SELECT [id]\n" +
                "      ,[first_n]\n" +
                "      ,[last_n]\n" +
                "      ,[login]\n" +
                "      ,[password]\n" +
                "  FROM [player]";
        List<Player> players = new ArrayList<Player>();
        try {
            st = con.createStatement();
            ResultSet resultSet = st.executeQuery(sql);
            while (resultSet.next()) {
                /**Jokey jokey = new Jokey(resultSet.getInt("id"), resultSet.getString("name"));*/
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
        PreparedStatement st = null;
        Player player = null;
        try {
            st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            rs.next();
            player = new Player();
            player.setId(rs.getInt(1));
            player.setFirstName(rs.getString(2));
            player.setLastName(rs.getString(3));
            player.setLogin(rs.getString(4));
            player.setPassword(rs.getString(5));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return player;
    }

    public Player lookFor(String firstName,
                          String lastName,
                          String login,
                          String password) {
        /*Look for player in table player. If found return player,
         * if don't found return null.
         * For registration  */
        String sql = "SELECT [id]\n" +
                "      ,[first_n]\n" +
                "      ,[last_n]\n" +
                "      ,[login]\n" +
                "      ,[password]\n" +
                "  FROM [dbo].[player] WHERE " +
                "[first_n] = ?\n" +
                "AND [last_n] = ?\n" +
                "AND [login] = ?\n" +
                "AND [password] = ?\n";
        PreparedStatement st = null;
        Player player = null;
        try {
            st = con.prepareStatement(sql);
            st.setString(1, firstName);
            st.setString(2, lastName);
            st.setString(3, login);
            st.setString(4, password);
            st.executeQuery();
            if (st.getMaxRows() != 0) {
                ResultSet rs = st.getResultSet();
                rs.next();
                player = new Player();
                player.setId(rs.getInt(1));
                player.setFirstName(rs.getString(2));
                player.setLastName(rs.getString(3));
                player.setLogin(rs.getString(4));
                player.setPassword(rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
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
                    "AND    [password] = ?\n";
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(sql);
            st.setString(1, player.getFirstName());
            st.setString(2, player.getLastName());
            st.setString(3, player.getLogin());
            st.setString(4, player.getPassword());
            st.executeQuery();

            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                player.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return player;
    }
}

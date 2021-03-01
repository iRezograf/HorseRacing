package dao;

import dao.interfaces.IPlayerDAO;
import entity.Player;
import racing.Solution;

/**static дописываем, чтобы увидеть и статические переменные из Solution*/
import static racing.Solution.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO implements IPlayerDAO {

    @Override
    public Player save(Player player) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO player (first_n, last_n, login, password)" +
                " VALUES (?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, player.getFirstName());
            ps.setString(2, player.getLastName());
            ps.setString(3, player.getLogin());
            ps.setString(4, player.getPassword());
            int rows = ps.executeUpdate();
            if (rows > 0 ){
                System.out.println("From Save: "+ player);
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
        Player playerRet = new Player();
        PreparedStatement ps = null;
        String sql =    "DELETE FROM player " +
                        "WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, player.getId());

            int rows = ps.executeUpdate();
            if (rows > 0 ){
                playerRet = null;
                System.out.println("From Remove: "+ player);
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
        return playerRet;
    }

    @Override
    public Player update(Player player) {
        PreparedStatement ps = null;
        String sql =    "UPDATE player \n" +
                        "SET    first_n = ? \n" +
                                ",last_n = ? \n" +
                                ",login = ? \n" +
                                ",password = ? \n" +
                        "WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString   (1, player.getFirstName());
            ps.setString   (2, player.getLastName());
            ps.setString   (3, player.getLogin());
            ps.setString   (4, player.getPassword());
            ps.setInt      (5, player.getId());

            int rows = ps.executeUpdate();
            if (rows > 0 ){
                System.out.println("From Update" + player);
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
        String sql = "SELECT TOP (10)"+
                            " id" +
                            ",first_n" +
                            ",last_n" +
                            ",login" +
                            ",password " +
                    "FROM   player";
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
                System.out.println("From getPlayers: " + player);
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
        String sql = "SELECT "+
                            "id" +
                            ",first_n" +
                            ",last_n" +
                            ",login" +
                            ",password " +
                    "FROM player "   +
                    "WHERE id = ?";
        PreparedStatement ps = null;
        Player player = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            if (rs.next()){
                player = new Player();
                player.setId(rs.getInt(1));
                player.setFirstName(rs.getString(2));
                player.setLastName(rs.getString(3));
                player.setLogin(rs.getString(4));
                player.setPassword(rs.getString(5));
                System.out.println("From get:"+player);
            } else{
                player = null;
                System.out.println("From get (not found):"+player);
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

    public Player lookFor(String login, String password) {
        String sql =    "SELECT id" +
                        ",first_n" +
                        ",last_n" +
                        ",login" +
                        ",password " +
                        "FROM player " +
                        "WHERE login = ?\n" +
                        "AND password = ?";
        PreparedStatement ps = null;
        Player player = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, password);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                Solution.player.setId(rs.getInt(1));
                Solution.player.setFirstName(rs.getString(2));
                Solution.player.setLastName(rs.getString(3));
                Solution.player.setLogin(rs.getString(4));
                Solution.player.setPassword(rs.getString(5));
                System.out.println("From LookFor (log/pass):"+Solution.player);
            } else {
                Solution.player = null;
                System.out.println("From LookFor (log/pass not found):" + Solution.player);
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
        return Solution.player;
    }
    public Player lookFor(Player player) {
        String sql = "SELECT id " +
                            ",first_n" +
                            ",last_n" +
                            ",login" +
                            ",password " +
                    "FROM player " +
                    "WHERE  first_n = ? " +
                    "AND    last_n = ? " +
                    "AND    login = ? " +
                    "AND    password = ?";
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
                Solution.player.setId(rs.getInt(1));
                Solution.player.setFirstName(rs.getString(2));
                Solution.player.setLastName(rs.getString(3));
                Solution.player.setLogin(rs.getString(4));
                Solution.player.setPassword(rs.getString(5));
                System.out.println("From LookFor(player):"+Solution.player);
            } else {
                Solution.player = null;
                System.out.println("From LookFor(player not found):"+Solution.player);
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
        return Solution.player;
    }
}

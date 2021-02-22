package dao;

import dao.interfaces.IJokeyDAO;
import entity.Jokey;
import racing.Solution;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static racing.Solution.con;

public class JokeyDAO implements IJokeyDAO {

    @Override
    public List<Jokey> getJokeys() {
        Jokey jokey = null;
        Statement ps = null;
        String sql = "SELECT id,name FROM jokey ";
        List<Jokey> jokeys = new ArrayList<Jokey>();
        try {
            ps = con.createStatement();
            ResultSet resultSet = ps.executeQuery(sql);
            while (resultSet.next()){
                /**Jokey jokey = new Jokey(resultSet.getInt("id"), resultSet.getString("name"));*/
                jokey = new Jokey();
                jokey.setId(resultSet.getInt("id"));
                jokey.setName(resultSet.getString("name"));
                jokeys.add(jokey);
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
        return jokeys;
    }

    @Override
    public Jokey get(int id) {
        Jokey jokey = null;
        PreparedStatement ps = null;
        String sql = "SELECT id,name FROM jokey WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();
            resultSet.next();
            /**jokey = new Jokey(resultSet.getInt("id"), resultSet.getString("name"));*/
            jokey = new Jokey();
            jokey.setId(resultSet.getInt("id"));
            jokey.setName(resultSet.getString("name"));
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
        return jokey;
    }

    @Override
    public Jokey save(Jokey jokey) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO jokey (name) VALUES (?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, jokey.getName());
            ps.executeUpdate();
            ResultSet resultSet = ps.getResultSet();
            /*resultSet.next();*/
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
        return jokey;
    }

    @Override
    public Jokey remove(Jokey jokey){
    PreparedStatement ps = null;
    String sql = "DELETE FROM jokey WHERE id = ?";
        try {
        ps = con.prepareStatement(sql);
        ps.setInt(1, jokey.getId());
        ps.executeUpdate();
        ResultSet resultSet = ps.getResultSet();
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
        jokey.setId(0);
        jokey.setName("");
        return jokey;
    }

    @Override
    public Jokey update(Jokey jokey) {
        PreparedStatement ps = null;
        String sql =    "UPDATE [dbo].[jokey] \n" +
                        "SET [name] = ? \n" +
                        "WHERE [id] = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString   (1, jokey.getName());
            ps.setInt      (2, jokey.getId());

            ps.executeUpdate();
            ResultSet resultSet = ps.getResultSet();

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
        return jokey;
    }

    public Jokey lookFor(Jokey jokey) {
        PreparedStatement ps = null;
        String sql = "SELECT id,name FROM jokey WHERE name = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, jokey.getName());
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();
            resultSet.next();

            jokey.setId(resultSet.getInt("id"));
            jokey.setName(resultSet.getString("name"));
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
        return jokey;
    }
}

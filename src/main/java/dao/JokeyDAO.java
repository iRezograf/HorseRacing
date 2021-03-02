package dao;

import dao.interfaces.IJokeyDAO;
import entity.Jokey;

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
                jokey = new Jokey();
                jokey.setId(resultSet.getInt("id"));
                jokey.setName(resultSet.getString("name"));
                jokeys.add(jokey);
                System.out.println("From getJokeys: "+jokey);
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
            if (resultSet.next()){
                jokey = new Jokey();
                jokey.setId(resultSet.getInt("id"));
                jokey.setName(resultSet.getString("name"));
                System.out.println("From Get: "+jokey);
            } else
                System.out.println("From Get (records not found): "+jokey);
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
            if (ps.executeUpdate() > 0 ){
                System.out.println("From Save: "+jokey);
            } else {
                System.out.println("From Save (not saved): "+jokey);
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
        return jokey;
    }

    @Override
    public Jokey remove(Jokey jokey){
    PreparedStatement ps = null;
    String sql = "DELETE FROM jokey WHERE id = ?";
        try {
        ps = con.prepareStatement(sql);
        ps.setInt(1, jokey.getId());
        if (ps.executeUpdate() > 0 ){
            System.out.println("From Remove:"+jokey);
        } else {
            System.out.println(jokey + "Not Found. Not Deleted");
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
        jokey = null;
        return jokey;
    }

    @Override
    public Jokey update(Jokey jokey) {
        PreparedStatement ps = null;
        String sql =    "UPDATE jokey \n" +
                        "SET    name = ? \n" +
                        "WHERE  id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString   (1, jokey.getName());
            ps.setInt      (2, jokey.getId());
            if (ps.executeUpdate() > 0 ){
                System.out.println("From Update:"+jokey);
            } else {
                System.out.println(jokey + "Not Found. Not Updated");
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
        return jokey;
    }

    public Jokey lookFor(Jokey jokey) {
        PreparedStatement ps = null;
        Jokey jokeyRet = new Jokey();
        String sql = "SELECT id,name FROM jokey WHERE name = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, jokey.getName());
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();
            if (resultSet.next()){
                jokeyRet.setId(resultSet.getInt("id"));
                jokeyRet.setName(resultSet.getString("name"));
                System.out.println("From LookFor: "+jokeyRet);
            } else {
                System.out.println("From LookFor: "+jokey + "\nNot Found");
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
        return jokeyRet;
    }
}

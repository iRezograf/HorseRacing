package dao;

import dao.interfaces.IIppoDAO;
import entity.Ippo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static racing.Solution.con;

public class IppoDAO implements IIppoDAO {

    @Override
    public List<Object> getIppoes() {
        Ippo ippo = null;
        Statement ps = null;
        String sql = "SELECT id,name FROM ippo ";
        List<Object> ippodromes = new ArrayList<>();
        try {
            ps = con.createStatement();
            ResultSet resultSet = ps.executeQuery(sql);
            while (resultSet.next()){
                ippo = new Ippo();
                ((Ippo) ippo).setId(resultSet.getInt("id"));
                ((Ippo) ippo).setName(resultSet.getString("name"));
                ippodromes.add((Ippo) ippo);
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
        System.out.println("getStudes:"+ippo);
        return ippodromes;
    }

    @Override
    public Ippo get(int id) {
        Ippo ippo = null;
        PreparedStatement ps = null;
        String sql = "SELECT id,name FROM ippo WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();
            resultSet.next();
            ippo = new Ippo();
            ippo.setId(resultSet.getInt("id"));
            ippo.setName(resultSet.getString("name"));
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
        System.out.println("get"+ippo);
        return ippo;
    }

    @Override
    public Object save(Object obj) {
        Ippo ippo = (Ippo) obj;
        PreparedStatement ps = null;
        String sql = "INSERT INTO ippo (name) VALUES (?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ippo.getName());
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
        System.out.println("Save:"+ippo);
        return ippo;
    }

    @Override
    public Object remove(Object obj){
        Ippo ippo = (Ippo) obj;
        PreparedStatement ps = null;
        String sql = "DELETE FROM ippo WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, ippo.getId());
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
        ippo = null;
        System.out.println("Remove:"+ippo);
        return ippo;
    }


    public Ippo lookFor(Ippo ippo) {
        PreparedStatement ps = null;
        String sql = "SELECT id,name FROM ippo WHERE name = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ippo.getName());
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();
            resultSet.next();

            ippo.setId(resultSet.getInt("id"));
            ippo.setName(resultSet.getString("name"));
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
        System.out.println("LookFor:"+ippo);
        return ippo;
    }

    @Override
    public Object update(Object obj) {
        Ippo ippo = (Ippo) obj;
        PreparedStatement ps = null;
        String sql =    "UPDATE [dbo].[ippo] \n" +
                "SET [name] = ? \n" +
                "WHERE [id] = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString   (1, ippo.getName());
            ps.setInt      (2, ippo.getId());

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
        System.out.println("Update:"+ippo);
        return ippo;
    }
}

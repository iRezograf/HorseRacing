package dao;

import dao.interfaces.IStudDAO;
import entity.Stud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static racing.Solution.con;

public class StudDAO implements IStudDAO {

    @Override
    public List<Object> getStudes() {
        Stud stud = null;
        Statement ps = null;
        String sql = "SELECT id,name FROM stud ";
        List<Object> studs = new ArrayList<>();
        try {
            ps = con.createStatement();
            ResultSet resultSet = ps.executeQuery(sql);
            while (resultSet.next()){
                stud = new Stud();
                ((Stud) stud).setId(resultSet.getInt("id"));
                ((Stud) stud).setName(resultSet.getString("name"));
                studs.add((Stud) stud);
                System.out.println("getStudes:"+stud);
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

        return studs;
    }

    @Override
    public Stud get(int id) {
        Stud stud = null;
        PreparedStatement ps = null;
        String sql = "SELECT id,name FROM stud WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();
            if (resultSet.next()) {
                stud = new Stud();
                stud.setId(resultSet.getInt("id"));
                stud.setName(resultSet.getString("name"));
                System.out.println("From get:"+stud);
            } else {
                System.out.println("From get (records not found):"+stud);
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
        return stud;
    }

    @Override
    public Object save(Object obj) {
        Stud stud = (Stud) obj;
        PreparedStatement ps = null;
        String sql = "INSERT INTO stud (name) VALUES (?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, stud.getName());
            if (ps.executeUpdate() > 0 ){
                System.out.println("From Save:"+stud);
            }
                else {
                    System.out.println("From Save (not saved):"+stud);
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
        return stud;
    }

    @Override
    public Object remove(Object obj){
        Stud stud = (Stud) obj;
        PreparedStatement ps = null;
        String sql = "DELETE FROM stud WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, stud.getId());
            if (ps.executeUpdate() > 0 ){
                stud = null;
                System.out.println("From Remove:"+stud);
            }
            else {
                System.out.println("From Remove (not removed):"+stud);
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
        return stud;
    }


    public Stud lookFor(Stud stud) {
        PreparedStatement ps = null;
        String sql = "SELECT id,name FROM stud WHERE name = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, stud.getName());
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();
            if (resultSet.next()){
                stud.setId(resultSet.getInt("id"));
                stud.setName(resultSet.getString("name"));
                System.out.println("LookFor: "+stud);
            } else {
                System.out.println("LookFor (records not found): "+stud);
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
        return stud;
    }

    @Override
    public Object update(Object obj) {
        Stud stud = (Stud) obj;
        PreparedStatement ps = null;
        String sql =    "UPDATE stud " +
                "SET name = ? " +
                "WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString   (1, stud.getName());
            ps.setInt      (2, stud.getId());
            if (ps.executeUpdate() > 0 ){
                System.out.println("From Update:"+stud);
            }
            else {
                System.out.println("From Update (not updated):"+stud);
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
        return stud;
    }
}

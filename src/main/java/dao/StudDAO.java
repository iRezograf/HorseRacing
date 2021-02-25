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
                /**Stud stud = new Stud(resultSet.getInt("id"), resultSet.getString("name"));*/
                stud = new Stud();
                ((Stud) stud).setId(resultSet.getInt("id"));
                ((Stud) stud).setName(resultSet.getString("name"));
                studs.add((Stud) stud);
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
        System.out.println("getStudes:"+stud);
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
            resultSet.next();
            /**stud = new Stud(resultSet.getInt("id"), resultSet.getString("name"));*/
            stud = new Stud();
            stud.setId(resultSet.getInt("id"));
            stud.setName(resultSet.getString("name"));
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
        System.out.println("get"+stud);
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
        System.out.println("Save:"+stud);
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
        stud = null;
        System.out.println("Remove:"+stud);
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
            resultSet.next();

            stud.setId(resultSet.getInt("id"));
            stud.setName(resultSet.getString("name"));
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
        System.out.println("LookFor:"+stud);
        return stud;
    }

    @Override
    public Object update(Object obj) {
        Stud stud = (Stud) obj;
        PreparedStatement ps = null;
        String sql =    "UPDATE [dbo].[stud] \n" +
                "SET [name] = ? \n" +
                "WHERE [id] = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString   (1, stud.getName());
            ps.setInt      (2, stud.getId());

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
        System.out.println("Update:"+stud);
        return stud;
    }
}

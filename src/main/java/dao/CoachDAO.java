package dao;

import dao.interfaces.ICoachDAO;
import entity.Coach;
import entity.Stud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static racing.Solution.con;

public class CoachDAO implements ICoachDAO {

    @Override
    public List<Object> getCoaches() {
        Coach coach = null;
        Statement ps = null;
        String sql = "SELECT id,name FROM coach ";
        List<Object> coaches = new ArrayList<>();
        try {
            ps = con.createStatement();
            ResultSet resultSet = ps.executeQuery(sql);
            while (resultSet.next()){
                coach = new Coach();
                coach.setId(resultSet.getInt("id"));
                coach.setName(resultSet.getString("name"));
                coaches.add(coach);
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
        System.out.println("getCoaches:"+coaches);
        return coaches;
    }

    @Override
    public Coach get(int id) {
        Coach coach = null;
        PreparedStatement ps = null;
        String sql = "SELECT id,name FROM coach WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();
            resultSet.next();
            /**coach = new Coach(resultSet.getInt("id"), resultSet.getString("name"));*/
            coach = new Coach();
            coach.setId(resultSet.getInt("id"));
            coach.setName(resultSet.getString("name"));
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
        System.out.println("get:"+coach);
        return coach;
    }

    @Override
    public Object save(Object obj) {
        Coach coach = (Coach) obj;
        PreparedStatement ps = null;
        String sql = "INSERT INTO coach (name) VALUES (?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, coach.getName());
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
        System.out.println("Save:"+coach);
        return coach;
    }

    @Override
    public Object remove(Object obj){
        Coach coach = (Coach) obj;
        PreparedStatement ps = null;
        String sql = "DELETE TOP (10) FROM coach WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, coach.getId());
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
        coach = null;
        System.out.println("Remove:"+coach);
        return coach;
    }

    @Override
    public Object update(Object obj) {
        Coach coach = (Coach) obj;
        PreparedStatement ps = null;
        String sql =    "UPDATE [dbo].[coach] \n" +
                "SET [name] = ? \n" +
                "WHERE [id] = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString   (1, coach.getName());
            ps.setInt      (2, coach.getId());

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
        System.out.println("Update:"+coach);
        return coach;
    }

    public Coach lookFor(Coach coach) {
        PreparedStatement ps = null;
        String sql = "SELECT id,name FROM coach WHERE name = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, coach.getName());
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();
            resultSet.next();

            coach.setId(resultSet.getInt("id"));
            coach.setName(resultSet.getString("name"));
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
        System.out.println("LookFor:"+coach);
        return coach;
    }
    
}

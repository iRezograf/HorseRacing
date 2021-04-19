package dao;

import dao.interfaces.ICoachDAO;
import entity.Coach;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static racing.Solution.con;

public class CoachDAO implements ICoachDAO {

    @Override
    public List<Coach> getCoaches() {
        Coach coach;
        Statement ps = null;
        String sql = "SELECT id,name FROM coach ";
        List<Coach> coaches = new ArrayList<>();
        try {
            ps = con.createStatement();
            ResultSet resultSet = ps.executeQuery(sql);
            while (resultSet.next()){
                coach = new Coach();
                coach.setId(resultSet.getInt("id"));
                coach.setName(resultSet.getString("name"));
                coaches.add(coach);
                System.out.println("From getCoaches: "+coach);
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
            if (resultSet.next()){
                coach = new Coach();
                coach.setId(resultSet.getInt("id"));
                coach.setName(resultSet.getString("name"));
                System.out.println("From get: "+coach);
            } else {
                System.out.println("From get (not found): "+ coach);
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
        return coach;
    }

    @Override
    public Coach save(Coach coach) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO coach (name) VALUES (?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, coach.getName());
            if (ps.executeUpdate() > 0 ){
                System.out.println("From Save: "+coach);
            } else {
                System.out.println("From Save (not saved): "+coach);
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
        return coach;
    }

    @Override
    public Coach remove(Coach coach){
        PreparedStatement ps = null;
        String sql = "DELETE TOP (10) FROM coach WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, coach.getId());
            if (ps.executeUpdate() > 0 ){
                coach = null;
                System.out.println("From Remove: "+coach);
            } else {
                System.out.println("From Remove: (not removed)"+coach);
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
        return coach;
    }

    @Override
    public Coach update(Coach coach) {
        PreparedStatement ps = null;
        String sql =    "UPDATE [dbo].[coach] \n" +
                "SET [name] = ? \n" +
                "WHERE [id] = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString   (1, coach.getName());
            ps.setInt      (2, coach.getId());
            if (ps.executeUpdate() > 0 ){
                System.out.println("From Update: "+coach);
            } else {
                System.out.println("From Update (not updated): "+coach);
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
            if (resultSet.next()){
                coach.setId(resultSet.getInt("id"));
                coach.setName(resultSet.getString("name"));
                System.out.println("From LookFor: "+coach);
            } else {
                System.out.println("From LookFor (not found): "+coach);
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
        return coach;
    }
}

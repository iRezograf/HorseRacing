package dao;

import dao.interfaces.IHorseDAO;
import entity.Horse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static racing.Solution.con;

public class HorseDAO implements IHorseDAO {

    @Override
    public List<Object> getHorses() {
        Horse horse = null;
        Statement ps = null;
        String sql = "SELECT id,name FROM horse ";
        List<Object> horses = new ArrayList<>();
        try {
            ps = con.createStatement();
            ResultSet resultSet = ps.executeQuery(sql);
            while (resultSet.next()){
                /**Horse horse = new Horse(resultSet.getInt("id"), resultSet.getString("name"));*/
                horse = new Horse();
                ((Horse) horse).setId(resultSet.getInt("id"));
                ((Horse) horse).setName(resultSet.getString("name"));
                horses.add((Horse) horse);
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
        System.out.println("getHorses:"+horses);
        return horses;
    }

    @Override
    public Horse get(int id) {
        Horse horse = null;
        PreparedStatement ps = null;
        String sql = "SELECT id,name FROM horse WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();
            resultSet.next();
            horse = new Horse();
            horse.setId(resultSet.getInt("id"));
            horse.setName(resultSet.getString("name"));
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
        System.out.println("get:"+horse);
        return horse;
    }

    @Override
    public Object save(Object obj) {
        Horse horse = (Horse) obj;
        PreparedStatement ps = null;
        String sql = "INSERT INTO horse (name, birth, sex, id_stud) " +
                "VALUES (?, ?, ?,  ? )";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, horse.getName());
            ps.setDate(2, horse.getBirth());
            ps.setString(3, horse.getSex());
            ps.setInt(4, horse.getIdStud());

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
        System.out.println("Save:"+horse);
        return horse;
    }

    @Override
    public Object remove(Object obj){
        Horse horse = (Horse) obj;
        PreparedStatement ps = null;
        String sql = "DELETE FROM horse WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, horse.getId());
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
        horse = null;
        System.out.println("Remove:"+horse);
        return horse;
    }


    public Horse lookFor(Horse horse) {
        PreparedStatement ps = null;
        String sql = "SELECT id,name FROM horse WHERE name = ? AND birth = ? AND sex = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, horse.getName());
            ps.setDate(2, horse.getBirth());
            ps.setString(3, horse.getSex());
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();
            resultSet.next();

            horse.setId(resultSet.getInt("id"));
            horse.setName(resultSet.getString("name"));
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
        System.out.println("LookFor:"+horse);
        return horse;
    }

    @Override
    public Object update(Object obj) {
        Horse horse = (Horse) obj;
        PreparedStatement ps = null;
        String sql =    "UPDATE [dbo].[horse] \n" +
                "SET [name] = ? \n" +
                "WHERE [id] = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString   (1, horse.getName());
            ps.setInt      (2, horse.getId());

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
        System.out.println("Update:"+horse);
        return horse;
    }
}
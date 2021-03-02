package dao;

import dao.interfaces.ITypeBetDAO;
import entity.TypeBet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static racing.Solution.con;

public class TypeBetDAO implements ITypeBetDAO {
    
    @Override
    public List<Object> GetTypeBets() {
        TypeBet typeBet = null;
        Statement ps = null;
        String sql = "SELECT * FROM type_bet ";
        List<Object> typeBets = new ArrayList<>();
        try {
            ps = con.createStatement();
            ResultSet resultSet = ps.executeQuery(sql);
            while (resultSet.next()){
                typeBet = new TypeBet();
                ((TypeBet) typeBet).setId(resultSet.getInt(1));
                ((TypeBet) typeBet).setTypeBet(resultSet.getString(2));
                ((TypeBet) typeBet).setRate(resultSet.getDouble(3));
                typeBets.add((TypeBet) typeBet);
                System.out.println("getHorses:"+typeBet);
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
        return typeBets;
    }

    @Override
    public TypeBet get(int id) {
        TypeBet typeBet = null;
        PreparedStatement ps = null;
        String sql = "SELECT * FROM type_bet WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();
            if (resultSet.next()){
                typeBet = new TypeBet();
                ((TypeBet) typeBet).setId(resultSet.getInt(1));
                ((TypeBet) typeBet).setTypeBet(resultSet.getString(2));
                ((TypeBet) typeBet).setRate(resultSet.getDouble(3));
                System.out.println("From get:"+typeBet);
            }else {
                System.out.println("From get (records not found):"+typeBet);
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
        return typeBet;
    }

    @Override
    public Object save(Object obj) {
        TypeBet typeBet = (TypeBet) obj;
        PreparedStatement ps = null;
        String sql = "INSERT INTO type_bet (type_bet, rate) " +
                "VALUES (?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, typeBet.getTypeBet());
            ps.setDouble(2, typeBet.getRate());

            if (ps.executeUpdate() > 0 ){
                System.out.println("Form Save:"+typeBet);
            }
            else {
                System.out.println("Form Save (not saved):"+typeBet);
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
        return typeBet;
    }

    @Override
    public Object remove(Object obj){
        TypeBet typeBet = (TypeBet) obj;
        PreparedStatement ps = null;
        String sql = "DELETE FROM type_bet WHERE id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, typeBet.getId());
            if (ps.executeUpdate() > 0 ){
                typeBet = null;
                System.out.println("Remove:"+typeBet);
            }
            else {
                System.out.println("Remove (not removed):"+typeBet);
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
        return typeBet;
    }


    public TypeBet lookFor(TypeBet typeBet) {
        PreparedStatement ps = null;
        String sql = "SELECT * FROM type_bet WHERE type_bet = ? AND rate = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, typeBet.getTypeBet());
            ps.setDouble(2, typeBet.getRate());
            ps.executeQuery();

            ResultSet resultSet = ps.getResultSet();
            if (resultSet.next()) {
                typeBet.setId(resultSet.getInt(1));
                typeBet.setTypeBet(resultSet.getString(2));
                typeBet.setRate(resultSet.getDouble(3));
                System.out.println("From LookFor:"+typeBet);
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
        return typeBet;
    }

    @Override
    public Object update(Object obj) {
        TypeBet typeBet = (TypeBet) obj;
        PreparedStatement ps = null;
        String sql =    "UPDATE type_bet \n" +
                "SET type_bet  = ?, \n" +
                " rate = ? \n" +
                "WHERE  id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString   (1, typeBet.getTypeBet());
            ps.setDouble   (2, typeBet.getRate());
            ps.setInt      (3, typeBet.getId());

            if (ps.executeUpdate() > 0 ){
                System.out.println("From Update:"+typeBet);
            }
            else {
                System.out.println("From Update (not updated):"+typeBet);
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
        return typeBet;
    }
}

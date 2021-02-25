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
        System.out.println("getHorses:"+typeBets);
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
            resultSet.next();
            typeBet = new TypeBet();
            ((TypeBet) typeBet).setId(resultSet.getInt(1));
            ((TypeBet) typeBet).setTypeBet(resultSet.getString(2));
            ((TypeBet) typeBet).setRate(resultSet.getDouble(3));
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
        System.out.println("get:"+typeBet);
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
            /*((TypeBet) typeBet).setId(resultSet.getInt(1));
                ((TypeBet) typeBet).setTypeBet(resultSet.getString(2));
                ((TypeBet) typeBet).setRate(resultSet.getDouble(3));*/
            ps.setString(1, typeBet.getTypeBet());
            ps.setDouble(2, typeBet.getRate());

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
        System.out.println("Save:"+typeBet);
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
        typeBet = null;
        System.out.println("Remove:"+typeBet);
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
            resultSet.next();

            typeBet.setId(resultSet.getInt(1));
            typeBet.setTypeBet(resultSet.getString(2));
            typeBet.setRate(resultSet.getDouble(3));

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
        System.out.println("LookFor:"+typeBet);
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
        System.out.println("Update:"+typeBet);
        return typeBet;
    }
}

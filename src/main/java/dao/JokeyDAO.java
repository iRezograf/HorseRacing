package dao;

import dao.interfaces.IJokeyDAO;
import entity.Jokey;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static racing.Solution.*;

public class JokeyDAO implements IJokeyDAO {

    @Override
    public List<Jokey> getJokeys() {
        Jokey jokey = null;
        Statement st = null;
        String sql = "SELECT id,name FROM jokey ";
        List<Jokey> jokeys = new ArrayList<Jokey>();
        try {
            st = con.createStatement();
            ResultSet resultSet = st.executeQuery(sql);
            while (resultSet.next()){
                /**Jokey jokey = new Jokey(resultSet.getInt("id"), resultSet.getString("name"));*/
                jokey = new Jokey();
                jokey.setId(resultSet.getInt("id"));
                jokey.setName(resultSet.getString("name"));
                jokeys.add(jokey);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jokeys;
    }

    @Override
    public Jokey get(Long id) {
        Jokey jokey = null;
        PreparedStatement pst = null;
        String sql = "SELECT id,name FROM jokey WHERE id = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setLong(1, id);
            pst.executeQuery();
            ResultSet resultSet = pst.getResultSet();
            resultSet.next();
            /**jokey = new Jokey(resultSet.getLong("id"), resultSet.getString("name"));*/
            jokey = new Jokey();
            jokey.setId(resultSet.getInt("id"));
            jokey.setName(resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return jokey;
    }

    @Override
    public Jokey save(Jokey jokey) {
        /** INSERT INTO [dbo].[jokey]
         ([name])
         VALUES
         (<name, varchar(100),>)*/
        PreparedStatement pst = null;
        String sql = "INSERT INTO jokey (name) VALUES (?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, jokey.getName());
            pst.executeUpdate();
            ResultSet resultSet = pst.getResultSet();
            /*resultSet.next();*/
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return jokey;
    }

    @Override
    public Jokey remove(Long id) {
        return null;
    }

    @Override
    public Jokey update(Jokey jokey, Long id) {
        return jokey;
    }


}

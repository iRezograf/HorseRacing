package dao;

import dao.interfaces.IJokeyDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static db.Solution.*;

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
    public Jokey get(int id) {
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
    public Object save(Object obj) {
        return null;
    }

    @Override
    public Object remove(Object id) {
        return null;
    }

    @Override
    public Object update(Object obj, Object id) {
        return null;
    }
}

package dao;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static db.Solution.*;

public class JokeyDAO implements IJokeyDAO {
        Connection con = null;
        PreparedStatement pst = null;
        Statement st = null;


    {
        try {
            con = DriverManager.getConnection(URL, USER,PASSWORD);
            st = con.createStatement();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public List<Jokey> getJokeys() {
        String sql = "SELECT id,name FROM jokey ";
        List<Jokey> jokeys = new ArrayList<Jokey>();
        try {
            ResultSet resultSet = st.executeQuery(sql);
            while (resultSet.next()){
                Jokey jokey = new Jokey(resultSet.getLong("id"), resultSet.getString("name"));
                jokeys.add(jokey);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return jokeys;
    }

    @Override
    public Jokey get(int id) {
        Jokey jokey = null;
        String sql = "SELECT id,name FROM jokey WHERE id = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setLong(1, id);
            pst.executeQuery();
            ResultSet resultSet = pst.getResultSet();
            resultSet.next();
            jokey = new Jokey(resultSet.getLong("id"), resultSet.getString("name"));
            //jokey.id = resultSet.getLong(1);
            //jokey.name = resultSet.getString(2);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {

        }
        System.out.println(jokey);
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

    @Override
    public Object get(Long id) {
        return null;
    }
}

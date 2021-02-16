package dao;

import dao.interfaces.IPlayerDAO;
import entity.Player;

/**static дописываем, чтобы увидеть и статические переменные из Solution*/
import static racing.Solution.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO implements IPlayerDAO {

    @Override
    public Player save(Player player) {
        /** insert player */
        return player;
    }

    @Override
    public Player remove(Long id) {
        /** delete */
        return null;
    }

    @Override
    public Player update(Player player, Long id) {
        /** update */
        return null;
    }

    @Override
    public List<Player> getPlayers() {
        Player player = null;
        Statement st = null;
        String sql = "SELECT [id]\n" +
                "      ,[first_n]\n" +
                "      ,[last_n]\n" +
                "      ,[login]\n" +
                "      ,[password]\n" +
                "  FROM [player]";
        List<Player> players = new ArrayList<Player>();
        try {
            st = con.createStatement();
            ResultSet resultSet = st.executeQuery(sql);
            while (resultSet.next()){
                /**Jokey jokey = new Jokey(resultSet.getInt("id"), resultSet.getString("name"));*/
                player = new Player();
                player.setId(resultSet.getInt("id"));
                player.setFirstName(resultSet.getString(2));
                player.setLastName(resultSet.getString(3));
                player.setLogin(resultSet.getString(4));
                player.setPassword(resultSet.getString(5));
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    public Player get(Long id) {
       String sql = "SELECT [id]\n" +
        "      ,[first_n]\n" +
        "      ,[last_n]\n" +
        "      ,[login]\n" +
        "      ,[password]\n" +
        "  FROM [dbo].[player] WHERE [id] = ?";
        PreparedStatement st = null;
        Player player = null;
        try {
            st = con.prepareStatement(sql);
            st.setLong(1, id);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            rs.next();
                player = new Player();
                player.setId(rs.getInt(1));
                player.setFirstName(rs.getString(2));
                player.setLastName(rs.getString(3));
                player.setLogin(rs.getString(4));
                player.setPassword(rs.getString(5));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return player;
    }
}

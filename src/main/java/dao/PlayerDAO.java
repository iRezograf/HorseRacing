package dao;

import dao.interfaces.IPlayerDAO;
import entity.BetsOfPlayer;
import entity.Jokey;
import entity.Player;
import jdk.jfr.StackTrace;
import org.testng.annotations.Test;

/**static дописываем, чтобы увидеть и статические переменные из Solution*/
import static entity.Solution.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO implements IPlayerDAO {
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
    public List<BetsOfPlayer> bets_of_player(int id) {
        String sql = "SELECT dbo.player.id, dbo.player.last_n, dbo.player.first_n, dbo.player_bet.date_ride,\n"+
                " dbo.player_bet.num_ride, dbo.horse.name AS horse, dbo.type_bet.type_bet, dbo.player_bet.bet AS [bet(roubles)], dbo.type_bet.rate, \n" +
                " dbo.player_bet.payout\n" +
                "FROM dbo.player INNER JOIN\n" +
                " dbo.player_bet ON dbo.player.id = dbo.player_bet.id_player INNER JOIN\n" +
                " dbo.horse ON dbo.player_bet.id_horse = dbo.horse.id INNER JOIN\n" +
                " dbo.type_bet ON dbo.player_bet.id_type_bet = dbo.type_bet.id\n" +
                "WHERE (dbo.player.id = ?)";
        PreparedStatement st = null;
        Player player = null;
        List<BetsOfPlayer> betsOfPlayers = null;
        try {
            st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            betsOfPlayers = new ArrayList<>();
            while (rs.next()){
                BetsOfPlayer betsOfPlayer = new BetsOfPlayer();
                betsOfPlayer.setId(rs.getInt(1));
                betsOfPlayer.setLast_n(rs.getString(2));
                betsOfPlayer.setFirst_n(rs.getString(3));
                betsOfPlayer.setDate_ride(rs.getString(4));
                betsOfPlayer.setNum_ride(rs.getString(5));
                betsOfPlayer.setHorse(rs.getString(6));
                betsOfPlayer.setType_bet(rs.getString(7));
                betsOfPlayer.setBet(rs.getString(8));
                betsOfPlayer.setRate(rs.getString(9));
                betsOfPlayer.setPayout(rs.getString(10));

                betsOfPlayers.add(betsOfPlayer);
            }

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
        return betsOfPlayers;
    }

    @Override
    public Player get(int id) {
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
            st.setInt(1, id);
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

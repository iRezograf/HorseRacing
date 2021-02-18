package dao;

import dao.interfaces.IPlayerBetDAO;
import entity.BetsOfPlayer;
import entity.Player;
import static racing.Solution.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerBetDAO implements IPlayerBetDAO {

    @Override
    public List<BetsOfPlayer> getBetsOfPlayerOnDate(Date date, Long id) {
        String sql = "SELECT dbo.player.id, dbo.player.last_n, dbo.player.first_n,\n"+
        "dbo.player_bet.date_ride, dbo.player_bet.num_ride, dbo.horse.name AS horse,\n"+
        "dbo.type_bet.type_bet, dbo.player_bet.bet AS [bet(roubles)], dbo.type_bet.rate, \n" +
        "dbo.player_bet.payout\n" +
        "FROM dbo.player INNER JOIN\n" +
        " dbo.player_bet ON dbo.player.id = dbo.player_bet.id_player INNER JOIN\n" +
        " dbo.horse ON dbo.player_bet.id_horse = dbo.horse.id INNER JOIN\n" +
        " dbo.type_bet ON dbo.player_bet.id_type_bet = dbo.type_bet.id\n" +
        "WHERE        (dbo.player.id = ?) AND (dbo.player_bet.date_ride = ?)";
        //" CONVERT(DATETIME, ?, 102))";
        PreparedStatement st = null;
        Player player = null;
        List<BetsOfPlayer> betsOfPlayers = null;
        try {
            st = con.prepareStatement(sql);
            st.setLong(1, id);
            st.setDate(2, date);
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
    public BetsOfPlayer save(BetsOfPlayer betsOfPlayer) {

        return null;
    }

    @Override
    public BetsOfPlayer remove(Long id) {
        return null;
    }

    @Override
    public BetsOfPlayer update(BetsOfPlayer player, Long id) {
        return null;
    }

    @Override
    /** method get didn't need for entity dbo.player_bet */
    public BetsOfPlayer get(Long id) {
        System.out.println("--- method get didn't need for entity dbo.player_bet ---");
        return null;
    }

    public BetsOfPlayer getBetsOfPlayerOnDateNumHorseBet(
            Long player_id, Date date, Long num,Long horse_id, Long bet_id) {
        String sql = "SELECT dbo.player.id, dbo.player.last_n, dbo.player.first_n,\n"+
        "dbo.player_bet.date_ride, dbo.player_bet.num_ride, dbo.horse.name AS horse,\n"+
        "dbo.type_bet.type_bet, dbo.player_bet.bet AS [bet(roubles)], dbo.type_bet.rate, \n" +
        "dbo.player_bet.payout\n" +
        "FROM dbo.player INNER JOIN\n" +
        " dbo.player_bet ON dbo.player.id = dbo.player_bet.id_player INNER JOIN\n" +
        " dbo.horse ON dbo.player_bet.id_horse = dbo.horse.id INNER JOIN\n" +
        " dbo.type_bet ON dbo.player_bet.id_type_bet = dbo.type_bet.id\n" +
        "WHERE  (dbo.player.id = ?)\n"+
                " AND (dbo.player_bet.date_ride = ?)\n"+
                " AND (dbo.player_bet.num_ride = ?)\n"+
                " AND (dbo.player_bet.id_horse = ?)\n"+
                " AND (dbo.player_bet.id_type_bet = ?)";
        PreparedStatement st = null;
        BetsOfPlayer betsOfPlayer = null;
        try {
            st = con.prepareStatement(sql);
            st.setLong(1, player_id);
            st.setDate(2, date);
            st.setLong(3, num);
            st.setLong(4, horse_id);
            st.setLong(5, bet_id);
            st.executeQuery();
            ResultSet rs = st.getResultSet();
            rs.next();
            betsOfPlayer = new BetsOfPlayer();
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
        return betsOfPlayer;
    }
}
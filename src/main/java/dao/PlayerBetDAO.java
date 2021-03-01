package dao;

import dao.interfaces.IPlayerBetDAO;
import entity.PlayerBet;
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
    public List<PlayerBet> getPlayerBets(PlayerBet playerBet) {
        String sql = "SELECT  "+
                "       player.id, \n" +
                "       player.last_n, \n" +
                "       player.first_n, \n" +
                "       player_bet.date_ride, \n" +
                "       player_bet.num_ride, \n" +
                "       horse.name AS horse, \n" +
                "       type_bet.type_bet, \n" +
                "       player_bet.bet AS [bet(roubles)], \n" +
                "       type_bet.rate,              \n" +
                "       player_bet.payout\n" +
                "FROM    player INNER JOIN\n" +
                "        player_bet ON player.id = player_bet.id_player INNER JOIN\n" +
                "        horse ON player_bet.id_horse = horse.id INNER JOIN\n" +
                "        type_bet ON player_bet.id_type_bet = type_bet.id\n" +
                "WHERE        (player.id = ?)" +
                "AND (player_bet.date_ride = ?)";


        PreparedStatement ps = null;
        Player player = null;
        List<PlayerBet> playerBets = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, playerBet.getId());
            ps.setDate(2, playerBet.getDateRide());
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            playerBets = new ArrayList<>();
            while (rs.next()){
                playerBet = new PlayerBet();
                playerBet.setId(rs.getInt(1));
                playerBet.setLastName(rs.getString(2));
                playerBet.setFirstName(rs.getString(3));
                playerBet.setDateRide(rs.getDate(4));
                playerBet.setNumRide(rs.getInt(5));
                playerBet.setHorse(rs.getString(6));
                playerBet.setTypeBet(rs.getString(7));
                playerBet.setBet(rs.getInt(8));
                playerBet.setRate(rs.getDouble(9));
                playerBet.setPayout(rs.getInt(10));
                playerBets.add(playerBet);
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
        System.out.println("From getPlayerBets:");
        for (PlayerBet pl: playerBets ) {
            System.out.println(pl);
        }
        return playerBets;
    }

    public PlayerBet getPlayerBet(PlayerBet playerBet) {
            String sql = "SELECT " +
                    " player.id,  " +
                    " player.last_n,  " +
                    " player.first_n,  " +
                    " ippo.id AS ippo_id,  " +
                    " ippo.name,  " +
                    " player_bet.date_ride,  " +
                    " player_bet.num_ride,  " +
                    " horse.name AS horse,  " +
                    " type_bet.type_bet,                           " +
                    " player_bet.bet AS [bet(roubles)],  " +
                    " type_bet.rate,  " +
                    " player_bet.payout,  " +
                    " type_bet.id AS type_bet_id,  " +
                    " horse.id AS horse_id " +
                    "FROM             " +
                    " player INNER JOIN                          " +
                    " player_bet ON player.id = player_bet.id_player INNER JOIN                          " +
                    " horse ON player_bet.id_horse = horse.id INNER JOIN                          " +
                    " type_bet ON player_bet.id_type_bet = type_bet.id INNER JOIN                          " +
                    " ippo ON player_bet.id_ippo = ippo.id " +
                    "WHERE " +
                    "     player_bet.id_player = ? " +
                    " AND player_bet.date_ride = ?" + //" CONVERT(DATETIME, '2021-01-08 00:00:00', 102))  " +
                    " AND player_bet.num_ride  = ?  " +
                    " AND player_bet.id_horse  = ? " +
                    " AND player_bet.id_type_bet = ?  " +
                    " AND player_bet.id_ippo   = ?" ;
                    
        PlayerBet  playerBetRet = null;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);

            ps.setInt (1, playerBet.getId());
            ps.setDate(2, playerBet.getDateRide());
            ps.setInt (3, playerBet.getNumRide());
            ps.setInt (4, playerBet.getIdHorse());
            ps.setInt (5, playerBet.getIdTypeBet());
            ps.setInt (6, playerBet.getIdIppodrom());

            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            if (rs.next()){
                playerBetRet = new PlayerBet();
                playerBetRet.setId(rs.getInt(1));
                playerBetRet.setLastName(rs.getString(2));
                playerBetRet.setFirstName(rs.getString(3));
                playerBetRet.setIdIppodrom(rs.getInt(4));
                playerBetRet.setIppodromName(rs.getString(5));
                playerBetRet.setDateRide(rs.getDate(6));
                playerBetRet.setNumRide(rs.getInt(7));
                playerBetRet.setHorse(rs.getString(8));
                playerBetRet.setTypeBet(rs.getString(9));
                playerBetRet.setBet(rs.getInt(10));
                playerBetRet.setRate(rs.getDouble(11));
                playerBetRet.setPayout(rs.getInt(12));
                playerBetRet.setIdTypeBet(rs.getInt(13));
                playerBetRet.setIdHorse(rs.getInt(14));

                System.out.println("From getPlayerBet:"+playerBetRet);
            } else {
                System.out.println("From getPlayerBet (nothing to updated):"+playerBetRet);
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

        return playerBetRet;
    }
    @Override
    public PlayerBet save(PlayerBet playerBet) {

        String sql = "INSERT INTO player_bet " +
                "           (id_player " +
                "           ,id_ippo " +
                "           ,date_ride " +
                "           ,num_ride " +
                "           ,id_horse " +
                "           ,id_type_bet " +
                "           ,bet " +
                "           ,payout) " +
                "     VALUES " +
                "           (?,?,?,?, ?,?,?,?)";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt (1, playerBet.getId());
            ps.setInt (2, playerBet.getIdIppodrom());
            ps.setDate(3, playerBet.getDateRide());
            ps.setInt (4, playerBet.getNumRide());
            ps.setInt (5, playerBet.getIdHorse());
            ps.setInt (6, playerBet.getIdTypeBet());
            ps.setInt (7, playerBet.getBet());
            ps.setInt (8, playerBet.getPayout());
            int rows = ps.executeUpdate();
            if (rows > 0 ){
                System.out.println("From Save:"+ playerBet+ "\n Bet is added");
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
        return playerBet;
    }

    @Override
    public PlayerBet remove(PlayerBet playerBet) {
        String sql =  "DELETE TOP(1)FROM player_bet "+
                        "WHERE id_player = ? "+
                        " AND  id_ippo = ? " +
                        " AND  date_ride = ? " +
                        " AND  num_ride = ? " +
                        " AND  id_horse = ? " +
                        " AND  id_type_bet = ?";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt (1, playerBet.getId());
            ps.setInt (2, playerBet.getIdIppodrom());
            ps.setDate(3, playerBet.getDateRide());
            ps.setInt (4, playerBet.getNumRide());
            ps.setInt (5, playerBet.getIdHorse());
            ps.setInt (6, playerBet.getIdTypeBet());
            int rows = ps.executeUpdate();
            if (rows > 0 ){
                System.out.println("From Remove:" + playerBet);
                playerBet = null;
            }
            else{
                System.out.println("From Remove (not removed):" + playerBet);
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
        return playerBet;
    }

    @Override
    public PlayerBet update(PlayerBet playerBet) {
        String sql ="UPDATE TOP(1) player_bet " +
                    "SET  bet = ?" +
                        ",payout = ? " +
                    "WHERE id_player = ? " +
                        "AND id_ippo = ? " +
                        "AND date_ride = ? " +
                        "AND num_ride = ? " +
                        "AND id_horse = ? " +
                        "AND id_type_bet = ?";
        PlayerBet playerBetRet = null;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt (1, playerBet.getBet());
            ps.setInt (2, playerBet.getPayout());

            ps.setInt (3, playerBet.getId());
            ps.setInt (4, playerBet.getIdIppodrom());
            ps.setDate(5, playerBet.getDateRide());
            ps.setInt (6, playerBet.getNumRide());
            ps.setInt (7, playerBet.getIdHorse());
            ps.setInt (8, playerBet.getIdTypeBet());

            int rows = ps.executeUpdate();
            if (rows > 0 ){
                playerBetRet = new PlayerBet();
                playerBetRet.setId(playerBet.getId());
                playerBetRet.setIdIppodrom(playerBet.getIdIppodrom());
                playerBetRet.setDateRide(playerBet.getDateRide());
                playerBetRet.setNumRide(playerBet.getNumRide());
                playerBetRet.setIdHorse(playerBet.getIdHorse());
                playerBetRet.setIdTypeBet(playerBet.getIdTypeBet());
                playerBetRet.setBet(playerBet.getBet());
                playerBetRet.setPayout(playerBet.getPayout());
                System.out.println("From Update:"+ playerBetRet);
            } else{
                System.out.println("From Update (not updated):"+ playerBetRet);
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
        return playerBet;
    }
}

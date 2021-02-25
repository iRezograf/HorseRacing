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
        String sql =    "SELECT dbo.player.id, dbo.player.last_n, dbo.player.first_n,\n"+
                        "dbo.player_bet.date_ride, dbo.player_bet.num_ride,\n " +
                        "dbo.player_bet.id_horse, dbo.horse.name AS horse,\n"+
                        "dbo.player_bet.id_type_bet, dbo.type_bet.type_bet, dbo.player_bet.bet\n " +
                        "AS [bet(roubles)],\n " +
                        "dbo.type_bet.rate, dbo.player_bet.payout\n" +
                        "FROM dbo.player INNER JOIN\n" +
                        " dbo.player_bet ON dbo.player.id = dbo.player_bet.id_player INNER JOIN\n" +
                        " dbo.horse ON dbo.player_bet.id_horse = dbo.horse.id INNER JOIN\n" +
                        " dbo.type_bet ON dbo.player_bet.id_type_bet = dbo.type_bet.id\n" +
                        "WHERE        (dbo.player.id = ?) AND (dbo.player_bet.date_ride = ?)";


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
                playerBet.setId(rs.getInt(1));
                playerBet.setLastName(rs.getString(2));
                playerBet.setFirstName(rs.getString(3));
                playerBet.setDateRide(rs.getDate(4));
                playerBet.setNumRide(rs.getInt(5));
                playerBet.setIdHorse(rs.getInt(6));
                playerBet.setHorse(rs.getString(7));
                playerBet.setIdTypeBet(rs.getInt(8));
                playerBet.setTypeBet(rs.getString(9));
                playerBet.setBet(rs.getInt(10));
                playerBet.setRate(rs.getDouble(11));
                playerBet.setPayout(rs.getInt(12));

                playerBets.add(playerBet);
                System.out.println(playerBets);
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
            String sql = "SELECT player.id, " +
                                "player.last_n, " +
                                "player.first_n,\n"+
                                "player_bet.id_ippo,"+
                                "player_bet.date_ride, " +
                                "player_bet.num_ride, " +
                                "horse.name AS horse,\n"+
                                "type_bet.type_bet, " +
                                "player_bet.bet AS [bet(roubles)], " +
                                "type_bet.rate, \n" +
                                "player_bet.payout\n" +
                            "FROM player INNER JOIN\n" +
                                " player_bet ON player.id = player_bet.id_player INNER JOIN\n" +
                                " horse ON player_bet.id_horse = horse.id INNER JOIN\n" +
                                " type_bet ON player_bet.id_type_bet = type_bet.id\n" +
                            "WHERE  " +
                                "     (player_bet.id_player = ?)\n"+
                                " AND (player_bet.date_ride = ?)\n"+
                                " AND (player_bet.num_ride = ?)\n"+
                                " AND (player_bet.id_horse = ?)\n"+
                                " AND (player_bet.id_type_bet = ?\n)"+
                                " AND (player_bet.id_ippo = ?)";

        PlayerBet  playerBet1 = new PlayerBet();;
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
            rs.next();



            playerBet1.setId(rs.getInt(1));
            playerBet1.setLastName(rs.getString(2));
            playerBet1.setFirstName(rs.getString(3));
            playerBet1.setIdIppodrom(rs.getInt(4));
            playerBet1.setIppodromName(playerBet.getIppodromName());
            playerBet1.setDateRide(rs.getDate(5));
            playerBet1.setNumRide(rs.getInt(6));
            playerBet1.setHorse(rs.getString(7));
            playerBet1.setIdHorse(playerBet.getIdHorse());
            playerBet1.setTypeBet(rs.getString(8));
            playerBet1.setBet(rs.getInt(9));
            playerBet1.setTypeBet(playerBet.getTypeBet());
            playerBet1.setRate(rs.getDouble(10));
            playerBet1.setPayout(rs.getInt(11));
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
        System.out.println("From getPlayerBet:"+playerBet1);
        return playerBet1;
    }
    @Override
    public PlayerBet save(PlayerBet playerBet) {

        String sql = "INSERT INTO player_bet\n" +
                "           (id_player\n" +
                "           ,id_ippo\n" +
                "           ,date_ride\n" +
                "           ,num_ride\n" +
                "           ,id_horse\n" +
                "           ,id_type_bet\n" +
                "           ,bet\n" +
                "           ,payout)\n" +
                "     VALUES\n" +
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
        String sql =  "DELETE TOP(1)FROM player_bet\n"+
                        "WHERE id_player = ?\n"+
                        " AND  id_ippo = ?\n" +
                        " AND  date_ride = ?\n" +
                        " AND  num_ride = ?\n" +
                        " AND  id_horse = ?\n" +
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
                System.out.println("From Remove:" + playerBet +"\n Bet is deleted");
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
        playerBet = null;
        return playerBet;
    }

    @Override
    public PlayerBet update(PlayerBet playerBet) {
        String sql ="UPDATE TOP(1) player_bet\n" +
                    "SET  bet = ?\n" +
                        ",payout = ?\n" +
                    "WHERE id_player = ?\n" +
                        "AND id_ippo = ?\n" +
                        "AND date_ride = ?\n" +
                        "AND num_ride = ?\n" +
                        "AND id_horse = ?\n" +
                        "AND id_type_bet = ?";
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
                System.out.println("From Update:"+ playerBet + "\n Bet is updated");
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
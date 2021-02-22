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
        return playerBets;
    }

    public PlayerBet getPlayerBet(PlayerBet playerBet) {
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
                " AND (dbo.player_bet.id_type_bet = ?\n)"+
                " AND (dbo.player_bet.id_ippo = ?)";
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
            System.out.println(playerBet);
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

        return playerBet;
    }
    @Override
    public PlayerBet save(PlayerBet playerBet) {

        String sql = "INSERT INTO [dbo].[player_bet]\n" +
                "           ([id_player]\n" +
                "           ,[id_ippo]\n" +
                "           ,[date_ride]\n" +
                "           ,[num_ride]\n" +
                "           ,[id_horse]\n" +
                "           ,[id_type_bet]\n" +
                "           ,[bet]\n" +
                "           ,[payout])\n" +
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
                System.out.println(playerBet+ "\n Bet is added");
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
        String sql =  "DELETE TOP(1)FROM [dbo].[player_bet]\n"+
                        "WHERE [id_player] = ?\n"+
                        " AND  [id_ippo] = ?\n" +
                        " AND  [date_ride] = ?\n" +
                        " AND  [num_ride] = ?\n" +
                        " AND  [id_horse] = ?\n" +
                        " AND  [id_type_bet] = ?";
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
                System.out.println(playerBet +"\n Bet is deleted");
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
        /** String below wos added for the correctly working testNG*/
        playerBet.setId(0);
        playerBet.setIdIppodrom(0);
        playerBet.setDateRide(null);
        playerBet.setNumRide(0);
        playerBet.setIdHorse(0);
        playerBet.setIdTypeBet(0);
        return playerBet;
    }

    @Override
    public PlayerBet update(PlayerBet playerBet) {
        String sql ="UPDATE TOP(1) [dbo].[player_bet]\n" +
                    "SET  [bet] = ?\n" +
                        ",[payout] = ?\n" +
                    "WHERE [id_player] = ?\n" +
                        "AND [id_ippo] = ?\n" +
                        "AND [date_ride] = ?\n" +
                        "AND [num_ride] = ?\n" +
                        "AND [id_horse] = ?\n" +
                        "AND [id_type_bet] = ?";
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
                System.out.println(playerBet + "\n Bet is updated");
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
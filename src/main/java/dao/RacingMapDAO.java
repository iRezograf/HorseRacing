package dao;

import dao.interfaces.IRacingMap;
import entity.Jokey;
import entity.Player;
import entity.RacingMap;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static racing.Solution.*;

public class RacingMapDAO implements IRacingMap {

    @Override
    public List<RacingMap> getRacingMaps(RacingMap racingMap) {
        PreparedStatement ps = null;
        String sql =
                  "SELECT  dbo.racing_map.id_ippo, dbo.ippo.name AS ippodrom,\n"+
                          "dbo.racing_map.date_ride, dbo.racing_map.num_ride,\n"+
                          "dbo.horse.id AS id_horse, dbo.horse.name AS Horse,\n"+
                          "dbo.horse.birth, dbo.horse.sex,\n"+
                          "dbo.racing_map.id_jokey AS idJokey, dbo.jokey.name AS JokeyName,\n"+
                          "dbo.racing_map.id_coach AS idCoach, dbo.coach.name AS CoachName, \n" +
                          "dbo.racing_map.weight,\n"+
                          "dbo.racing_map.last_ride, dbo.racing_map.distance,\n"+
                          "dbo.racing_map.rating, dbo.racing_map.prize_place\n"+
                  "FROM    dbo.racing_map INNER JOIN\n"+
                          "dbo.ippo ON dbo.racing_map.id_ippo = dbo.ippo.id INNER JOIN\n"+
                          "dbo.horse ON dbo.racing_map.id_horse = dbo.horse.id INNER JOIN\n"+
                          "dbo.jokey ON dbo.racing_map.id_jokey = dbo.jokey.id INNER JOIN\n"+
                          "dbo.coach ON dbo.racing_map.id_coach = dbo.coach.id\n"+
                  "WHERE   (dbo.racing_map.id_ippo = ?) AND (dbo.racing_map.date_ride = ?)";

        List<RacingMap> racingMaps = new ArrayList<RacingMap>();
        try {

            ps = con.prepareStatement(sql);
            ps.setInt(1, racingMap.getId_ippodrom());
            ps.setDate(2, racingMap.getDate_ride());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //RacingMap racingMap = new RacingMap();
                racingMap.setId_ippodrom(rs.getInt(1));
                racingMap.setIppodromName(rs.getString(2));
                racingMap.setDate_ride(rs.getDate(3));
                racingMap.setNum_ride(rs.getInt(4));
                racingMap.setId_horse(rs.getInt(5));
                racingMap.setHorseName(rs.getString(6));
                racingMap.setBirth(rs.getDate(7));
                racingMap.setSex(rs.getString(8));
                racingMap.setId_jokey(rs.getInt(9));
                racingMap.setJokeyName(rs.getString(10));
                racingMap.setId_coach(rs.getInt(11));
                racingMap.setCoachName(rs.getString(12));
                racingMap.setWeight(rs.getInt(13));
                racingMap.setLast_ride(rs.getDate(14));
                racingMap.setDistance(rs.getInt(15));
                racingMap.setRating(rs.getInt(16));
                racingMap.setPrize_place(rs.getInt(17));
                racingMaps.add(racingMap);
                System.out.println("From getRacingMaps:"+racingMap);
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
        return racingMaps;
    }

    @Override
    public RacingMap save(RacingMap racingMap) {
        PreparedStatement ps = null;
        RacingMap racingMapRet = null;
        String sql =    "INSERT INTO racing_map " +
                        "(id_ippo" +
                        ",date_ride" +
                        ",num_ride" +
                        ",id_horse" +
                        ",id_jokey" +
                        ",id_coach" +
                        ",weight" +
                        ",last_ride" +
                        ",distance" +
                        ",rating" +
                        ",prize_place) " +
                        " VALUES (?,?,? ,?,?,?, ?,?,?, ?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt (1, racingMap.getId_ippodrom());
            ps.setDate(2, racingMap.getDate_ride());
            ps.setInt (3, racingMap.getNum_ride());
            ps.setInt (4, racingMap.getId_horse());
            ps.setInt (5, racingMap.getId_jokey());
            ps.setInt (6, racingMap.getId_coach());
            ps.setInt (7, racingMap.getWeight());
            ps.setDate(8,racingMap.getLast_ride());
            ps.setInt (9, racingMap.getDistance());
            ps.setDouble(10, racingMap.getRating());
            ps.setInt (11, racingMap.getPrize_place());

            int rows = ps.executeUpdate();
            if (rows > 0 ){
                System.out.println("From Save:"+ racingMap);
            } else {
                System.out.println("From Save (not added):"+ racingMap);
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
        return racingMap;
    }

    @Override
    public RacingMap remove(RacingMap racingMap) {
                String sql = "DELETE TOP(1) FROM racing_map " +
                            " WHERE id_ippo = ? " +
                            " AND   date_ride   = ?" +
                            " AND   num_ride    = ?" +
                            " AND   id_horse    = ?";
        PreparedStatement ps = null;
        RacingMap racingMapRet = null;
        try {
            ps = con.prepareStatement(sql);

            ps.setInt (1, racingMap.getId_ippodrom());
            ps.setDate(2, racingMap.getDate_ride());
            ps.setInt (3, racingMap.getNum_ride());
            ps.setInt (4, racingMap.getId_horse());
            int cnt = ps.executeUpdate();
            racingMapRet  = new RacingMap();
            if (cnt > 0) {
                System.out.println("From Remove:"+racingMapRet);
                racingMapRet = null;
            }
            else {
                racingMapRet = racingMap;
                System.out.println("From Remove (not removed):"+racingMap);
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
        return racingMapRet;
    }

    @Override
    public RacingMap update(RacingMap racingMap) {
        String sql = "UPDATE racing_map " +
                        "SET id_ippo = ?" +
                        ",date_ride = ?" +
                        ",num_ride = ?" +
                        ",id_horse = ?" +
                        ",id_jokey = ?" +
                        ",id_coach = ?" +
                        ",weight = ?" +
                        ",last_ride = ?" +
                        ",distance = ?" +
                        ",rating = ?" +
                        ",prize_place = ? "+
                        "WHERE id_ippo = ?" +
                        " AND  date_ride = ?" +
                        " AND  num_ride = ?" +
                        " AND  id_horse = ?";
                        /** = 15*/
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            /**SET*/
            ps.setInt (1, racingMap.getId_ippodrom());
            ps.setDate(2, racingMap.getDate_ride());
            ps.setInt (3, racingMap.getNum_ride());
            ps.setInt (4, racingMap.getId_horse());
            ps.setInt (5, racingMap.getId_jokey());
            ps.setInt (6, racingMap.getId_coach());
            ps.setInt (7, racingMap.getWeight());
            ps.setDate(8, racingMap.getLast_ride());
            ps.setInt (9, racingMap.getDistance());
            ps.setDouble(10, racingMap.getRating());
            ps.setInt   (11, racingMap.getPrize_place());
            /**WHERE*/
            ps.setInt (12, racingMap.getId_ippodrom());
            ps.setDate(13, racingMap.getDate_ride());
            ps.setInt (14, racingMap.getNum_ride());
            ps.setInt (15, racingMap.getId_horse());

            int rows = ps.executeUpdate();
            if (rows > 0 ){
                System.out.println("From update: "+ racingMap);
            } else{
                System.out.println("From update: (isn't updated) :" + racingMap);
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
        return racingMap;
    }
}
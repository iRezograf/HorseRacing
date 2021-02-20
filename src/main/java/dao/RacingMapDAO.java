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
    public List<RacingMap> getRacingMaps(int ippo, Date date) {
        PreparedStatement st = null;
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

            st = con.prepareStatement(sql);
            st.setInt(1, curIppo);
            st.setDate(2, curDate);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                RacingMap racingMap = new RacingMap();
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return racingMaps;
    }

    @Override
    public RacingMap save(RacingMap obj) {
        return null;
    }

    @Override
    public RacingMap remove(RacingMap racingMap) {
        return null;
    }

    @Override
    public RacingMap update(RacingMap racingMap) {
        return null;
    }
}

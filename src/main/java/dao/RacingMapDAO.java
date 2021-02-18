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
        /**
         * SELECT  dbo.racing_map.id_ippo, dbo.ippo.name AS ippodrom,
         *         dbo.racing_map.date_ride, dbo.racing_map.num_ride,
         *         dbo.horse.id AS id_horse, dbo.horse.name AS Horse,
         *         dbo.horse.birth, dbo.horse.sex,
         *         dbo.racing_map.id_jokey AS Jokey, dbo.jokey.name,
         *         dbo.racing_map.id_coach AS Coach, dbo.racing_map.weight,
         *         dbo.racing_map.last_ride, dbo.racing_map.distance,
         *         dbo.racing_map.rating, dbo.racing_map.prize_place
         * FROM    dbo.racing_map INNER JOIN
         *         dbo.ippo ON dbo.racing_map.id_ippo = dbo.ippo.id INNER JOIN
         *         dbo.horse ON dbo.racing_map.id_horse = dbo.horse.id INNER JOIN
         *         dbo.jokey ON dbo.racing_map.id_jokey = dbo.jokey.id INNER JOIN
         *         dbo.coach ON dbo.racing_map.id_coach = dbo.coach.id
         * WHERE   (dbo.racing_map.id_ippo = 1)
         * */
        PreparedStatement st = null;
        String sql =    "SELECT [id_ippo]\n" +
                        ",[date_ride]\n" +
                        ",[num_ride]\n" +
                        ",[id_horse]\n" +
                        ",[id_jokey]\n" +
                        ",[id_coach]\n" +
                        ",[weight]\n" +
                        ",[last_ride]\n" +
                        ",[distance]\n" +
                        ",[rating]\n" +
                        ",[prize_place]\n" +
                        "FROM [dbo].[racing_map]\n" +
                        "WHERE [id_ippo] = ?\n" +
                        "AND [date_ride] = ?";
        List<RacingMap> racingMaps = new ArrayList<RacingMap>();
        try {

            st = con.prepareStatement(sql);
            st.setInt(1, curIppo);
            st.setDate(2, curDate);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                RacingMap racingMap = new RacingMap();
                racingMap.setId_ippodrom(rs.getInt(1));
                racingMap.setDate_ride(rs.getDate(2));
                racingMap.setNum_ride(rs.getInt(3));
                racingMap.setId_horse(rs.getInt(4));
                racingMap.setId_jokey(rs.getInt(5));
                racingMap.setId_coach(rs.getInt(6));
                racingMap.setWeight(rs.getInt(7));
                racingMap.setLast_ride(rs.getDate(8));
                racingMap.setDistance(rs.getInt(9));
                racingMap.setRating(rs.getInt(10));
                racingMap.setPrize_place(rs.getInt(11));
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
    public RacingMap remove(int id) {
        return null;
    }

    @Override
    public RacingMap update(RacingMap obj, int id) {
        return null;
    }

    @Override
    public RacingMap get(int id) {
        return null;
    }

}

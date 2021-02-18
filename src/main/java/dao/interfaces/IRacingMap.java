package dao.interfaces;

import entity.BetsOfPlayer;
import entity.RacingMap;

import java.sql.Date;
import java.util.List;

public interface IRacingMap extends ICrud <RacingMap>{
    List<RacingMap> getRacingMaps(int ippo, Date date);
}

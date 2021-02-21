package dao.interfaces;

import entity.RacingMap;

import java.sql.Date;
import java.util.List;

public interface IRacingMap extends ICrud <RacingMap>{
    List<RacingMap> getRacingMaps(RacingMap racingMap);
}

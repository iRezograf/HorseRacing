package dao.interfaces;

import entity.BetsOfPlayer;

import java.sql.Date;
import java.util.List;

public interface IRacingMap extends ICrud{
    List<Object> getRacingMaps(int ippo, Date date);
}

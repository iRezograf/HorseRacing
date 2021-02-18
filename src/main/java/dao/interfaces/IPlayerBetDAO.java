package dao.interfaces;

import entity.BetsOfPlayer;

import java.sql.Date;
import java.util.List;

public interface IPlayerBetDAO extends ICrud <BetsOfPlayer, Long>{
    List<BetsOfPlayer> getBetsOfPlayerOnDate(Date date, Long id);
    BetsOfPlayer getBetsOfPlayerOnDateNumHorseBet(Long player_id,
                                                  Date date,
                                                  Long num,
                                                  Long horse_id,
                                                  Long bet_id);
}

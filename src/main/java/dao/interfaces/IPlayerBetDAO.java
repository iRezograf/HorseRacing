package dao.interfaces;

import entity.BetsOfPlayer;

import java.sql.Date;
import java.util.List;

public interface IPlayerBetDAO extends ICrud <BetsOfPlayer>{
    List<BetsOfPlayer> getBetsOfPlayerOnDate(Date date, int id);
    BetsOfPlayer getBetsOfPlayerOnDateNumHorseBet(int player_id,
                                                  Date date,
                                                  int num,
                                                  int horse_id,
                                                  int bet_id);
}

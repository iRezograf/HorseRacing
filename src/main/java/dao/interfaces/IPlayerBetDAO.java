package dao.interfaces;

import entity.BetsOfPlayer;

import java.sql.Date;
import java.util.List;

public interface IPlayerBetDAO extends ICrud <BetsOfPlayer, Long>{
    List<BetsOfPlayer> getBetsOfPlayers(Date date);
    List<BetsOfPlayer> get(Date date, Long id);
    List<BetsOfPlayer>  bets_of_player(Date date, int id);
}

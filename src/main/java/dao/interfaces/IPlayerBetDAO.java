package dao.interfaces;

import entity.PlayerBet;

import java.sql.Date;
import java.util.List;

public interface IPlayerBetDAO extends ICrud <PlayerBet>{
    List<PlayerBet> getPlayerBets(PlayerBet playerBet);
    PlayerBet getPlayerBet(PlayerBet playerBet);
}

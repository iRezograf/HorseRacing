package dao.interfaces;

import entity.PlayerBet;

import java.sql.Date;
import java.util.List;

public interface IPlayerBetDAO extends ICrud <PlayerBet>{
    List<PlayerBet> getPlayerBets(PlayerBet playerBet);
    PlayerBet getPlayerBet(PlayerBet playerBet);
    /**PlayerBet getPlayerBetOnDateNumHorseBet(int player_id,
                                            Date date,
                                            int num,
                                            int horse_id,
                                            int bet_id,
                                            int idIppo);*/
}

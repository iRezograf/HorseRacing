package dao.interfaces;

import dao.PlayerDAO;
import entity.BetsOfPlayer;
import entity.Player;

import java.util.List;

public interface IPlayerDAO extends ICrud{
    List<BetsOfPlayer>  bets_of_player(int id);
    Player get(int id);
}

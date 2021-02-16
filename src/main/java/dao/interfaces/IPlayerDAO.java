package dao.interfaces;

import dao.PlayerDAO;
import entity.BetsOfPlayer;
import entity.Jokey;
import entity.Player;

import java.util.List;

public interface IPlayerDAO extends ICrud <Player, Long>{
    List<Player> getPlayers();
    Player get(Long id);
}

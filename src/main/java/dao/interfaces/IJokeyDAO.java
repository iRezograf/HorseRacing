package dao.interfaces;

import entity.Jokey;

import java.util.List;

public interface IJokeyDAO extends ICrud <Jokey, Long> {
    List<Jokey> getJokeys();
    Jokey get(Long id);
}

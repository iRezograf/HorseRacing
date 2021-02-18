package dao.interfaces;

import entity.Jokey;

import java.util.List;

public interface IJokeyDAO extends ICrud <Jokey> {
    List<Jokey> getJokeys();
    Jokey get(int id);
}

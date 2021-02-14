package dao.interfaces;

import dao.Jokey;

import java.util.List;

public interface IJokeyDAO extends ICrud {
    public List<Jokey> getJokeys();
    public Jokey get(int id);
}

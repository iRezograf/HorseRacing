package dao;

import dao.interfaces.ICrud;

import java.util.List;

public interface IJokeyDAO extends ICrud {
    public List<Jokey> getJokeys();
    public Jokey get(int id);
}

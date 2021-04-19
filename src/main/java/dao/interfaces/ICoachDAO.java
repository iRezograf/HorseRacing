package dao.interfaces;

import entity.Coach;

import java.util.List;

public interface ICoachDAO extends ICrud <Coach> {
    List<Coach> getCoaches();
    Coach get (int id);
}

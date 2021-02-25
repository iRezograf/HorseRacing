package dao.interfaces;

import java.util.List;

public interface ICoachDAO extends ICrud {
    List<Object> getCoaches();
    Object get (int id);
}

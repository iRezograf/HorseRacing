package dao.interfaces;

import java.util.List;

public interface IStudDAO extends ICrud{
    List<Object> getStudes();
    Object get (int id);
}

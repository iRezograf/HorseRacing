package dao.interfaces;

import java.util.List;

public interface ITypeBetDAO extends ICrud{
    List<Object> GetTypeBets();
    Object get (int id);
}

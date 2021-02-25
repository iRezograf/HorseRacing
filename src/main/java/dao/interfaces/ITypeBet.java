package dao.interfaces;

import java.util.List;

public interface ITypeBet extends ICrud{
    List<Object> GetTypeBets();
    Object get (int id);
}

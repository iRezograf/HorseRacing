package dao.interfaces;

import java.util.List;

public interface IIppoDAO extends ICrud{
    List<Object>  getIppoes();
    Object get (int id);

}

package dao.interfaces;

import entity.Ippo;

import java.util.List;

public interface IIppoDAO extends ICrud <Ippo>{
    List<Ippo>  getIppoes();
    Ippo get (int id);

}

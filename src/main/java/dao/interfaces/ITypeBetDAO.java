package dao.interfaces;

import entity.TypeBet;

import java.util.List;

public interface ITypeBetDAO extends ICrud <TypeBet>{
    List<TypeBet> GetTypeBets();
    TypeBet get (int id);
}

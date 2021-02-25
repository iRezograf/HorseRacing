package dao.interfaces;

import entity.Horse;

import java.util.List;

public interface IHorseDAO extends ICrud{
        List<Object> getHorses();
        Object get (int id);
}

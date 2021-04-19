package dao.interfaces;

import entity.Stud;

import java.util.List;

public interface IStudDAO extends ICrud <Stud>{
    List<Stud> getStudes();
    Stud get (int id);
}

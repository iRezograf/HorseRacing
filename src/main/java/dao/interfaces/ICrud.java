package dao.interfaces;

import java.sql.Connection;

public interface ICrud <T>{
        public abstract T save(T obj);
        public abstract T remove(T obj);
        public abstract T update(T obj);
        /*public abstract T update(T obj,int id);*/
        //public abstract T get(int id);
}





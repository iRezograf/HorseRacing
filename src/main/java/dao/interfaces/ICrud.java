package dao;

import java.sql.Connection;

public interface ICrud <T,K>{
        public abstract T save(T obj);
        public abstract T remove(K id);
        public abstract T update(T obj,K id);
        public abstract T get(Long id);
}





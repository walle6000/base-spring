package com.hd.daiban.dao;

import java.util.List;


public interface BaseDaoInf<T> {
	
    public void save(T entity);
    
    public void update(T entity);
    
    public T getById(Object id);
    
    public List<T> getAll();
    
    public int delete();
    
    public void saveAll(List<T> entities);
    
    public void updateAll(List<T> entities);
    
}

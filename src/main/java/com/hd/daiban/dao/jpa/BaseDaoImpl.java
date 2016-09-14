package com.hd.daiban.dao.jpa;

import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;

import com.hd.daiban.dao.BaseDaoInf;

public class BaseDaoImpl<T> implements BaseDaoInf<T> {

	protected Class<T> entityClass;
	
	protected String entityName;

	public BaseDaoImpl(final Class<T> entityClass) {
		this.entityClass = entityClass;
		this.entityName = this.entityClass.getSimpleName();
	}

	//@PersistenceContext
	//protected EntityManager em;

	@Override
	public void save(T entity) {
		//em.persist(entity);
	}
	
	@Override
	public void update(T entity){
		///em.merge(entity);
	}

	@Override
	public int delete() {
		/*Query query = em.createQuery("delete from " + this.entityName);

		int result = query.executeUpdate();

		return result;*/
		return 0;
	}

	@Override
	public List<T> getAll() {
		/*Query query = em.createQuery("select t from "
				+ this.entityName + " t");
		List<T> result = query.getResultList();
		return result;*/
		return null;
	}

	@Override
	public T getById(Object id) {
		//return em.find(this.entityClass, id);
		return null;
	}

	@Override
	public void saveAll(List<T> entities) {
		/*for (int i = 0; i < entities.size(); i++) {
			em.persist(entities.get(i));
			if (i % 30 == 0) {
				em.flush();
				em.clear();
			}
		}*/
	}

	@Override
	public void updateAll(List<T> list) {
		/*for (int i = 0; i < list.size(); i++) {
			em.merge(list.get(i));
			if (i % 30 == 0) {
				em.flush();
				em.clear();
			}
		}*/
	}

}

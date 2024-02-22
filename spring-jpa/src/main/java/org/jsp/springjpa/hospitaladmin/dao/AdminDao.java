package org.jsp.springjpa.hospitaladmin.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.springjpa.hospitaladmin.dto.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {
	@Autowired
	public EntityManager manager;
	
	public Admin saveAdmin(Admin a) {
		manager.persist(a);
		EntityTransaction transaction=manager.getTransaction();
		transaction.begin();
		transaction.commit();
		return a;
	}
	public Admin updateAdmin(Admin a) {
		
		manager.merge(a);
		EntityTransaction transaction=manager.getTransaction();
		transaction.begin();
		transaction.commit();
		return a;
		
	}
	public Admin findByid(int id) {
		return manager.find(Admin.class,id);
	}
	
	public boolean delete(int id) {
		Admin a=manager.find(Admin.class, id);
		if(a!=null) {
			manager.remove(a);
			EntityTransaction t=manager.getTransaction();
			t.begin();t.commit();
			return true;
		}return false;
	}
	public Admin verify(long phone,String email) {
		Query q=manager.createQuery("select a from Admin a where a.phone=?1 and a.email=?2");
		q.setParameter(1, phone);
		q.setParameter(2, email);
		try {
			return (Admin) q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
}



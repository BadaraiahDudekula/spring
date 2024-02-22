package org.jsp.springjpa.userfood.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.springjpa.userfood.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	public EntityManager manager;

	public User save(User user) {
		manager.persist(user);
		EntityTransaction t = manager.getTransaction();
		t.begin();
		t.commit();
		return user;
	}

	public User update(User user) {
			manager.merge(user);
			EntityTransaction t = manager.getTransaction();
			t.begin();
			t.commit();
			return user;
	}

	public User findById(int id) {
		return manager.find(User.class, id);
	}

	public User verify(long phone, String password) {
		Query q = manager.createQuery("select u from User u where u.phone=?1 and u.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		return  (User) q.getSingleResult();
	}

	public User verify(String email, String password) {
		Query q = manager.createQuery("select u from User u where u.email=?1 and u.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		return (User) q.getSingleResult();
	}
}

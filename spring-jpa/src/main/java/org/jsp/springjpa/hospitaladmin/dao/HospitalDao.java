package org.jsp.springjpa.hospitaladmin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.springjpa.hospitaladmin.dto.Admin;
import org.jsp.springjpa.hospitaladmin.dto.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HospitalDao {
	@Autowired
	public EntityManager manager;

	public Hospital save(Hospital h, int id) {
		Admin a = manager.find(Admin.class, id);
		if (a != null) {
			h.setAdmin(a);
			a.getHospital().add(h);
			manager.persist(h);
			EntityTransaction t = manager.getTransaction();
			t.begin();
			t.commit();
			return h;
		} else {
			return null;
		}
	}

	public Hospital update(Hospital h, int id) {
		Admin a = manager.find(Admin.class, id);
		if (a != null) {
			h.setAdmin(a);
			// a.getHospital().add(h);
			manager.merge(h);
			EntityTransaction t = manager.getTransaction();
			t.begin();
			t.commit();
			return h;
		} else {
			return null;
		}
	}

	public Hospital findById(int id) {
		return manager.find(Hospital.class, id);
	}

	public boolean delete(int id) {
		Hospital h = manager.find(Hospital.class, id);
		if (h != null) {
			manager.remove(h);
			EntityTransaction t = manager.getTransaction();
			t.begin();
			t.commit();
			return true;
		}
		return false;
	}

	public List<Hospital> findByUserPhoneAndEmail(long phone, String email) {
		Query q = manager.createQuery("select h from Hospital h where h.admin.phone=?1 and h.admin.email=?2");
		q.setParameter(1, phone);
		q.setParameter(2, email);
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Hospital> findByUserId(int id) {
		Query q=manager.createQuery("select h from Hospital h where h.admin.id=?1");
		q.setParameter(1, id);
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}



}

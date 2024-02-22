package org.jsp.springmvc.deptemp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.jsp.springmvc.deptemp.dto.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDao {
	@Autowired
	private EntityManager manager;

	public Department saveDepartment(Department dept) {
		EntityTransaction t = manager.getTransaction();
		manager.persist(dept);
		t.begin();
		t.commit();
		return dept;
	}

	public Department updateDepartment(Department dept) {
		Department d = manager.find(Department.class, dept.getId());
		if (d != null) {
			EntityTransaction t = manager.getTransaction();
			manager.merge(dept);
			t.begin();
			t.commit();
			return dept;
		}
		return null;
	}

	public Department findById(int id) {
		return manager.find(Department.class, id);
	}

	public boolean delete(int id) {
		Department dept = manager.find(Department.class, id);
		if (dept != null) {
			manager.remove(dept);
			EntityTransaction t = manager.getTransaction();
			t.begin();
			t.commit();
			return true;
		}
		return false;
	}

}

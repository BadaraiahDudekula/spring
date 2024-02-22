package org.jsp.springmvc.deptemp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.jsp.springmvc.deptemp.dto.Department;
import org.jsp.springmvc.deptemp.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {
	@Autowired
	private EntityManager manager;
	public Employee saveEmployee(Employee emp,int id) {
		Department dept=manager.find(Department.class,id);
		if(dept!=null) {
			emp.setDept(dept);
			dept.getEmp().add(emp);
			EntityTransaction t=manager.getTransaction();
			t.begin();t.commit();
			return emp;
		}
		return null;
	}
}

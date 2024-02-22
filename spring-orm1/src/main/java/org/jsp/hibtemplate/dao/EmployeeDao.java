package org.jsp.hibtemplate.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.jsp.hibtemplate.dto.Department;
import org.jsp.hibtemplate.dto.Employee;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class EmployeeDao {
	private HibernateTemplate template;

	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	@Transactional
	public Employee save(Employee e, int did) {
		Department d = template.get(Department.class, did);
		if (d != null) {
			e.setDept(d);
			d.getEmp().add(e);
			template.persist(e);

			return e;
		}
		return null;
	}

	@Transactional
	public Employee update(Employee e, int did) {
		Department d = template.get(Department.class, did);
		if (d != null) {
			e.setDept(d);
			// d.getEmp().add(e);
			template.merge(e);

			return e;
		}
		return null;
	}

	@Transactional
	public Employee findById(int id) {
		return template.get(Employee.class, id);
	}
	@Transactional
	public boolean delete(int id) {
		Employee e = template.get(Employee.class, id);
		if (e != null) {
			template.delete(e);
			return true;
		}
		return false;
	}

	

}

package org.jsp.hibtemplate.dao;

import javax.transaction.Transactional;

import org.jsp.hibtemplate.dto.Department;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class DepartmentDao {

	private HibernateTemplate template;

	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	@Transactional
	public Department save(Department dept) {
		template.persist(dept);
		return dept;
	}

	@Transactional
	public Department update(Department dept) {
		template.merge(dept);
		return dept;
	}

	@Transactional
	public Department findById(int id) {
		return template.get(Department.class, id);
	}

	@Transactional
	public boolean delete(int id) {
		Department d = template.get(Department.class, id);
		if (d != null) {
			template.delete(d);
			return true;
		}
		return false;
	}

}

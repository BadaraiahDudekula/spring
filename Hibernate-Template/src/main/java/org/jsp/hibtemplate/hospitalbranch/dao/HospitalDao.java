package org.jsp.hibtemplate.hospitalbranch.dao;

import org.springframework.orm.hibernate5.HibernateTemplate;

public class HospitalDao {
	private HibernateTemplate template;

	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

}

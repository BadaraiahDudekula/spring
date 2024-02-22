package org.jsp.jdbctemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class InsertUser {

	public static void main(String[] args) {
		ApplicationContext context=new AnnotationConfigApplicationContext("jdbc-template.xml");
		JdbcTemplate template=context.getBean("jdbcTemplate",JdbcTemplate.class);
		String qry = " insert into user values(6,'abc',12345,'abc123') ";
		int r=template.update(qry);
		System.out.println(r+"rows inserted");
	}

}

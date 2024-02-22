package org.jsp.jdbctemp.dto;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class EmployeeTable {
	public static void main(String[] args) {
		String qry = "create table employee(id int not null, name varchar(20) not null,phone bigint(10) not null unique,email varchar(20) not null unique,password varchar(20) not null, primary key(id) )";
		ApplicationContext context = new ClassPathXmlApplicationContext("jdbc-template.xml");
		JdbcTemplate template = context.getBean("jdbcTemplate",JdbcTemplate.class);
		template.execute(qry);
		System.out.println("User Table Created");
	}
}

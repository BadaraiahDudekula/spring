package org.jsp.jdbctemplate;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class FindUserByPhone {
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("jdbc-template.xml");
		JdbcTemplate template=context.getBean(JdbcTemplate.class);
		String qry="select * from user where phone=63723 ";
		List<User> users=template.query(qry,new MyResultSetExtractor());
		for(User u:users) {
			System.out.println("Id:"+u.getId());
			System.out.println("Name:"+u.getName());
			System.out.println("Phone:"+u.getPhone());
			System.out.println("Password:"+u.getPassword());
		}
	}
}

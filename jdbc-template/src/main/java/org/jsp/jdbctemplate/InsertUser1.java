package org.jsp.jdbctemplate;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class InsertUser1 {

	public static void main(String[] args) {
		String qry = "insert into user values(?,?,?,?)";
		Scanner s=new Scanner(System.in);
		System.out.println("enter the id name phone and password to insert a record");
		int id=s.nextInt();
		String name=s.next();
		long phone=s.nextLong();
		String password=s.next();
		ApplicationContext context=new ClassPathXmlApplicationContext("jdbc-template.xml");
		JdbcTemplate template=context.getBean(JdbcTemplate.class);
		int r=template.update(qry, id,name,phone,password);
		System.out.println(r+"row inserted");
	}

}

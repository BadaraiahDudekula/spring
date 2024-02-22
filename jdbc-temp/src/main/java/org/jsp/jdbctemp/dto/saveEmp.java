package org.jsp.jdbctemp.dto;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class saveEmp {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the id , name,phone,email and password to save emp");
		String qry="insert into Employee values(?,?,?,?,?)";
		int id=sc.nextInt();
		String name=sc.next();
		long phone=sc.nextLong();
		String email=sc.next();
		String password=sc.next();
		ApplicationContext context=new ClassPathXmlApplicationContext("jdbc-template.xml");
		JdbcTemplate template=context.getBean("jdbcTemplate",JdbcTemplate.class);
		int r=template.update(qry,id,name,phone,email,password);
		System.out.println(r+"rows inserted");
	}

}

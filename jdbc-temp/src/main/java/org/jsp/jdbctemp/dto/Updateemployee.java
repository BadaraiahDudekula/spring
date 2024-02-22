package org.jsp.jdbctemp.dto;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class Updateemployee {

	public static void main(String[] args) {
		String qry = "update Employee  set name=? ,phone=?,email=?,password=? where id=?";
		System.out.println("enter the id to update the Employee");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		System.out.println("enter the name,phone emailand password to update the Employee");
		String name = sc.next();
		long phone = sc.nextLong();
		String email = sc.next();
		String password = sc.next();
		ApplicationContext context = new ClassPathXmlApplicationContext("jdbc-template.xml");
		JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
		int r = template.update(qry, name, phone, email, password, id);
		if (r > 0) {
			System.out.println("employee Updated");
		} else {
			System.out.println("cannot Update the Employee as Invalid Id");
		}
	}

}

package org.jsp.jdbctemplate;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class UpdateUser {

	public static void main(String[] args) {
		String sql = " update user set name=? , phone=?, password=? where id=?";
		Scanner s=new Scanner(System.in);
		System.out.println("enter the id to update the record");
		int id=s.nextInt();
		System.out.println("enter the  name phone and password to insert a record");
		
		String name=s.next();
		long phone=s.nextLong();
		String password=s.next();
		ApplicationContext context=new ClassPathXmlApplicationContext("jdbc-template.xml");
		JdbcTemplate template=context.getBean(JdbcTemplate.class);
		int r=template.update(sql,name,phone,password,id);
		if(r==1){
			System.out.println("User Updated");
		}else {
			System.out.println("Invalid User");
		}
	}

}
 
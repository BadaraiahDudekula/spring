package org.jsp.jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class FindUserByName {
	public static void main(String[] args) {
//		Scanner sc=new Scanner(System.in);
//		String name=sc.next();
		ApplicationContext context=new ClassPathXmlApplicationContext("jdbc-template.xml");
		JdbcTemplate template=context.getBean(JdbcTemplate.class);
		String qry="select * from user where name='badaraiah' ";
		List<User> users=template.query(qry,new MyResultSetExtractor());
		for(User u:users) {
			System.out.println("Id:"+u.getId());
			System.out.println("Name:"+u.getName());
			System.out.println("Phone:"+u.getPhone());
			System.out.println("Password:"+u.getPassword());
		}
	}

}


//class MyResultSetExtractor implements ResultSetExtractor<List<User>>{
//
//	public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
//		 List<User> users =new ArrayList<User>();
//		 while(rs.next()) {
//			 User u=new User();
//			 u.setId(rs.getInt("id"));
//			 u.setName(rs.getString("name"));
//			 u.setPhone(rs.getLong("phone"));
//			 u.setPassword(rs.getString("password"));
//			 users.add(u);
//		 }
//		return users;
//	}
	

//}

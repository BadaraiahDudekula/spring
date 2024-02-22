package org.jsp.jdbctemp.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsp.jdbctemplate.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class FetchByPhone {

	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("jdbc-template.xml");
		JdbcTemplate template=context.getBean("jdbcTemplate",JdbcTemplate.class);
		String qry="select * from Employee  where phone=630144";
		System.out.println("enter the phone to fetch the Employee Details");
		List<Employee> emp=template.query(qry,new MyResultSetExtractor());
		for(Employee e:emp) {
			System.out.println("Id:"+e.getId());
			System.out.println("Name:"+e.getName());
			System.out.println("Email:"+e.getEmail());
			System.out.println("Phone:"+e.getPhone());
			System.out.println("Password:"+e.getPassword());
		}		
	}

}
class MyResultSetExtractor implements ResultSetExtractor<List<Employee>>{

	public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {
		 List<Employee> emp =new ArrayList<Employee>();
		 while(rs.next()) {
			 Employee e=new Employee();
			 e.setId(rs.getInt("id"));
			 e.setName(rs.getString("name"));
			 e.setEmail(rs.getString("Email"));
			 e.setPhone(rs.getLong("phone"));
			 e.setPassword(rs.getString("password"));
			 emp.add(e);
		 }
		return emp;
	}
	
}

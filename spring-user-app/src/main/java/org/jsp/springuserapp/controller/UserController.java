package org.jsp.springuserapp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.springuserapp.UserConfig;
import org.jsp.springuserapp.dao.UserDao;
import org.jsp.springuserapp.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class UserController {

	static Scanner s=new Scanner(System.in);
	static UserDao dao;
	static {
		ApplicationContext context=new AnnotationConfigApplicationContext(UserConfig.class);
		dao=context.getBean(UserDao.class);
	}

	public static void main(String[] args) {
		System.out.println("1.Save User");
		System.out.println("2.Update User");
		System.out.println("3.Find User By Id");
		System.out.println("4.Verify User By Phone and password");
		System.out.println("5.Verify User By Email and password");
		System.out.println("6.Verify User By Id and password");
		System.out.println("7.Delete User");
		System.out.println("8.Fetch Names");

		switch (s.nextInt()) {
		case 1:{
			save();
			break;
		}
		case 2:{
			update();
			break;
		}
		case 3:{
			findById();
			break;
		}
		case 4:{
			VerifyByPhoneandpassword();
			break;
		}
		case 5:{
			VerifyByEmailandpassword();
			break;
		}
		case 6:{
			VerifyByIdandpassword();
			break;
		}
		case 7:{
			delete();
			break;
		}
		case 8:{
			printNames();
			break;
		}
		default:
			break;
		}


	}
	public static void save() {
		System.out.println("enter the name, phone, email and password to save User");
		User u=new User();
		u.setName(s.next());
		u.setPhone(s.nextLong());
		u.setEmail(s.next());
		u.setPassword(s.next());
		u=dao.saveUser(u);
		System.out.println("user registerd with Id:"+u.getId());
	}

	public static void update() {
		System.out.println("Enter User Id to Update The Details");
		int id=s.nextInt();
		System.out.println("enter the name, phone, email and password to update the uder details");
		User u=new User();
		u.setId(id);
		u.setName(s.next());
		u.setPhone(s.nextLong());
		u.setEmail(s.next());
		u.setPassword(s.next());
		u=dao.updateUser(u);
		if(u!=null) {
			System.out.println("User Updated");
		}else {
			System.err.println("cannot update the user entered ID is Invalid");
		}
	}

	public static void findById() {
		System.out.println("Enter the id to print Details");
		int id=s.nextInt();
		User u=dao.findById(id);
		if(u!=null) {
			System.out.println("user Id:"+u.getId());
			System.out.println("User Name:"+u.getName());
			System.out.println("phone Number:"+u.getPhone());
			System.out.println("Password:"+u.getPassword());
			System.out.println("Email:"+u.getEmail());
		}else {
			System.out.println("You have entered Invalid Id");
		}
	}
	public static void VerifyByPhoneandpassword() {
		System.out.println("Enter the phoneNumber and Password to Verify User");
		long phone=s.nextLong();
		String password=s.next();
		User u=dao.verifyUser(phone, password);
		if(u!=null) {
			System.out.println("user Id:"+u.getId());
			System.out.println("User Name:"+u.getName());
			System.out.println("Email:"+u.getEmail());
			System.out.println("phone Number:"+u.getPhone());
			System.out.println("Password:"+u.getPassword());
		}else {
			System.out.println("You have entered Invalid phone or password");
		}
	}
	public static void VerifyByEmailandpassword() {
		System.out.println("Enter the Email and Password to Verify User");
		String email=s.next();
		String password=s.next();
		User u=dao.verifyUser(email, password);
		if(u!=null) {
			System.out.println("user Id:"+u.getId());
			System.out.println("User Name:"+u.getName());
			System.out.println("Email:"+u.getEmail());
			System.out.println("phone Number:"+u.getPhone());
			System.out.println("Password:"+u.getPassword());
		}else {
			System.out.println("You have entered Invalid email Id or password");
		}
	}
	public static void VerifyByIdandpassword() {
		System.out.println("Enter the Id and Password to Verify User");
		int id=s.nextInt();
		String password=s.next();
		User u=dao.verifyUser(id, password);
		if(u!=null) {
			System.out.println("user Id:"+u.getId());
			System.out.println("User Name:"+u.getName());
			System.out.println("Email:"+u.getEmail());
			System.out.println("phone Number:"+u.getPhone());
			System.out.println("Password:"+u.getPassword());
		}else {
			System.out.println("You have entered Invalid User Id or password");
		}
	}
	public static void delete() {
		System.out.println("enter the uder id to delete");
		int id=s.nextInt();
		boolean deleted=dao.delete(id);
		if(deleted) {
			System.out.println("user deleted");
		}
		else {
			System.out.println("cannot deleted user as Invalid User Id");
		}
	}
	public static void printNames() {
		List<String> names=dao.getnames();
		for(String name:names) {
			System.out.println(name);
		}
	}

}















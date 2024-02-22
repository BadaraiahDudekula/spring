package org.jsp.customeraddress.controller;

import java.util.Scanner;

import org.jsp.customeraddress.CustomerAddressConfig;
import org.jsp.customeraddress.dao.CustomerAddressDao;
import org.jsp.customeraddress.dto.Address;
import org.jsp.customeraddress.dto.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CustomerAddressController {
	static CustomerAddressDao dao;
	static Scanner s=new Scanner(System.in);
	static {
	ApplicationContext context=new AnnotationConfigApplicationContext(CustomerAddressConfig.class);
	 dao=context.getBean(CustomerAddressDao.class);
	}
	public static void main(String[] args) {
		System.out.println("1.Save Customer");
		System.out.println("2.update the Customer");
		System.out.println("3.save Address");
		System.out.println("Enter the number");
		
		switch (s.nextInt()) {
		case 1:{
			saveCustomer();
			break;
		}
		case 2:{
			updateCustomer();
			break;
		}

		case 3: {
			saveAddress();
			break;
		}
		case 4: {
			updateAddress();
			break;
		}
		default:{
			break;
		}
		}
		
	}
	
	public static void saveCustomer() {
		System.out.println("enter the name, gender,phone,email and password to save Customer");
		Customer c=new Customer();
		c.setName(s.next());
		c.setGender(s.next());
		c.setPhone(s.nextLong());
		c.setEmail(s.next());
		c.setPassword(s.next());
		c=dao.saveUser(c);
		System.out.println("Customer registerd with Id:"+c.getId());
	}
	
	public static void saveAddress() {
		System.out.println("Enter customer id");
		int id=s.nextInt();
		System.out.println("enter the house_number, building_name,landmark,address_type, state,city,country and pincode to save Customer");
		Address a=new Address();
		a.setHouse_number(s.nextInt());
		a.setBuilding_name(s.next());
		a.setLandmark(s.next());
		a.setAddress_type(s.next());
		a.setState(s.next());
		a.setCity(s.next());
		a.setCountry(s.next());
		a.setPincode(s.nextInt());
		a=dao.saveAddress(a,id);
		System.out.println("Address registerd with Id:"+a.getId());
	}
	
	public static void updateCustomer() {
		System.out.println("Enter User Id to Update The Details");
		int id=s.nextInt();
		System.out.println("enter the name, phone, email and password to update the uder details");
		Customer c=new Customer();
		c.setId(id);
		c.setName(s.next());
		c.setGender(s.next());
		c.setPhone(s.nextLong());
		c.setEmail(s.next());
		c.setPassword(s.next());
		c=dao.updateCustomer(c);
		if(c!=null) {
			System.out.println("Customer Updated");
		}else {
			System.err.println("cannot update the Customer entered ID is Invalid");
		}
	}
	public static void updateAddress() {
		
	}

}

package org.jsp.springjpa.customeradress.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.springjpa.customeradress.CustomerAdressConfig;
import org.jsp.springjpa.customeradress.dao.AdressDao;
import org.jsp.springjpa.customeradress.dao.CustomerDao;
import org.jsp.springjpa.customeradress.dto.Adress;
import org.jsp.springjpa.customeradress.dto.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerAdressController {

	static Scanner sc = new Scanner(System.in);
	static CustomerDao cdao;
	static AdressDao adao;
	static {
		ApplicationContext context = new AnnotationConfigApplicationContext(CustomerAdressConfig.class);
		cdao = context.getBean(CustomerDao.class);
		adao = context.getBean(AdressDao.class);

	}

	public static void main(String[] args) {

		System.out.println("1.Save Customer");
		System.out.println("2.Update Customer");
		System.out.println("3.Find Customer By Customer");
		System.out.println("4.Verify  Customer By Phone and Password");
		System.out.println("5.Verify  Customer By Email and Password");
		System.out.println("6.add Adress");
		System.out.println("7.update adress");
		System.out.println("8.Find adress By Id");
		System.out.println("9.Find adress By customer Id");
		System.out.println("10.Find adress By customer phone and password");

		int key = sc.nextInt();
		switch (key) {

		case 1: {
			saveCustomer();
			break;
		}

		case 2: {
			updateCustomer();
			break;
		}
		case 3: {
			findByCustomerId();
			break;
		}
		case 4: {
			verifyPhoneandPassword();
			break;
		}
		case 5: {
			verifyEmailandPassword();
			break;
		}
		case 6: {
			addAdress();
			break;
		}
		case 7: {
			updateAdress();
			break;
		}
		case 8: {
			findByAddId();
			break;
		}
		case 9: {
			findByCustId();
			break;
		}
		case 10: {
			findByCustPhoneandPassword();
			break;
		}
		default:
			break;
		}
	}

	public static void saveCustomer() {
		System.out.println("Enter the name,phone,email,password");
		Customer c = new Customer();
		c.setName(sc.next());
		c.setPhone(sc.nextLong());
		c.setEmail(sc.next());
		c.setPassword(sc.next());
		c = cdao.save(c);
		System.out.println("Customer saved with Id:" + c.getId());
	}

	public static void updateCustomer() {
		System.out.println("Enter the Id To update The Customer");
		int id = sc.nextInt();
		Customer c = cdao.findById(id);
		System.out.println("Enter the name,phone,email,password");
		if (c != null) {
			c.setId(id);
			c.setName(sc.next());
			c.setPhone(sc.nextLong());
			c.setEmail(sc.next());
			c.setPassword(sc.next());
			c = cdao.save(c);
			System.out.println("Customer Updated with Id:" + c.getId());
		} else {
			System.out.println("Invalid Id");
		}
	}

	public static void findByCustomerId() {
		System.out.println("enter the id to get customer Details");
		int id = sc.nextInt();
		Customer c = cdao.findById(id);
		if (c != null) {
			System.out.println("Name:" + c.getName());
			System.out.println("Phone:" + c.getPhone());
			System.out.println("Email:" + c.getEmail());
			System.out.println("Password:" + c.getPassword());
		} else {
			System.out.println("Invalid Id");
		}
	}

	public static void verifyPhoneandPassword() {
		System.out.println("enter the Phone and Password to get customer Details");
		long phone = sc.nextLong();
		String password = sc.next();
		Customer c = cdao.verify(phone, password);
		if (c != null) {
			System.out.println("Name:" + c.getName());
			System.out.println("Phone:" + c.getPhone());
			System.out.println("Email:" + c.getEmail());
			System.out.println("Password:" + c.getPassword());
		} else {
			System.out.println("Invalid Id");
		}
	}

	public static void verifyEmailandPassword() {
		System.out.println("enter the email and Password to get customer Details");
		String email = sc.next();
		String password = sc.next();
		Customer c = cdao.verify(email, password);
		if (c != null) {
			System.out.println("Name:" + c.getName());
			System.out.println("Phone:" + c.getPhone());
			System.out.println("Email:" + c.getEmail());
			System.out.println("Password:" + c.getPassword());
		} else {
			System.out.println("Invalid Id");
		}
	}

	public static void addAdress() {
		System.out.println("enter the Customer Id to Save with adress");
		int id = sc.nextInt();
		System.out.println("Enter the building name,landmark area,city,state,country pincode,type");
		Adress a = new Adress();
		a.setBuildingname(sc.next());
		a.setLandmark(sc.next());
		a.setArea(sc.next());
		a.setCity(sc.next());
		a.setState(sc.next());
		a.setCountry(sc.next());
		a.setPincode(sc.nextInt());
		a.setType(sc.next());
		a = adao.save(a, id);
		if (a != null) {
			System.out.println("adress saved with Id:" + a.getId());
		} else {
			System.out.println("Invalid Customer Id");
		}
	}

	public static void updateAdress() {
		System.out.println("enter the Customer Id to Save with adress");
		int id = sc.nextInt();
		System.out.println("Enter the id, building name,landmark, area,city,state,country pincode,type");
		Adress a = new Adress();
		a.setId(sc.nextInt());
		a.setBuildingname(sc.next());
		a.setLandmark(sc.next());
		a.setArea(sc.next());
		a.setCity(sc.next());
		a.setState(sc.next());
		a.setCountry(sc.next());
		a.setPincode(sc.nextInt());
		a.setType(sc.next());
		a = adao.update(a, id);
		if (a != null) {
			System.out.println("adress updated with Id:" + a.getId());
		} else {
			System.out.println("Invalid Customer Id");
		}
	}

	public static void findByAddId() {
		System.out.println("enter the id to get address details");
		int id = sc.nextInt();
		Adress a = adao.findById(id);
		if (a != null) {
			System.out.println("BuildingName:" + a.getBuildingname());
			System.out.println("BuildingName:" + a.getLandmark());
			System.out.println("BuildingName:" + a.getArea());
			System.out.println("BuildingName:" + a.getCity());
			System.out.println("BuildingName:" + a.getState());
			System.out.println("BuildingName:" + a.getCountry());
			System.out.println("BuildingName:" + a.getPincode());
			System.out.println("BuildingName:" + a.getType());
		} else {
			System.out.println("Invalid Id");
		}
	}

	public static void findByCustId() {
		System.out.println("enter the Customer id to get address details");
		int id = sc.nextInt();
		List<Adress> ad = adao.findByCustoId(id);
		if (ad.size() > 0) {
			for (Adress a : ad) {
				System.out.println("BuildingName:" + a.getBuildingname());
				System.out.println("BuildingName:" + a.getLandmark());
				System.out.println("BuildingName:" + a.getArea());
				System.out.println("BuildingName:" + a.getCity());
				System.out.println("BuildingName:" + a.getState());
				System.out.println("BuildingName:" + a.getCountry());
				System.out.println("BuildingName:" + a.getPincode());
				System.out.println("BuildingName:" + a.getType());
			}
		} else {
			System.out.println("Invalid  Customer Id");
		}
	}
	public static void findByCustPhoneandPassword() {
		System.out.println("enter the Customer phone and password to get address details");
		long phone=sc.nextLong();
		String password=sc.next();
		
		List<Adress> ad = adao.findByCustoPhoneandPassword(phone,password);
		if (ad.size() > 0) {
			for (Adress a : ad) {
				System.out.println("BuildingName:" + a.getBuildingname());
				System.out.println("BuildingName:" + a.getLandmark());
				System.out.println("BuildingName:" + a.getArea());
				System.out.println("BuildingName:" + a.getCity());
				System.out.println("BuildingName:" + a.getState());
				System.out.println("BuildingName:" + a.getCountry());
				System.out.println("BuildingName:" + a.getPincode());
				System.out.println("BuildingName:" + a.getType());
			}
		} else {
			System.out.println("Invalid  CustomerPhone or password");
		}
	}

}

package org.jsp.springhibernatetemplatedemo.Controller;

import java.util.Scanner;

import org.jsp.springhibernatetemplatedemo.dao.MerchantDao;
import org.jsp.springhibernatetemplatedemo.dto.Merchant;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MerchantController {
	private static MerchantDao dao;
	private static Scanner sc=new Scanner(System.in);
	static {
		ApplicationContext context=new ClassPathXmlApplicationContext("hib-template.xml");
		dao=context.getBean(MerchantDao.class);
	}

	public static void main(String[] args) {
		System.out.println("1.saveMerchant");
		System.out.println("2.UpdateMerchant");
		System.out.println("3.FindById");
		System.out.println("4.delete Merchant");
		switch (sc.nextInt()) {
		case 1:{
			saveMerchant();
			break;
		}
		case 2:{
			updateMerchant();
			break;
		}
		case 3:{
			findById();
			break;
		}
		case 4:{
			deleteById();
			break;
		}
		default:
			break;
		}
	
	}
	
	public static void saveMerchant() {
		System.out.println("enter the name,email,gstNumber,phone and password to save Merchant");
		Merchant m=new Merchant();
		m.setName(sc.next());
		m.setEmail(sc.next());
		m.setGst_number(sc.next());
		m.setPhone(sc.nextLong());
		m.setPassword(sc.next());
		m=dao.saveMerchant(m);
		System.out.println("Merchant save with Id:"+m.getId());
	}

	public static void updateMerchant() {
		System.out.println("Enter User Id to Update The Details");
		int id=sc.nextInt();
		System.out.println("enter the name,email,gstNumber,phone and password to save Update Merchant");
		Merchant m=new Merchant();
		m.setId(id);
		m.setName(sc.next());
		m.setEmail(sc.next());
		m.setGst_number(sc.next());
		m.setPhone(sc.nextLong());
		m.setPassword(sc.next());
		m=dao.updateMerchant(m);
		if(m!=null) {
			System.out.println("Merchant Updated");
		}else {
			System.err.println("cannot update the Merchant Details entered ID is Invalid");
		}
	}

	public static void findById() {
		System.out.println("Enter the id to print Details");
		int id=sc.nextInt();
		Merchant m=dao.findById(id);
		if(m!=null) {
			System.out.println("Merchant Id:"+m.getId());
			System.out.println("Merchant Name:"+m.getName());
			System.out.println("Merchant Gst_Number:"+m.getGst_number());
			System.out.println("phone Number:"+m.getPhone());
			System.out.println("Password:"+m.getPassword());
			System.out.println("Email:"+m.getEmail());
		}else {
			System.out.println("You have entered Invalid Id");
		}
	}
	public static void deleteById() {
		System.out.println("enter the Merchant id to delete");
		int id=sc.nextInt();
		boolean deleted=dao.deleteById(id);
		if(deleted) {
			System.out.println("Merchant deleted");
		}
		else {
			System.out.println("cannot deleted Merchant as Invalid Merchant Id");
		}
	}

}

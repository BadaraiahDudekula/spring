package org.jsp.springjpa.merchantproduct.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.print.attribute.standard.PDLOverrideSupported;

import org.jsp.springjpa.merchantproduct.MerchantProductCfg;
import org.jsp.springjpa.merchantproduct.dao.MerchantDao;
import org.jsp.springjpa.merchantproduct.dao.ProductDao;
import org.jsp.springjpa.merchantproduct.dto.Merchant;
import org.jsp.springjpa.merchantproduct.dto.Product;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class MerchantProductController {
	static MerchantDao mdao;
	static ProductDao pdao;
	static Scanner sc = new Scanner(System.in);
	static {
		ApplicationContext context = new AnnotationConfigApplicationContext(MerchantProductCfg.class);
		mdao = context.getBean(MerchantDao.class);
		pdao = context.getBean(ProductDao.class);
	}

	public static void main(String[] args) {
		System.out.println("1.save merchant");
		System.out.println("2.update Merchant");
		System.out.println("3.findByMerchantId");
		System.out.println("4.delete");
		System.out.println("5.Add Product");
		System.out.println("6.update Product");
		System.out.println("7.findByProduct Id");
		System.out.println("8.delete product");
		System.out.println("9.verifyMerchantByPhoneAndPassword");
		System.out.println("10.verifyMerchantByEmailAndPassword");
		System.out.println("11.verifyMerchantByIdAndPassword");
		System.out.println("12.find product by Merchant Id");
		System.out.println("13.find All the Product");
		System.out.println("14.findProductByBrand");
		System.out.println("15.findProductByCatagory");
		int choice = sc.nextInt();
		
		switch (choice) {
		case 1: {
			saveMerchant();
			break;
		}
		case 2: {
			updateMerchant();
			break;
		}
		case 3: {
			findByMerchantId();
			break;
		}
		case 4: {
			deleteMerchant();
			break;
		}
		case 5: {
			addProduct();
			break;
		}
		case 6: {
			updateProduct();
			break;
		}
		case 7: {
			findByProductId();
			break;
		}
		case 8: {
			deleteProduct();
			break;
		}
		case 9: {
			verifyMerchantByPhoneAndPassword();
			break;
		}
		case 10: {
			verifyMerchantByEmailAndPassword();
			break;
		}
		case 11: {
			verifyMerchantByIdAndPassword();
			break;
		}
		case 12: {
			findProductByMId();
			break;
		}
		case 13: {
			findAllProducts();
			break;
		}
		case 14: {
			findProductByBrand();
			break;
		}
		case 15: {
			findProductByCatagory();
			break;
		}

		default:
			break;
		}

	}

	public static void saveMerchant() {
		System.out.println("Enter the name,phone,email,gst,password to save the merchant");
		Merchant m = new Merchant();
		m.setName(sc.next());
		m.setPhone(sc.nextLong());
		m.setEmail(sc.next());
		m.setGst(sc.next());
		m.setPassword(sc.next());
		m = mdao.saveMerchantDetails(m);
		System.out.println("Mrchant saved with Id:" + m.getId());
	}

	public static void updateMerchant() {
		System.out.println("Enter the Id to Update the Merchant Details");
		int id = sc.nextInt();
		Merchant m = mdao.getMercgantId(id);
		if (m != null) {
			System.out.println("Enter the name,phone,email,gst,password to save the merchant");
			m.setId(id);
			m.setName(sc.next());
			m.setPhone(sc.nextLong());
			m.setEmail(sc.next());
			m.setGst(sc.next());
			m.setPassword(sc.next());
			m = mdao.updateMerchantDetails(m);
			System.out.println("Mrchant saved with Id:" + m.getId());
		} else {
			System.out.println("Invalid Id");
		}
	}

	public static void findByMerchantId() {
		System.out.println("Enter the id to get Merchant Details");
		int id = sc.nextInt();
		Merchant m = mdao.getMercgantId(id);
		if (m != null) {
			System.out.println("Name:" + m.getName());
			System.out.println("Email" + m.getEmail());
			System.out.println("Phone:" + m.getPhone());
			System.out.println("Gst" + m.getGst());
			System.out.println("Pssword:" + m.getPassword());
		} else {
			System.out.println("Invalid Id");
		}
	}

	public static void deleteMerchant() {
		System.out.println("Enter the id to get Merchant Details");
		int id = sc.nextInt();
		boolean deleted = mdao.merchantDelete(id);
		if (deleted) {
			System.out.println("Merchant Deleted");
		} else {
			System.out.println("Invalid Merchant Id");
		}
	}

	public static void verifyMerchantByPhoneAndPassword() {
		System.out.println("Enter the phone and password to verify Merchant");
		Merchant m = new Merchant();
		long phone = sc.nextLong();
		String password = sc.next();
		m = mdao.verify(phone, password);
		if (m != null) {
			System.out.println("Name:" + m.getName());
			System.out.println("Email" + m.getEmail());
			System.out.println("Phone:" + m.getPhone());
			System.out.println("Gst" + m.getGst());
			System.out.println("Pssword:" + m.getPassword());
		} else {
			System.out.println("Invalid phone or password");
		}
	}

	public static void verifyMerchantByEmailAndPassword() {
		System.out.println("Enter the Email and password to verify Merchant");
		Merchant m = new Merchant();
		String email = sc.next();
		String password = sc.next();
		m = mdao.verify(email, password);
		if (m != null) {
			System.out.println("Name:" + m.getName());
			System.out.println("Email" + m.getEmail());
			System.out.println("Phone:" + m.getPhone());
			System.out.println("Gst" + m.getGst());
			System.out.println("Pssword:" + m.getPassword());
		} else {
			System.out.println("Invalid Email or password");
		}
	}

	public static void verifyMerchantByIdAndPassword() {
		System.out.println("Enter the id and password to verify Merchant");
		Merchant m = new Merchant();
		int id = sc.nextInt();
		String password = sc.next();
		m = mdao.verifyIdAndPassword(id, password);
		if (m != null) {
			System.out.println("Name:" + m.getName());
			System.out.println("Email" + m.getEmail());
			System.out.println("Phone:" + m.getPhone());
			System.out.println("Gst" + m.getGst());
			System.out.println("Pssword:" + m.getPassword());
		} else {
			System.out.println("Invalid Id or password");
		}
	}

	public static void addProduct() {
		System.out.println("enter the Merchant id to save with Product");
		int id = sc.nextInt();
		Product p = new Product();
		System.out.println("Enter name,brand,description,catagory,cost to add Product");
		p.setName(sc.next());
		p.setBrand(sc.next());
		p.setDescription(sc.next());
		p.setCatagory(sc.next());
		p.setCost(sc.nextInt());
		p = pdao.saveProduct(p, id);
		System.out.println("Product Added with Id:" + p.getId());
	}

	public static void updateProduct() {
		System.out.println("Enter Merchant Id to update the product");
		int id = sc.nextInt();
		Product p = new Product();
		System.out.println("enter id, name,brand,description,catagory,cost ");
		p.setId(sc.nextInt());
		p.setName(sc.next());
		p.setBrand(sc.next());
		p.setDescription(sc.next());
		p.setCatagory(sc.next());
		p.setCost(sc.nextInt());
		p = pdao.updateProduct(p, id);
		if (p != null) {
			System.out.println("product updated ");
		} else {
			System.out.println("Invalid Merchant Id");
		}
	}

	public static void findByProductId() {
		System.out.println("Enter the product id");
		int id = sc.nextInt();
		Product p = pdao.findById(id);
		if (p != null) {
			System.out.println("Name:" + p.getName());
			System.out.println("Brand:" + p.getBrand());
			System.out.println("Catagory:" + p.getCatagory());
			System.out.println("Description:" + p.getDescription());
			System.out.println("Cost:" + p.getCost());
		} else {
			System.out.println("Invalid Id");
		}
	}

	public static void deleteProduct() {
		System.out.println("Enter the id to delete product ");
		int id = sc.nextInt();
		boolean deleted = pdao.delete(id);
		if (deleted) {
			System.out.println("Product Deleted");
		} else {
			System.out.println("Cannot delete the product asInvalid Id");
		}
	}

	public static void findAllProducts() {
		List<Product> p=pdao.findProducts();
		if(p.size()>0) {
			for(Product pr:p) {
			System.out.println("Id:"+pr.getId());
			System.out.println("Name:" + pr.getName());
			System.out.println("Brand:" + pr.getBrand());
			System.out.println("Catagory:" + pr.getCatagory());
			System.out.println("Description:" + pr.getDescription());
			System.out.println("Cost:" + pr.getCost());
			System.out.println("====================");}
			
		}
	}
	public static void findProductByMId() {
		System.out.println("enter the merchant id to find product");
		int m_id = sc.nextInt();
		List<Product> p = pdao.findProductByMerchantId(m_id);
		if (p.size() > 0) {
			for (Product pr : p) {
				System.out.println("Name:" + pr.getName());
				System.out.println("Brand:" + pr.getBrand());
				System.out.println("Catagory:" + pr.getCatagory());
				System.out.println("Description:" + pr.getDescription());
				System.out.println("Cost:" + pr.getCost());
			}
		} else {
			System.out.println("Invalid Merchant Id");
		}
	}
	public static void findProductByBrand() {
		System.out.println("enter the brand to find product detals");
		String brand=sc.next();
		List<Product> p=pdao.findProductByBrand(brand);
		if(p.size()>0) {
			for(Product pr:p) {
				System.out.println("Name:" + pr.getName());
				System.out.println("Brand:" + pr.getBrand());
				System.out.println("Catagory:" + pr.getCatagory());
				System.out.println("Description:" + pr.getDescription());
				System.out.println("Cost:" + pr.getCost());
			}
		}else {
			System.out.println("Invalid brand");
		}
	}
	public static void findProductByCatagory() {
		System.out.println("enter the catagoty to find product detals");
		String catagory=sc.next();
		List<Product> p=pdao.findProductByCatagory(catagory);
		if(p.size()>0) {
			for(Product pr:p) {
				System.out.println("Name:" + pr.getName());
				System.out.println("Brand:" + pr.getBrand());
				System.out.println("Catagory:" + pr.getCatagory());
				System.out.println("Description:" + pr.getDescription());
				System.out.println("Cost:" + pr.getCost());
			}
		}else {
			System.out.println("Invalid Catagory");
		}
	}

}

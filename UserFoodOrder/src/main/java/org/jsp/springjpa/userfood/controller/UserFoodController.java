package org.jsp.springjpa.userfood.controller;

import java.util.List;
import java.util.Scanner;

import javax.persistence.NoResultException;

import org.jsp.springjpa.userfood.UserFoodOrderConfig;
import org.jsp.springjpa.userfood.dao.FoodOrderDao;
import org.jsp.springjpa.userfood.dao.UserDao;
import org.jsp.springjpa.userfood.dto.FoodOrder;
import org.jsp.springjpa.userfood.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

@Controller
public class UserFoodController {
	static Scanner sc = new Scanner(System.in);
	static UserDao udao;
	static FoodOrderDao fdao;
	static {
		ApplicationContext context = new AnnotationConfigApplicationContext(UserFoodOrderConfig.class);
		udao = context.getBean(UserDao.class);
		fdao = context.getBean(FoodOrderDao.class);
	}

	public static void main(String[] args) {
		System.out.println("1.save User");
		System.out.println("2.update User");
		System.out.println("3.findByUser Id");
		System.out.println("4.verify By Phone and Password");
		System.out.println("5.verify By Email and password");
		System.out.println("6.save FoodOrder");
		System.out.println("7.update Food Order");
		System.out.println("8.Find Food Order By id");
		System.out.println("9.Find Food Order By UserId");
		System.out.println("10 find food order By User Phone And password");
		int choice = sc.nextInt();
		switch (choice) {
		case 1: {
			saveUser();
			break;
		}
		case 2: {
			updateUser();
			break;
		}
		case 3: {
			findByUserId();
			break;
		}
		case 4: {
			verifyByPhoneAndPassword();
			break;
		}
		case 5: {
			verifyByemailAndPassword();
			break;
		}
		case 6: {
			saveFoodOrder();
			break;
		}
		case 7: {
			updateFoodOrder();
			break;
		}
		case 8: {
			findByFoodOrderId();
			break;
		}
		case 9: {
			findFoodOrderByUserId();
			break;
		}
		case 10: {
			findFoodOrderByUserPhoneAndPassword();
			break;
		}
		default:
			break;
		}
	}

	public static void saveUser() {
		System.out.println("enter the name,phone email and password to save User");
		User u = new User();
		u.setName(sc.next());
		u.setPhone(sc.nextLong());
		u.setEmail(sc.next());
		u.setPassword(sc.next());
		u = udao.save(u);
		System.out.println("User saved with Id:" + u.getId());

	}

	public static void updateUser() {
		System.out.println("Enter the id to update the user");
		int id = sc.nextInt();
		System.out.println("enter the name,phone email and password to save User");
		User u = new User();
		u.setId(id);
		u.setName(sc.next());
		u.setPhone(sc.nextLong());
		u.setEmail(sc.next());
		u.setPassword(sc.next());
		try {
			u = udao.update(u);

			System.out.println("User updated");
		} catch (Exception e) {
			System.out.println("Invalid id");
		}
	}

	public static void findByUserId() {
		System.out.println("Enter the id to Find User details");
		int id = sc.nextInt();
		User u = udao.findById(id);
		if (u != null) {
			System.out.println("Name:" + u.getName());
			System.out.println("Phone:" + u.getPhone());
			System.out.println("Emai:" + u.getEmail());
			System.out.println("Password:" + u.getPassword());

		} else {
			System.out.println("Invalid id");
		}

	}

	public static void verifyByPhoneAndPassword() {
		System.out.println("Enter the phone and password to verify User");
		long phone = sc.nextLong();
		String password = sc.next();
		User u = udao.verify(phone, password);
		if (u != null) {
			System.out.println("Name:" + u.getName());
			System.out.println("Phone:" + u.getPhone());
			System.out.println("Emai:" + u.getEmail());
			System.out.println("Password:" + u.getPassword());

		} else {
			System.out.println("Invalid phone or password");
		}

	}

	public static void verifyByemailAndPassword() {
		System.out.println("Enter the email and password to verify User");
		String email = sc.next();
		String password = sc.next();
		User u = udao.verify(email, password);
		if (u != null) {
			System.out.println("Name:" + u.getName());
			System.out.println("Phone:" + u.getPhone());
			System.out.println("Emai:" + u.getEmail());
			System.out.println("Password:" + u.getPassword());

		} else {
			System.out.println("Invalid phone or password");
		}

	}

	public static void saveFoodOrder() {
		System.out.println("Enter the User Id to save the FoodOrder ");
		int id = sc.nextInt();
		System.out.println("Enter the food_item,cost,order_item,address");
		FoodOrder fo = new FoodOrder();
		fo.setFood_item(sc.next());
		fo.setCost(sc.nextDouble());
		fo.setOreders_item(sc.next());
		fo.setAddress(sc.next());
		fo = fdao.saveFoodOrder(fo, id);
		System.out.println("foodOrder saved with Id:" + fo.getId());
	}

	public static void updateFoodOrder() {
		System.out.println("Enter the User Id to update the FoodOrder ");
		int id = sc.nextInt();
		System.out.println("Enter the id, food_item,cost,order_item,address");
		FoodOrder fo = new FoodOrder();
		fo.setId(sc.nextInt());
		fo.setFood_item(sc.next());
		fo.setCost(sc.nextDouble());
		fo.setOreders_item(sc.next());
		fo.setAddress(sc.next());
		fo = fdao.updateFoodOrder(fo, id);
		if (fo != null) {
			System.out.println("foodOrder saved with Id:" + fo.getId());
		} else {
			System.out.println("Inavlid User Id");
		}
	}

	public static void findByFoodOrderId() {
		System.out.println("Enter the id to find Order Details");
		int id = sc.nextInt();
		FoodOrder fo = fdao.findById(id);
		if (fo != null) {
			System.out.println("Food_Item:" + fo.getFood_item());
			System.out.println("Cost:" + fo.getCost());
			System.out.println("Ordered_Item:" + fo.getOreders_item());
			System.out.println("address:" + fo.getAddress());
		} else {
			System.out.println("invalid Id");
		}
	}

	public static void findFoodOrderByUserId() {
		System.out.println("Enter the User Id");
		int id = sc.nextInt();
		try {
			List<FoodOrder> fo = fdao.findFoodOrderByUserId(id);
			for (FoodOrder f : fo) {
				System.out.println("Food_Item:" + f.getFood_item());
				System.out.println("Cost:" + f.getCost());
				System.out.println("Ordered_Item:" + f.getOreders_item());
				System.out.println("address:" + f.getAddress());
			}
		} catch (Exception e) {
			System.out.println("Invalid User Id");
		}

	}

	public static void findFoodOrderByUserPhoneAndPassword() {
		System.out.println("Enter the User phone and password");
		long phone = sc.nextLong();
		String password = sc.next();

		List<FoodOrder> fo = fdao.findFoodOrderByUserPhoneAndPassword(phone, password);
		if (fo.size() > 0) {
			for (FoodOrder f : fo) {
				System.out.println("Food_Item:" + f.getFood_item());
				System.out.println("Cost:" + f.getCost());
				System.out.println("Ordered_Item:" + f.getOreders_item());
				System.out.println("address:" + f.getAddress());
			}
		} else {
			System.out.println("Invalid User Phone or Password");
		}

	}
}

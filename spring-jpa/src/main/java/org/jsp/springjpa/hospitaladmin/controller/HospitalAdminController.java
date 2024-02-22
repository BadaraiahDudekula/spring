package org.jsp.springjpa.hospitaladmin.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.jsp.springjpa.hospitaladmin.HospitalAdminConfig;
import org.jsp.springjpa.hospitaladmin.dao.AdminDao;
import org.jsp.springjpa.hospitaladmin.dao.HospitalDao;
import org.jsp.springjpa.hospitaladmin.dto.Admin;
import org.jsp.springjpa.hospitaladmin.dto.Hospital;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class HospitalAdminController {
	static Scanner sc = new Scanner(System.in);
	static HospitalDao hdao;
	static AdminDao adao;
	static {
		ApplicationContext context = new AnnotationConfigApplicationContext(HospitalAdminConfig.class);
		hdao = context.getBean(HospitalDao.class);
		adao = context.getBean(AdminDao.class);
	}

	public static void main(String[] args) {
		System.out.println("1.save Admin");
		System.out.println("2.update admin");
		System.out.println("3.findById Admin");
		System.out.println("4.delete admin");
		System.out.println("5.verify Phone And Email Admin");
		System.out.println("6.save Hospital");
		System.out.println("7.update Hospital");
		System.out.println("8.findByHospitalId ");
		System.out.println("9.delete Hospital");
		System.out.println("10.find hospital by User Phone And Email Admin");
		System.out.println("11.find Hospital By User Id");
		System.err.println("choose one option");
		int key = sc.nextInt();
		switch (key) {
		case 1: {
			saveAdmin();
			break;
		}
		case 2: {
			updateAdmin();
			break;
		}
		case 3: {
			findByid();
			break;
		}
		case 4: {
			delete();
			break;
		}
		case 5: {
			findByPhoneAndEmail();
			break;
		}
		case 6: {
			saveHospital();
			break;
		}
		case 7: {
			updateHospital();
			break;
		}
		case 8: {
			findByHospitalId();
			break;
		}
		case 9: {
			deleteHospital();
			break;
		}
		case 10: {
			findHospitalByUserPhonAndEmail();
			break;
		}
		case 11: {
			findHospitalByUserId();
			break;
		}

		default:
			break;
		}
	}

	public static void saveAdmin() {
		System.out.println("Enter the name ,dob,phone and emialto save the admin");
		Admin a = new Admin();
		a.setName(sc.next());
		a.setDob(LocalDate.parse(sc.next()));
		a.setPhone(sc.nextLong());
		a.setEmail(sc.next());
		a = adao.saveAdmin(a);
		System.out.println("Admin Saved with Id:" + a.getId());
	}

	public static void updateAdmin() {
		System.out.println("enter the id to update the Admin");
		int id = sc.nextInt();
		Admin a = new Admin();
		System.out.println("Enter the name ,dob,phone and emialto save the admin");
		a.setId(id);
		a.setName(sc.next());
		a.setDob(LocalDate.parse(sc.next()));
		a.setPhone(sc.nextLong());
		a.setEmail(sc.next());
		a = adao.updateAdmin(a);
		System.out.println("Admin updated with Id:" + a.getId());
	}

	public static void findByid() {
		System.out.println("Enter the id to find Admin Details");
		int id = sc.nextInt();

		Admin ad = adao.findByid(id);
		if (ad != null) {
			System.out.println("Name:" + ad.getName());
			System.out.println("Dob:" + ad.getDob());
			System.out.println("Phone:" + ad.getPhone());
			System.out.println("Email:" + ad.getEmail());
		} else {
			System.out.println("Invalid Id");
		}
	}

	public static void delete() {
		System.out.println("Enter the id to delete the admin");
		int id = sc.nextInt();
		boolean deleted = adao.delete(id);
		if (deleted) {
			System.out.println("Admin Deleted");
		} else {
			System.out.println("Inavlid Id");
		}
	}

	public static void findByPhoneAndEmail() {
		System.out.println("Enter the phone and email to find admin Details");
		long phone = sc.nextLong();
		String email = sc.next();
		Admin a = adao.verify(phone, email);
		if (a != null) {
			System.out.println("Name:" + a.getName());
			System.out.println("Dob:" + a.getDob());
			System.out.println("Phone:" + a.getPhone());
			System.out.println("Email:" + a.getEmail());
		} else {
			System.out.println("Invalid phone or email");
		}
	}

	public static void saveHospital() {
		System.out.println("enter the User id to save with hospital");
		int id = sc.nextInt();
		System.out.println("enter the name,location to save hospital");
		Hospital h = new Hospital();
		h.setName(sc.next());
		h.setLocation(sc.next());
		h = hdao.save(h, id);
		if (h != null) {
			System.out.println("hospital saved with id" + h.getId());
		} else {
			System.out.println("Invalid User Id");
		}
	}

	public static void updateHospital() {
		System.out.println("enter the User id to update with hospital");
		int id = sc.nextInt();
		System.out.println("enter the id  name,location to save hospital");
		int id1 = sc.nextInt();
		Hospital h = new Hospital();
		h.setId(id1);
		h.setName(sc.next());
		h.setLocation(sc.next());
		h = hdao.update(h, id);
		if (h != null) {
			System.out.println("hospital saved with id" + h.getId());
		} else {
			System.out.println("Invalid User Id");
		}
	}
	public static void findByHospitalId() {
		System.out.println("enter the hospital Id to find Hodpital Details");
		int id=sc.nextInt();
		Hospital h=hdao.findById(id);
		if(h!=null) {
			System.out.println("name:"+h.getName());
			System.out.println("Location:"+h.getLocation());
		}else {
			System.out.println("Invalid Id");
		}
	}
	public static void deleteHospital() {
		System.out.println("enter the id To delete the hospital");
		int id=sc.nextInt();
		boolean del=hdao.delete(id);
		if(del) {
			System.out.println("hospital deleted");
		}else {
			System.out.println("invalid Id");
		}
	}
	public static void findHospitalByUserPhonAndEmail() {
		System.out.println("enter the User phone and email to get hospital Details");
		long phone=sc.nextLong();
		String email=sc.next();
		List<Hospital> h=hdao.findByUserPhoneAndEmail(phone, email);
		if(h.size()>0) {
			for(Hospital hp:h) {
				System.out.println("Id:"+hp.getId());
				System.out.println("name:"+hp.getName());
				System.out.println("Location:"+hp.getLocation());
			}
		}else {
			System.out.println("Invalid phone or email");
		}
	}
	public static void findHospitalByUserId() {
		System.out.println("enter the User Id to get hospital Details");
		int id=sc.nextInt();
		List<Hospital> h=hdao.findByUserId(id);
		if(h.size()>0) {
			for(Hospital hp:h) {
				System.out.println("Id:"+hp.getId());
				System.out.println("name:"+hp.getName());
				System.out.println("Location:"+hp.getLocation());
			}
		}else {
			System.out.println("Invalid phone or email");
		}
	}
}

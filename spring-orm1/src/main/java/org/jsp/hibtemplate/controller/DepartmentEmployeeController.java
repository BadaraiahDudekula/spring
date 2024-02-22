package org.jsp.hibtemplate.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.hibtemplate.dao.DepartmentDao;
import org.jsp.hibtemplate.dao.EmployeeDao;
import org.jsp.hibtemplate.dto.Department;
import org.jsp.hibtemplate.dto.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DepartmentEmployeeController {
	static Scanner sc = new Scanner(System.in);
	static DepartmentDao ddao = new DepartmentDao();
	static EmployeeDao edao = new EmployeeDao();
	static {
		ApplicationContext context = new ClassPathXmlApplicationContext("hib-template.xml");
		ddao = context.getBean(DepartmentDao.class);
		edao = context.getBean(EmployeeDao.class);
	}

	public static void main(String[] args) {
		System.out.println("1.saveDepratment");
		System.out.println("2.updateDepratment");
		System.out.println("3.find ByDept Id");
		System.out.println("4.delete department");
		System.out.println("5.save Employee");
		System.out.println("6.update Employee");
		System.out.println("7.Find By Emp Id");
		System.out.println("8.Delete emp");
		int choice = sc.nextInt();
		switch (choice) {
		case 1: {
			saveDept();
			break;
		}
		case 2: {
			updateDept();
			break;
		}
		case 3: {
			findById();
			break;
		}
		case 4: {
			delete();
			break;
		}
		case 5: {
			saveEmp();
			break;
		}
		case 6: {
			updateEmp();
			break;
		}
		case 7: {
			findByEmpId();
			break;
		}
		case 8: {
			deleteEmp();
			break;
		}

		default: {
			break;
		}
		}
	}

	public static void saveDept() {
		System.out.println("Enter the name and dept no to save the department");
		Department d = new Department();
		d.setName(sc.next());
		d.setD_no(sc.nextInt());
		d = ddao.save(d);
		System.out.println("Department Saved withe Id:" + d.getId());
	}

	public static void updateDept() {
		System.out.println("Enter the id, name and dept no to save the department");
		int id = sc.nextInt();
		Department d = new Department();
		d.setId(id);
		d.setName(sc.next());
		d.setD_no(sc.nextInt());
		d = ddao.update(d);
		System.out.println("Department update with  Id:" + d.getId());
	}

	public static void findById() {
		System.out.println("Enter the id to find department Details");
		int id = sc.nextInt();
		Department d = ddao.findById(id);
		if (d != null) {
			System.out.println("Name:" + d.getName());
			System.out.println("Dept_no:" + d.getD_no());
		} else {
			System.out.println("Invalid Id");
		}
	}

	public static void delete() {
		System.out.println("Enter the id delete the Dept");
		int id = sc.nextInt();
		boolean deleted = ddao.delete(id);
		if (deleted) {
			System.out.println("Department Deleted");
		} else {
			System.out.println("Cannot Delete as Invalid Id");
		}
	}

	public static void saveEmp() {
		System.out.println("enter the Dept Id to save with emp");
		int did = sc.nextInt();
		System.out.println("Enter the name,phone,email,sal and password to save the employee");
		Employee e = new Employee();
		e.setName(sc.next());
		e.setPhone(sc.nextLong());
		e.setEmail(sc.next());
		e.setSal(sc.nextInt());
		e.setPassword(sc.next());
		e = edao.save(e, did);
		if (e != null) {
			System.out.println("employee saved with id:" + e.getId());
		} else {
			System.out.println("Invalid department Id");
		}
	}

	public static void updateEmp() {
		System.out.println("enter the Dept Id to update with emp");
		int did = sc.nextInt();
		System.out.println("Enter theid, name,phone,email,sal and password to save the employee");
		Employee e = new Employee();
		e.setId(sc.nextInt());
		e.setName(sc.next());
		e.setPhone(sc.nextLong());
		e.setEmail(sc.next());
		e.setSal(sc.nextInt());
		e.setPassword(sc.next());
		e = edao.update(e, did);
		if (e != null) {
			System.out.println("employee saved with id:" + e.getId());
		} else {
			System.out.println("Invalid department Id");
		}
	}

	public static void findByEmpId() {
		System.out.println("Enter the id to find emp details");
		int id = sc.nextInt();
		Employee e = edao.findById(id);
		if (e != null) {
			System.out.println("Name:"+e.getName());
			System.out.println("Email:"+e.getEmail());
			System.out.println("Name:"+e.getPhone());
			System.out.println("Email:"+e.getSal());
			System.out.println("Name:"+e.getPassword());
		} else {
			System.out.println("Invalid Id");
		}
	}
	public static void deleteEmp() {
		System.out.println("Enter the id to find emp details");
		int id = sc.nextInt();
	    boolean deleted = edao.delete(id);
		if(deleted) {
			System.out.println("Emp deleted");
		}else {
			System.out.println("Invalid id");
		}
	}
	
}

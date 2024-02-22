package org.jsp.hibtemplate.hospitalbranch.controller;

import java.util.Scanner;

import org.jsp.hibtemplate.hospitalbranch.dao.BranchDao;
import org.jsp.hibtemplate.hospitalbranch.dao.HospitalDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HospitalBranchController {
	static Scanner sc=new Scanner(System.in);
	static HospitalDao hdao;
	static BranchDao bdao;
	static{
		ApplicationContext context = new ClassPathXmlApplicationContext("hib-template.xml");
		hdao=context.getBean(HospitalDao.class);
		bdao=context.getBean(BranchDao.class);
	}
	public static void main(String[] args) {

		System.out.println("");
		int key=sc.nextInt();
		switch (key) {
		case 1:{
			
			break;
		}
		default:
			break;
		}
	}
}

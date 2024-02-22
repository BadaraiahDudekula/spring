package org.jsp.springmvc.deptemp.controller;

import org.jsp.springmvc.deptemp.dao.DepartmentDao;
import org.jsp.springmvc.deptemp.dao.EmployeeDao;
import org.jsp.springmvc.deptemp.dto.Department;
import org.jsp.springmvc.deptemp.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeDao edao;
	@Autowired
	private DepartmentDao ddao;

	@RequestMapping(value = "/open-empregister")
	public ModelAndView openRegister(ModelAndView mav) {
		mav.setViewName("empregister");
		mav.addObject("emp", new Employee());
		return mav;
	}

	@ResponseBody
	@PostMapping(value = "/saveemp")
	public String saveEmp(@ModelAttribute(name="e") @RequestParam int id, Employee emp) {
		Department d = ddao.findById(id);
		if (d != null) {
			emp = edao.saveEmployee(emp, id);
			return "employee saved with id:" + emp.getId();
		}
		return "Invalid Department Id";
	}

}

package org.jsp.springmvc.deptemp.controller;

import org.jsp.springmvc.deptemp.dao.DepartmentDao;
import org.jsp.springmvc.deptemp.dto.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DepartmentController {
	@Autowired
	private DepartmentDao ddao;

	@RequestMapping(value = "/open-register")
	public ModelAndView openRegister() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("d", new Department());
		mav.setViewName("register");
		return mav;
	}

	@ResponseBody
	@PostMapping(value = "/save")
	public String saveDepartment(@ModelAttribute(name = "d") Department dept) {
		dept = ddao.saveDepartment(dept);
		return "Department Saved with Id:" + dept.getId();
	}

	@RequestMapping(value = "/open-update")
	public ModelAndView openUpdate(ModelAndView mav) {
		mav.addObject("d", new Department());
		mav.setViewName("update");
		return mav;
	}

	@ResponseBody
	@PostMapping(value = "/update")
	public String updateDepartment(@ModelAttribute(name = "d") Department dept) {
		dept = ddao.updateDepartment(dept);
		if (dept != null)
			return "Department Updated with Id" + dept.getId();
		return "Invalid Id";
	}

	@RequestMapping(value = "open-view")
	public String openview(@RequestParam String view) {
		return view;
	}

	@GetMapping("/delete")
	public ModelAndView deleteDepartment(@RequestParam int id, ModelAndView mav) {
		boolean deleted = ddao.delete(id);
		if (deleted) {
			mav.setViewName("error");
			mav.addObject("msg", "department Deleted");
			return mav;
		}
		mav.setViewName("error");
		mav.addObject("msg", "Invalid Id");
		return mav;
	}
	@GetMapping(value = "findById")
	public ModelAndView verify(@RequestParam int id, ModelAndView mav) {
		Department d = ddao.findById(id);
		if (d != null) {
			mav.setViewName("print");
			mav.addObject("d", d);
			return mav;
		}
		mav.setViewName("error");
		mav.addObject("msg", "Inavlid Id");
		return mav;
	}
}

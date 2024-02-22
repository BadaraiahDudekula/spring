package org.jsp.springmvc.merchantproduct.controller;

import org.jsp.springmvc.merchantproduct.dao.MerchantDao;
import org.jsp.springmvc.merchantproduct.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MerchantController {
	@Autowired
	public MerchantDao mdao;

	@RequestMapping("/open-register")
	public ModelAndView openRegister(ModelAndView mav) {
		mav.setViewName("register");
		mav.addObject("m", new Merchant());
		return mav;
	}

	@ResponseBody
	@PostMapping(value = "save")
	public String saveMerchant(@ModelAttribute(name = "u") Merchant merchant) {
		Merchant m = mdao.save(merchant);
		return "Merchant saved with Id:" + m.getId();
	}

	@RequestMapping("/open-update")
	public ModelAndView openUpdate(ModelAndView mav) {
		mav.setViewName("update");
		mav.addObject("m", new Merchant());
		return mav;
	}

	@ResponseBody
	@PostMapping(value = "update")
	public String updateMerchant(@ModelAttribute(name = "m") Merchant merchant) {
		Merchant m = mdao.findById(merchant.getId());
		if (m != null) {
			m = mdao.update(merchant);
			return "Merchant Updated with" + merchant.getId();
		}
		return "Invalid Id";
	}

	@RequestMapping(value = "open-view")
	public String setView(@RequestParam String view) {
		return view;
	}
	
	@GetMapping(value = "findById")
	public ModelAndView findById(@RequestParam int id, ModelAndView mav) {
		Merchant m = mdao.findById(id);
		if (m != null) {
			mav.setViewName("print");
			mav.addObject("m", "m");
			return mav;
		}
		mav.setViewName("error");
		mav.addObject("msg", "Invalid Id");
		return mav;

	}

}

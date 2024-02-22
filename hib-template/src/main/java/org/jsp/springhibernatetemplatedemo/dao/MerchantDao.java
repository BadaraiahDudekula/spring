package org.jsp.springhibernatetemplatedemo.dao;

import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

import org.jsp.springhibernatetemplatedemo.dto.Merchant;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class MerchantDao {
	
	private HibernateTemplate template;

	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	@Transactional
	public Merchant saveMerchant(Merchant merchant) {
		template.persist(merchant);
		return merchant;
	}
	@Transactional
	public Merchant updateMerchant(Merchant merchant) {
		Merchant m=template.get(Merchant.class, merchant.getId());
		if(m!=null) {
//			m.setEmail(merchant.getEmail());
//			m.setGst_number(merchant.getGst_number());
//			m.setPhone(merchant.getPhone());
//			m.setName(merchant.getName());
//			m.setPassword(merchant.getPassword());
			template.merge(merchant);
			return m;
		}
		return null;
	}
	@Transactional
	public Merchant findById(int id) {
		return template.get(Merchant.class, id);
	}
	@Transactional
	public boolean deleteById(int id) {
		Merchant m=findById(id);
		if(m!=null) {
			template.delete(m);
			return true;
		}
		return false;
	}
	

}

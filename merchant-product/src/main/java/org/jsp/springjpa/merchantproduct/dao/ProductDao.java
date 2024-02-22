package org.jsp.springjpa.merchantproduct.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.springjpa.merchantproduct.dto.Merchant;
import org.jsp.springjpa.merchantproduct.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	public EntityManager manager;

	public Product saveProduct(Product p, int id) {
		Merchant m = manager.find(Merchant.class, id);
		EntityTransaction t = manager.getTransaction();
		if (m != null) {
			p.setMerchant(m);
			m.getProduct().add(p);
			manager.persist(p);
			t.begin();
			t.commit();
			return p;
		}
		return null;
	}

	public Product findById(int id) {
		return manager.find(Product.class, id);
	}

	public Product updateProduct(Product p, int id) {
		EntityTransaction t = manager.getTransaction();
		Merchant m = manager.find(Merchant.class, id);
		if (m != null) {
			p.setMerchant(m);
			// m.getProduct().add(p);
			manager.merge(p);
			t.begin();
			t.commit();
			return p;
		}
		return null;
	}

	public boolean delete(int id) {
		Product p = manager.find(Product.class, id);
		if (p != null) {
			EntityTransaction t = manager.getTransaction();
			manager.remove(p);
			t.begin();
			t.commit();
			return true;
		}
		return false;
	}

	public List<Product> findProducts() {
		Query q = manager.createQuery("select p from Product p");

		return  q.getResultList();

	}
	public List<Product> findProductByMerchantId(int m_id) {
		Query q=manager.createQuery("select p from Product p where p.merchant.id=?1");
		q.setParameter(1, m_id);
		return q.getResultList();
		
	}
	public List<Product> findProductByBrand(String brand){
		Query q=manager.createQuery("select p from Product p where p.brand=?1");
		q.setParameter(1, brand);
		return q.getResultList();
	}

	public List<Product> findProductByCatagory(String catagory) {
		Query q=manager.createQuery("select p from Product p where p.catagory=?1");
		q.setParameter(1, catagory);
		return q.getResultList();
	}

}

package org.jsp.springjpa.userfood.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.springjpa.userfood.dto.FoodOrder;
import org.jsp.springjpa.userfood.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FoodOrderDao {
	@Autowired
	public EntityManager manager;

	public FoodOrder saveFoodOrder(FoodOrder order, int u_id) {
		User u = manager.find(User.class, u_id);
		if (u != null) {
			order.setUser(u);
			u.getOrder().add(order);
			EntityTransaction t = manager.getTransaction();
			manager.persist(order);
			t.begin();
			t.commit();
			return order;
		}
		return null;
	}

	public FoodOrder updateFoodOrder(FoodOrder order, int id) {
		User u = manager.find(User.class, id);
		if (u != null) {
			order.setUser(u);
			// u.getOrder().add(order);
			EntityTransaction t = manager.getTransaction();
			manager.merge(order);
			t.begin();
			t.commit();
			return order;
		}
		return null;
	}

	public FoodOrder findById(int id) {
		return manager.find(FoodOrder.class, id);
	}

	public List<FoodOrder> findFoodOrderByUserId(int id) {
		User u = manager.find(User.class, id);
		if (u != null) {
			Query q = manager.createQuery("select f from FoodOrder f where f.user.id=?1");
			q.setParameter(1, id);

			return q.getResultList();
		}
		return null;
	}

	public List<FoodOrder> findFoodOrderByUserPhoneAndPassword(long phone, String password) {
		Query q = manager.createQuery("select f from FoodOrder f where f.user.phone=?1 and f.user.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		return q.getResultList();
	}

}

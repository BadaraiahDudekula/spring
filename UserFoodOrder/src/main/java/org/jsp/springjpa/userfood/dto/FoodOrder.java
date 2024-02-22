package org.jsp.springjpa.userfood.dto;

import java.sql.Time;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class FoodOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String food_item;
	@Column(nullable = false)
	private double cost;
	@Column(nullable = false)
	private String oreders_item;
//	@CreationTimestamp
	@UpdateTimestamp
	private LocalDateTime d_time;
	@Column(nullable = false)
	private String address;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFood_item() {
		return food_item;
	}

	public void setFood_item(String food_item) {
		this.food_item = food_item;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getOreders_item() {
		return oreders_item;
	}

	public void setOreders_item(String oreders_item) {
		this.oreders_item = oreders_item;
	}

	public LocalDateTime getD_time() {
		return d_time;
	}

	public void setD_time(LocalDateTime d_time) {
		this.d_time = d_time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

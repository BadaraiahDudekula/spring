package org.jsp.springjpa.hospitaladmin;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "org.jsp.springjpa.hospitaladmin" })
public class HospitalAdminConfig {
	@Bean
	public EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("dev").createEntityManager();
	}

}

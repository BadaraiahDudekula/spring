package org.jsp.springjpa.merchantproduct;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.jsp.springjpa.merchantproduct")
public class MerchantProductCfg {
	@Bean
	public EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("dev").createEntityManager();
	}

}

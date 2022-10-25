package com.titan.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JPAPersistenceConfiguration {
     
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf)
    {
		JpaTransactionManager jpaTransactionManager=new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(emf);
		jpaTransactionManager.setRollbackOnCommitFailure(true);
		return jpaTransactionManager;
	}
}

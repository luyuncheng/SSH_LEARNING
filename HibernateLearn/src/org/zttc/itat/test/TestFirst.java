package org.zttc.itat.test;


import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;
import org.zttc.itat.model.User;

public class TestFirst {

	@SuppressWarnings("deprecation")
	@Test
	public void test01() {
		System.out.println("start");
		Configuration cfg=new Configuration().configure();
		System.out.println("");
		System.out.println("end");
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
					.applySettings(cfg.getProperties()).buildServiceRegistry();
		
		SessionFactory factory=cfg.buildSessionFactory(serviceRegistry);
		Session session =factory.openSession();
		//开启事务
		session.beginTransaction();
		
		User u = new User();
		u.setNickname("张三");
		u.setPassword("123");
		u.setUsername("张三");
		//u.setBorn(new Date());
		//System.out.println(u.getBorn().toString());
		session.save(u);
		//提交事务
		session.getTransaction().commit();
		
	}

}

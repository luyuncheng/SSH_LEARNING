package org.zttc.itat.test;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.junit.Test;
import org.zttc.itat.util.HibernateUtil;

public class TestHQL {
	
	@Test
	public void test01(){
		Session session =null;
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			session.getTransaction().commit();
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(session!=null){
				session.getTransaction().rollback();
			}
		}finally{
			 HibernateUtil.closeSession(session);
		}
	}
}

package org.zttc.itat.test;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.junit.Test;
import org.zttc.itat.model.User;
import org.zttc.itat.util.HibernateUtil;
public class TestCURD {
	
	
	@Test 
	public void tAdd(){
		Session session = null;
		
		try {
			session=HibernateUtil.openSession();
			session.beginTransaction();
			//===//

			User u = new User();
			u.setId(3);
			u.setNickname("luyuncheng");
			u.setPassword("123456");
			u.setUsername("luyuncheng");
			session.save(u);
			//===//
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(session!=null)session.getTransaction().rollback();
		} finally{
			 HibernateUtil.closeSession(session);
		}
	
	}
	@Test
	public void tLoad(){
		Session session = null;
		
		try {
			session=HibernateUtil.openSession();
			User u=(User) session.load(User.class, 1); 
			System.out.println(u);
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			 HibernateUtil.closeSession(session);
		}
	}
	@Test
    public void tDel(){
    	Session session = null;
		try {
			session=HibernateUtil.openSession();
			session.beginTransaction();
			//===//
			
//			User u=(User) session.load(User.class, 1);
//			System.out.println(u);
//			u.setNickname("lixiaosi");
//			System.out.println("st:updata");
			User u = new User();
			u.setId(0);
			session.delete(u);
//			System.out.println("ed:updata");
			//===//
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(session!=null)session.getTransaction().rollback();
		} finally{
			 HibernateUtil.closeSession(session);
		}
	}
    @Test
    public void tUpdate(){
		Session session = null;
		
		try {
			session=HibernateUtil.openSession();
			session.beginTransaction();
			//===//
			User u=(User) session.load(User.class, 1);
			System.out.println(u);
			u.setNickname("lixiaosi");
			System.out.println("st:updata");
			session.update(u);
			System.out.println("ed:updata");
			//===//
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(session!=null)session.getTransaction().rollback();
		} finally{
			 HibernateUtil.closeSession(session);
		}
    }
    @Test
    public void tList(){
    	Session session = null;
		
		try {
			session=HibernateUtil.openSession();
			List<User> users = session.createQuery("from User").list();
			for(User u : users)
			{
				System.out.println(u);
			}
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			 HibernateUtil.closeSession(session);
		}
    }
    @Test
    public void tListPage(){
    	Session session = null;
		
		try {
			session=HibernateUtil.openSession();
			List<User> users = session.createQuery("from User").setFirstResult(0).setMaxResults(3).list();
			for(User u : users)
			{
				System.out.println(u);
			}
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			 HibernateUtil.closeSession(session);
		}
    }
    
    @Test
    public void tQuery(){
    	
    }
}

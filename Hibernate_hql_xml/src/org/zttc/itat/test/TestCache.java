package org.zttc.itat.test;


import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;
import org.zttc.itat.model2.Student;
import org.zttc.itat.util.HibernateUtil;

@SuppressWarnings("unchecked")
public class TestCache {
	@Test
	public void test01() {
		Session session = null;
		try {
			/**
			 * 此时会发出一条sql取出所有的学生信息
			 */
			session = HibernateUtil.openSession();
			List<Student> ls = session.createQuery("from Student")
					.setFirstResult(0).setMaxResults(50).list();
			Iterator<Student> stus = ls.iterator();
			for(;stus.hasNext();) {
				Student stu = stus.next();
				System.out.println(stu.getName());
			}
			/**
			 * id=1的Student对象已经在session的缓存(一级缓存)中，此时就不会发sql去取Student
			 */
			Student stu = (Student)session.load(Student.class, 1);
			System.out.println(stu.getName()+",---");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
		try {
			session = HibernateUtil.openSession();
			/**
			 * 上一个Session已经关闭，此时又得重新取Student
			 */
			Student stu = (Student)session.load(Student.class, 1);
			System.out.println(stu.getName()+",---");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
	}
	
	@Test
	public void test02() {
		Session session = null;
		try {
			/**
			 * 如果使用iterator方法返回列表，对于hibernate而言，它仅仅只是发出取id列表的sql
			 * 在查询相应的具体的某个学生信息时，会发出相应的SQL去取学生信息
			 * 这就是典型的N+1问题
			 * 存在iterator的原因是，有可能会在一个session中查询两次数据，如果使用list每一次都会把所有的对象查询上来
			 * 而是要iterator仅仅只会查询id，此时所有的对象已经存储在一级缓存(session的缓存)中，可以直接获取
			 */
			session = HibernateUtil.openSession();
			Iterator<Student> stus = session.createQuery("from Student")
					.setFirstResult(0).setMaxResults(50).iterate();
			for(;stus.hasNext();) {
				Student stu = stus.next();
				System.out.println(stu.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
	}
	
	@Test
	public void test03() {
		Session session = null;
		try {
			/**
			 * 此时会发出一条sql取出所有的学生信息
			 */
			session = HibernateUtil.openSession();
			List<Student> ls = session.createQuery("from Student")
					.setFirstResult(0).setMaxResults(50).list();
			Iterator<Student> stus = ls.iterator();
			for(;stus.hasNext();) {
				Student stu = stus.next();
				System.out.println(stu.getName());
			}
			/**
			 * 使用iterate仅仅只会去Student的id，此时Student的数据已经在缓存中，所以不会在出现N+1
			 */
			stus = session.createQuery("from Student")
					.setFirstResult(0).setMaxResults(50).iterate();
			for(;stus.hasNext();) {
				Student stu = stus.next();
				System.out.println(stu.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
	}
	
	@Test
	public void test04() {
		Session session = null;
		try {
			/**
			 * 此时会发出一条sql取出所有的学生信息
			 */
			session = HibernateUtil.openSession();
			List<Student> ls = session.createQuery("from Student")
					.setFirstResult(0).setMaxResults(50).list();
			Iterator<Student> stus = ls.iterator();
			for(;stus.hasNext();) {
				Student stu = stus.next();
				System.out.println(stu.getName());
			}
			/**
			 * 会发出SQL取完整的学生对象，占用内存相对较多
			 */
			ls = session.createQuery("from Student")
					.setFirstResult(0).setMaxResults(50).list();
			stus = ls.iterator();
			for(;stus.hasNext();) {
				Student stu = stus.next();
				System.out.println(stu.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
	}
	
	@Test
	public void test05() {
		Session session = null;
		try {
			/**
			 * 此时会发出一条sql取出所有的学生信息
			 */
			session = HibernateUtil.openSession();
			List<Student> ls = session.createQuery("from Student")
					.setCacheable(true)//开启查询缓存，查询缓存也是SessionFactory级别的缓存
					.setFirstResult(0).setMaxResults(50).list();
			Iterator<Student> stus = ls.iterator();
			for(;stus.hasNext();) {
				Student stu = stus.next();
				System.out.println(stu.getName());
			}
			/**
			 * 会发出SQL取完整的学生对象，占用内存相对较多
			 */
			ls = session.createQuery("from Student")
					.setCacheable(true)
					.setFirstResult(0).setMaxResults(50).list();
			stus = ls.iterator();
			for(;stus.hasNext();) {
				Student stu = stus.next();
				System.out.println(stu.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
	}
	
	@Test
	public void test06() {
		Session session = null;
		try {
			/**
			 * 此时会发出一条sql取出所有的学生信息
			 */
			session = HibernateUtil.openSession();
			List<Student> ls = session.createQuery("from Student where name like ?")
					.setCacheable(true)//开启查询缓存，查询缓存也是SessionFactory级别的缓存
					.setParameter(0, "%王%")
					.setFirstResult(0).setMaxResults(50).list();
			Iterator<Student> stus = ls.iterator();
			for(;stus.hasNext();) {
				Student stu = stus.next();
				System.out.println(stu.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
		
		session = null;
		try {
			/**
			 * 此时会发出一条sql取出所有的学生信息
			 */
			session = HibernateUtil.openSession();
			List<Student> ls = session.createQuery("from Student where name like ?")
					.setCacheable(true)//开启查询缓存，查询缓存也是SessionFactory级别的缓存
					.setParameter(0, "%王%")
					.setFirstResult(0).setMaxResults(50).list();
			Iterator<Student> stus = ls.iterator();
			for(;stus.hasNext();) {
				Student stu = stus.next();
				System.out.println(stu.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
	}
	
	@Test
	public void test07() {
		Session session = null;
		try {
			/**
			 * 查询缓存缓存的不是对象而是id
			 */
			session = HibernateUtil.openSession();
			List<Student> ls = session.createQuery("from Student where name like ?")
					.setCacheable(true)//开启查询缓存，查询缓存也是SessionFactory级别的缓存
					.setParameter(0, "%王%")
					.setFirstResult(0).setMaxResults(50).list();
			Iterator<Student> stus = ls.iterator();
			for(;stus.hasNext();) {
				Student stu = stus.next();
				System.out.println(stu.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
		
		session = null;
		try {
			/**
			 * 查询缓存缓存的是id，此时由于在缓存中已经存在了这样的一组学生数据，但是仅仅只是缓存了
			 * id，所以此处会发出大量的sql语句根据id取对象，这也是发现N+1问题的第二个原因
			 * 所以如果使用查询缓存必须开启二级缓存
			 */
			session = HibernateUtil.openSession();
			List<Student> ls = session.createQuery("from Student where name like ?")
					.setCacheable(true)//开启查询缓存，查询缓存也是SessionFactory级别的缓存
					.setParameter(0, "%王%")
					.setFirstResult(0).setMaxResults(50).list();
			Iterator<Student> stus = ls.iterator();
			for(;stus.hasNext();) {
				Student stu = stus.next();
				System.out.println(stu.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateUtil.close(session);
		}
	}
	
}

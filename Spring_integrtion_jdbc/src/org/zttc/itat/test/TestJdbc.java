package org.zttc.itat.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zttc.itat.dao.IGroupDao;
import org.zttc.itat.dao.IUserDao;
import org.zttc.itat.model.Group;
import org.zttc.itat.model.User;

/**
 * 当使用了以下注释之后，就可以直接在Test中进行依赖注入
 */
//让Junit运行在Spring的测试环境中
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")//加载beans.xml文件
public class TestJdbc {
	@Resource(name="userJdbcDao")
	private IUserDao userJdbcDao;
	@Resource(name="groupJdbcDao")
	private IGroupDao groupJdbcDao;
	
	@Test
	public void testAdd() {
		Group g = new Group();
		g.setName("文章审核人员");
		groupJdbcDao.add(g);
		System.out.println(g.getId());
		User u = new User("tangsheng","123","唐僧");
		userJdbcDao.add(u, 1);
	}
	
	@Test
	public void testUpdate() {
		User u = new User("zhangfeng","123","张峰");
		u.setId(1);
		userJdbcDao.update(u);
	}
	
	@Test
	public void testDelete() {
		userJdbcDao.delete(1);
	}
	
	@Test
	public void testLoad() {
		User u = userJdbcDao.load(2);
		System.out.println(u.getNickname()+","+u.getGroup().getName());
	}
	
	@Test
	public void testList() {
		List<User> us = userJdbcDao.list("select t1.id uid,t1.*,t2.* from t_user t1 left join t_group t2 on(t1.gid=t2.id)", null);
		for(User u:us) {
			System.out.println(u);
		}
	}
}

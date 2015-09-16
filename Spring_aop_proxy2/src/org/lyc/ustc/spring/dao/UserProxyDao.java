package org.lyc.ustc.spring.dao;


import javax.annotation.Resource;

import org.lyc.ustc.spring.log.Logger;
import org.lyc.ustc.spring.model.User;
import org.springframework.stereotype.Component;

@Component("userProxyDao")
public class UserProxyDao implements IUserDao {
	private IUserDao userDao;

	@Override
	public void add(User use) {
		// TODO Auto-generated method stub
		System.out.println("擦擦擦擦");
		Logger.info("测试aop——proxy");
		//userDao.add(use);
	}
	@Resource
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

		System.out.println("擦擦擦擦");
	}

	@Override
	public User load(int id) {
		// TODO Auto-generated method stub

		System.out.println("擦擦擦擦");
		return null;
	}

}

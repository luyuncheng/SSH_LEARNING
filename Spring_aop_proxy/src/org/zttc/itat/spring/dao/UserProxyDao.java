package org.zttc.itat.spring.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.zttc.itat.spring.model.User;
import org.zttc.itat.spring.proxy.Logger;

@Component("userProxyDao")
public class UserProxyDao implements IUserDao{
	private IUserDao userDao;
	
	
	public IUserDao getUserDao() {
		return userDao;
	}
	@Resource
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void add(User user) {
		Logger.info("代理info");
		userDao.add(user);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User load(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}

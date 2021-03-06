package org.lyc.ustc.spring.service;

import org.lyc.ustc.spring.dao.IUserDao;
import org.lyc.ustc.spring.model.User;

public class UserService implements IUserService {
	private IUserDao userDao;
	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		userDao.add(user);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
	}

	@Override
	public User load(int id) {
		// TODO Auto-generated method stub
		
		return userDao.load(id);
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

//	public UserService(IUserDao userDao) {
//		super();
//		this.userDao = userDao;
//	}
 
}

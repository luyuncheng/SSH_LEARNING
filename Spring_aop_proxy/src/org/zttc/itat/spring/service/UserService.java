package org.zttc.itat.spring.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.zttc.itat.spring.dao.IUserDao;
import org.zttc.itat.spring.model.User;

//@Component("userService")
@Service("userService")//业务层一般使用@Service
public class UserService implements IUserService {
	private IUserDao userDao;
	private IUserDao userJDBCDao;
	

	public IUserDao getUserDao() {
		return userDao;
	}
	
	//默认通过名称注入，在JSR330中提供了@Inject来注入
	@Resource(name="userDynamicDao")
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void add(User user) {
		userDao.add(user);
	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
	}

	@Override
	public User load(int id) {
		return userDao.load(id);
	}

}

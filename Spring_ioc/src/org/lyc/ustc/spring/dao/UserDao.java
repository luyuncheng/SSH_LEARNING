package org.lyc.ustc.spring.dao;

import org.lyc.ustc.spring.model.User;

public class UserDao implements IUserDao {

	@Override
	public void add(User use) {
		// TODO Auto-generated method stub
		System.out.println("add a user :"+use.toString());
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		System.out.println("delete a user with id:"+id);
	}

	@Override
	public User load(int id) {
		// TODO Auto-generated method stub
		System.out.println("load:"+id);
		return null;
	}

}

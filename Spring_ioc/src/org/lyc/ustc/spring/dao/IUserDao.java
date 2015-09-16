package org.lyc.ustc.spring.dao;

import org.lyc.ustc.spring.model.User;

public interface IUserDao {
	public void add(User use);
	public void delete(int id);
	public User load(int id);	
}

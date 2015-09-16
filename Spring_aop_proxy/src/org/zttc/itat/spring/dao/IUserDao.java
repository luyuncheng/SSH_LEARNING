package org.zttc.itat.spring.dao;

import org.zttc.itat.spring.model.User;

public interface IUserDao {
	public void add(User user);
	public void delete(int id);
	public User load(int id);
}

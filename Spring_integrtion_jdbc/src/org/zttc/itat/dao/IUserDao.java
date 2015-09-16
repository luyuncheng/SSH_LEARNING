package org.zttc.itat.dao;

import java.util.List;

import org.zttc.itat.model.User;

public interface IUserDao {
	public void add(User user,int gid);
	public void update(User user);
	public void delete(int id);
	public User load(int id);
	public List<User> list(String sql,Object[] args);
}

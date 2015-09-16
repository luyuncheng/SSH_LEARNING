package org.zttc.itat.spring.service;

import org.zttc.itat.spring.model.User;

public interface IUserService {
	public void add(User user);
	public void delete(int id);
	public User load(int id);
}

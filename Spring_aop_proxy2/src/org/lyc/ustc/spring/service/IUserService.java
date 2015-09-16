package org.lyc.ustc.spring.service;

import org.lyc.ustc.spring.model.User;

public interface IUserService {
	public void add(User user);
	public void delete(int id);
	public User load(int id);
}

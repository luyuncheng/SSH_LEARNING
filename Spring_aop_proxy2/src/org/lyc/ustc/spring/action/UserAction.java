package org.lyc.ustc.spring.action;

import java.util.List;

import org.lyc.ustc.spring.dao.IUserDao;
import org.lyc.ustc.spring.model.User;
import org.lyc.ustc.spring.service.IUserService;

public class UserAction {
	private User user;
	private IUserService userService;
	private int id;
	private List<String> names;
	
	public UserAction() {
		// TODO Auto-generated constructor stub
	}
	
	public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	}
	//	public UserAction(IUserService userService) {
//		super();
//		this.userService = userService;
//	}
	public void add(){

		System.out.println(user);
		System.out.println(names);
		userService.add(user);
	}
	public void delete(){
		userService.delete(id);
	}
	public void load(){
		userService.load(id);
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userservice) {
		this.userService = userservice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	
	
}

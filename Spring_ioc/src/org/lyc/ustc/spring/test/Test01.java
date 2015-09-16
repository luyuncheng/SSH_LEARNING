 package org.lyc.ustc.spring.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.lyc.ustc.spring.action.UserAction;
import org.lyc.ustc.spring.model.HelloWorld;
import org.lyc.ustc.spring.model.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test01 {
//创建spring的工厂
	private BeanFactory factory = new ClassPathXmlApplicationContext("beans.xml");

	@Test
	public void testhello(){
		//通过工厂创建spring的对象
		 HelloWorld hello = (HelloWorld)factory.getBean("helloworld");
		 HelloWorld hello2= factory.getBean("helloworld",HelloWorld.class); 
		 System.out.println(hello.hello());
		 System.out.println(hello2.hello());
	}
	
	@Test
	public void test01(){
		 UserAction ua=factory.getBean("userAction",UserAction.class);
		 User u =new User(1,"wukong");
		 ua.setUser(u);
		 ua.add();
		 
		 UserAction ua2=factory.getBean("userAction",UserAction.class);
		 ua2 .add();
		 
	}
}

package org.zttc.itat.spring.test;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zttc.itat.spring.action.UserAction;
import org.zttc.itat.spring.model.HelloWorld;
import org.zttc.itat.spring.model.User;

public class TestSpring {
	//创建Spring的工厂
	private BeanFactory factory = new ClassPathXmlApplicationContext("beans.xml");
	
	@Test
	public void testHello() {
		//通过工厂获取Spring的对象
		//此处getBean中的helloWorld就是beans.xml配置文件中的id
//		HelloWorld hello = (HelloWorld)factory.getBean("helloWorld");
		HelloWorld hello = factory.getBean("helloWorld",HelloWorld.class);
		//此时的hello对象就是被Spring说管理的对象
		System.out.println(hello.hello());
		
		HelloWorld hello2 = factory.getBean("helloWorld",HelloWorld.class);
		//如果在bean中没有作scope的配置，默认是singleton（单例）,当把bean中的scope设置为prototype的时候就是多例
		System.out.println(hello==hello2);
	}
	
	@Test
	public void testUser01() {
		UserAction ua1 = factory.getBean("userAction", UserAction.class);
		User u = new User(1,"悟空");
		ua1.setUser(u);
		ua1.add();
		
		UserAction ua2 = factory.getBean("userAction", UserAction.class);
		ua2.add();
	}
}

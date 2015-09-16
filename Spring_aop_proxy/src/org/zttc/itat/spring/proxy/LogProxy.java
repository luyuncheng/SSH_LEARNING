package org.zttc.itat.spring.proxy;

import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogProxy implements InvocationHandler {
	
	private Object target;
	private LogProxy(){}
	public static Object getInstance(Object o){
		LogProxy proxy=new LogProxy();
		proxy.target=o;
		Object result=Proxy.newProxyInstance(o.getClass().getClassLoader(), 
				o.getClass().getInterfaces(),proxy );
		return result;
		
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Logger.info("进行了相应的操作");
		Object obj=method.invoke(target, args);
		// TODO Auto-generated method stub
		return obj;
	}

}
package org.zttc.itat.test;

import org.junit.Test;
import org.zttc.itat.template.MessageDao;
import org.zttc.itat.template.MyJdbcTemplateByIn;
import org.zttc.itat.template.RoleDao;

public class TestTemplate {

	@Test
	public void test01() {
		MyJdbcTemplateByIn mt = new RoleDao();
		mt.execute();
		MyJdbcTemplateByIn msgt = new MessageDao();
		msgt.execute();
		
		
	}
	
	@Test
	public void test02() {
		RoleDao rd = new RoleDao();
		rd.add(1);
	}
}

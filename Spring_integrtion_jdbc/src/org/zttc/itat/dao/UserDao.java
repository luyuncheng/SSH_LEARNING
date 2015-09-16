package org.zttc.itat.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.zttc.itat.model.Group;
import org.zttc.itat.model.User;


@Repository("userJdbcDao")
public class UserDao implements IUserDao {
	private JdbcTemplate jdbcTemplate;
	
	
	
	@Resource
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void add(User user,int gid) {
		jdbcTemplate.update("insert into t_user(username,password,nickname,gid) value (?,?,?,?)",
				user.getUsername(),user.getPassword(),user.getNickname(),gid);
	}

	@Override
	public void update(User user) {
		jdbcTemplate.update("update t_user set username=?,password=?,nickname=? where id=?",
				user.getUsername(),user.getPassword(),user.getNickname(),user.getId());
	}

	@Override
	public void delete(int id) {
		jdbcTemplate.update("delete from t_user where id=?",id);
	}

	@Override
	public User load(int id) {
		String sql = "select t1.id uid,t1.*,t2.* from t_user t1 left join t_group t2 on(t1.gid=t2.id) where t1.id=?";
		/*
		 * 第一个参数是SQL语句
		 * 第二个参数是SQL语句中的参数值，需要传入一个对象数组
		 * 第三个参数是一个RowMapper,这个rowMapper可以完成一个对象和数据库字段的对应，实现这个RowMapper需要
		 * 实现mapRow方法，在mapRow方法中有rs这个参数，通过rs可以有效的获取数据库的字段
		 * 如果这个方法在该DAO中会被重复使用，建议通过内部类来解决，而不要使用匿名的内部类
		 */
		User u = (User)jdbcTemplate.queryForObject(sql, new Object[]{id},new UserMapper());
		return u;
	}

	@Override
	public List<User> list(String sql,Object[] args) {
		String sqlCount = "select count(*) from t_user";
		//获取整数值
		int count = jdbcTemplate.queryForInt(sqlCount);
		System.out.println(count);
		String nCount = "select nickname from t_user";
		//获取String类型的列表
		List<String> ns = jdbcTemplate.queryForList(nCount,String.class);
		for(String n:ns) {
			System.out.println("--->"+n);
		}
		String tSql = "select username,nickname from t_user";
		//无法取出user
		/*List<User> us = jdbcTemplate.queryForList(tSql, User.class);
		for(User u:us) {
			System.out.println(u);
		}*/
		//对象数组也无法返回
		/*List<Object[]> os = jdbcTemplate.queryForList(tSql, Object[].class);
		for(Object[] oo:os) {
			System.out.println(oo[0]+","+oo[1]);
		}*/
		
		List<User> us = jdbcTemplate.query(tSql,new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User u = new User();
				u.setNickname(rs.getString("nickname"));
				u.setUsername(rs.getString("username"));
				return u;
			}
		});
		for(User u:us) {
			System.out.println(u);
		}
		return jdbcTemplate.query(sql, args, new UserMapper());
	}
	
	private class UserMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			Group g = new Group();
			g.setName(rs.getString("name"));
			g.setId(rs.getInt("gid"));
			User u = new User();
			u.setGroup(g);
			u.setId(rs.getInt("uid"));
			u.setNickname(rs.getString("nickname"));
			u.setPassword(rs.getString("password"));
			u.setUsername(rs.getString("username"));
			return u;
		}
	}

}

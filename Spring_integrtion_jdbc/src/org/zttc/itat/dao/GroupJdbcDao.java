package org.zttc.itat.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.zttc.itat.model.Group;

@Repository("groupJdbcDao")
public class GroupJdbcDao implements IGroupDao {
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public void add(final Group group) {
		/**
		 * 通过以下方法可以添加一个对象，并且获取这个对象自动递增的id
		 */
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				String sql = "insert into t_group (name) value(?)";
				PreparedStatement ps = con.prepareStatement(sql,new String[]{"id"});
				ps.setString(1, group.getName());
				return ps;
			}
		},keyHolder);
		group.setId(keyHolder.getKey().intValue());
	}

}

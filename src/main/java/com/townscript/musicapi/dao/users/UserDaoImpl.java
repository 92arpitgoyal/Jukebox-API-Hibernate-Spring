package com.townscript.musicapi.dao.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.townscript.musicapi.dao.JdbcTemplateFactory;
import com.townscript.musicapi.model.Users;
import com.townscript.musicapi.model.UsersRowMapper;

public class UserDaoImpl implements UserDao {


	
	@Override
	public int addUser(final Users user) {
		final String sql = "INSERT INTO USERS (USER_ID, USER_NAME, USER_EMAIL, USER_PASSWORD) VALUES (" + user.getId()+ ", '" + user.getName() +"', '"+ user.getEmail()+"', '"+ user.getPassword()+"')";
	 
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		  new PreparedStatementCreator() {
			    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			      return connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
			    }
			  }, keyHolder);

		return keyHolder.getKey().intValue();
	}

	@Override
	public Users readUser(int userId) {
		String sql = "SELECT * FROM USERS " +
				"WHERE USER_ID = "+ userId;
	 
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
	 
		List<Users> usersList = jdbcTemplate.query(sql, new UsersRowMapper());
		
		if(usersList == null || usersList.isEmpty()){
			return null;
		}
		else{
			return usersList.get(0);
		}
	}

	@Override
	public void updateUser(Users user) {
		String sql = "UPDATE USERS SET USER_NAME = ?, USER_EMAIL = ?, USER_PASSWORD = ? " +
				"WHERE USER_ID = ?" ;
		Object[] params = { user.getName(), user.getEmail(), user.getPassword(), user.getId() };
		int[] types = {Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BIGINT};
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql, params, types);
		
	}

	@Override
	public void deleteUser(int userId) {
		String sql = "DELETE FROM USERS " +
				"WHERE USER_ID = "+ userId;
		 
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		
		Users user = new Users();
		jdbcTemplate.execute(sql);
	}

	@Override
	public boolean isAuthenticUser(String userEmail, String UserPassword) {
		String sql = "SELECT * FROM USERS " +
				"WHERE USER_EMAIL = '"+ userEmail+"' AND USER_PASSWORD = '"+ UserPassword + "'";
		 
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();

		List<Users> users = jdbcTemplate.query(sql, new UsersRowMapper());
		if(users == null || users.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}

}

package com.townscript.musicapi.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.townscript.musicapi.model.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("USER_ID"));
		user.setName(rs.getString("USER_NAME"));
		user.setEmail(rs.getString("USER_EMAIL"));
		user.setPassword(rs.getString("USER_PASSWORD"));	
		
		return user;
	}

}
package com.townscript.musicapi.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.townscript.musicapi.model.Users;

public class UsersRowMapper implements RowMapper<Users> {

	@Override
	public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
		Users user = new Users();
		user.setId(rs.getInt("USER_ID"));
		user.setName(rs.getString("USER_NAME"));
		user.setEmail(rs.getString("USER_EMAIL"));
		user.setPassword(rs.getString("USER_PASSWORD"));	
		
		return user;
	}

}
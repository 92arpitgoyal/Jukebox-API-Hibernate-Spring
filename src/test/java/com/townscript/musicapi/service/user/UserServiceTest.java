package com.townscript.musicapi.service.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.townscript.musicapi.dao.JdbcTemplateFactory;
import com.townscript.musicapi.model.User;
import com.townscript.musicapi.model.UserRowMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/com/townscript/musicapi/test-beans.xml")
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Before
	public void createEnvironMent()
	{
		String sql = "DELETE FROM USERS";
		 
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql);
		
		System.out.println("Set Up Environment");
	}
	
	@After
	public void clearEnvironMent()
	{
		String sql = "DELETE FROM USERS";
		 
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql);
		
		System.out.println("Cleared Environment");
	}
	
	@Test
	public void testNewUser(){
		
		User user = new User();
		user.setName("ramesh walia");
		user.setPassword("qwerty123");
		user.setEmail("rameshwalia@ts.com");
		
		int userId = userService.newUser(user);
		
		String sql = "SELECT * FROM USERS WHERE USER_ID = " + userId;
		
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		 
		List<User> usersList = jdbcTemplate.query(sql, new UserRowMapper());
		
		Assert.assertEquals(userId, usersList.get(0).getId());
		Assert.assertEquals(user.getName(), usersList.get(0).getName());
		Assert.assertEquals(user.getEmail(), usersList.get(0).getEmail());
		Assert.assertEquals(user.getPassword(), usersList.get(0).getPassword());
		
		
	}
	
	@Test
	public void testAccessUser(){
		final String sql = "INSERT INTO USERS (USER_ID, USER_NAME, USER_EMAIL, USER_PASSWORD) VALUES ( 0 , 'ramesh falan', 'rameshfalan@ts.com', 'qwerty123')";
		 
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		  new PreparedStatementCreator() {
			    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			      return connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
			    }
			  }, keyHolder);

		int userId  =  keyHolder.getKey().intValue();
		
		User user = userService.accessUser(userId);
		
		Assert.assertEquals(user.getId(), userId);
		Assert.assertEquals(user.getName(), "ramesh falan");
		Assert.assertEquals(user.getEmail(), "rameshfalan@ts.com");
		Assert.assertEquals(user.getPassword(), "qwerty123");	
		
	}
	
	@Test
	public void testEditUser(){
		final String sql = "INSERT INTO USERS (USER_ID, USER_NAME, USER_EMAIL, USER_PASSWORD) VALUES ( 0 , 'ramesh falan', 'rameshfalan@ts.com', 'qwerty123')";
		 
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		  new PreparedStatementCreator() {
			    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			      return connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
			    }
			  }, keyHolder);

		int userId  =  keyHolder.getKey().intValue();
		User user =  new User();
		user.setId(userId);
		user.setName("ramesh falan");
		user.setEmail("rameshfalan@ts.com");
		user.setPassword("qwerty1234");
		
		userService.editUser(user);
		
		String sqlForAccess = "SELECT * FROM USERS " +
				"WHERE USER_ID = "+ userId;
	 
		JdbcTemplate jdbcTemplateForAccess = JdbcTemplateFactory.getJdbcTemplate();
	 
		List<User> usersList = jdbcTemplateForAccess.query(sqlForAccess, new UserRowMapper());
		

		Assert.assertEquals(usersList.get(0).getPassword(), user.getPassword());	
	}
	
	@Test
	public void testRemoveUser(){
		final String sql = "INSERT INTO USERS (USER_ID, USER_NAME, USER_EMAIL, USER_PASSWORD) VALUES ( 0 , 'ramesh falan', 'rameshfalan@ts.com', 'qwerty123')";
		 
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		  new PreparedStatementCreator() {
			    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			      return connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
			    }
			  }, keyHolder);

		int userId  =  keyHolder.getKey().intValue();
		
		userService.removeUser(userId);
		
		String sqlForAccess = "SELECT * FROM USERS " +
				"WHERE USER_ID = "+ userId;
	 
		JdbcTemplate jdbcTemplateForAccess = JdbcTemplateFactory.getJdbcTemplate();
	 
		List<User> usersList = jdbcTemplateForAccess.query(sqlForAccess, new UserRowMapper());

		Assert.assertTrue(usersList.isEmpty());
		
	}
	
	@Test
	public void testAuthenticUserTrue(){
		final String sql = "INSERT INTO USERS (USER_ID, USER_NAME, USER_EMAIL, USER_PASSWORD) VALUES ( 0 , 'ramesh falan', 'rameshfalan@ts.com', 'qwerty123')";
		 
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		  new PreparedStatementCreator() {
			    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			      return connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
			    }
			  }, keyHolder);

		int userId  =  keyHolder.getKey().intValue();
		
		boolean bool = userService.authenticUser("rameshfalan@ts.com", "qwerty123");
		Assert.assertTrue(bool);
	}
	
	@Test
	public void testAuthenticUserFalse(){
		final String sql = "INSERT INTO USERS (USER_ID, USER_NAME, USER_EMAIL, USER_PASSWORD) VALUES ( 0 , 'ramesh falan', 'rameshfalan@ts.com', 'qwerty123')";
		 
		JdbcTemplate jdbcTemplate = JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
		  new PreparedStatementCreator() {
			    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			      return connection.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);
			    }
			  }, keyHolder);

		int userId  =  keyHolder.getKey().intValue();
		
		boolean bool = userService.authenticUser("rameshfalan@ts.com", "qwerty1234");
		Assert.assertFalse(bool);
	}
}
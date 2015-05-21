package com.townscript.musicapi.cache;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cache.EhCacheProvider;
import org.junit.After;
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
import com.townscript.musicapi.service.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/com/townscript/musicapi/test-beans.xml")
public class CacheTest{

	@Autowired private SessionFactory sessionFactory;
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
	public void testWithCache(){
		
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
		Session session = sessionFactory.openSession();
		session.beginTransaction();

//		first level cache test
		
		for(int x = 0; x < 2; x = x+1) {
			long currentTime  = System.currentTimeMillis();
			User user = (User) session.load(User.class, userId);
			long afterTime = System.currentTimeMillis();
			System.out.println("Time taken : "+ (afterTime - currentTime));
		}
		session.close();
		
//		second level cache test
		
		System.out.println("session closed");
		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();
		System.out.println("new session opened");
		
		long currentTimeWith2ndLevel  = System.currentTimeMillis();
		User user2 = (User) session2.load(User.class, userId);
		long afterTimeWith2ndLevel = System.currentTimeMillis();
		System.out.println("Time taken : "+ (afterTimeWith2ndLevel - currentTimeWith2ndLevel));
		session2.close();

//		query cache test
		Session session3 = sessionFactory.openSession();
		session3.beginTransaction();
		String queryString = "FROM "+ User.class.getName() +" WHERE id = :id";
		
		Query query = session3.createQuery(queryString);
		query.setParameter("id", 1);
		query.setCacheable(true);
		long currentTimeWithoutQueryCache  = System.currentTimeMillis();
		List userList = query.list();
		long afterTimeWithoutQueryCache = System.currentTimeMillis();
		System.out.println("Time taken : "+ (afterTimeWithoutQueryCache - currentTimeWithoutQueryCache));

		Query query2 = session3.createQuery(queryString);
		query2.setParameter("id", 1);
		query2.setCacheable(true);
		long currentTimeWithQueryCache  = System.currentTimeMillis();
		List userList2 = query2.list();
		long afterTimeWithQueryCache = System.currentTimeMillis();
		System.out.println("Time taken : "+ (afterTimeWithQueryCache - currentTimeWithQueryCache));
	}
}

package com.townscript.musicapi.dao.user;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.townscript.musicapi.model.User;

@Repository
public class UserDaoHnateImpl extends HibernateDaoSupport implements UserDao{

	@Override
	public int addUser(User user) {
		getHibernateTemplate().save(user);
		return user.getId();
	}

	@Override
	public User readUser(int userId) {
//		String queryString = "FROM "+ User.class.getName() +" WHERE id = :userId";
//		
//		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//		Query query = session.createQuery(queryString);
//
//		query.setParameter("userId", userId);
		
		long currentTime  = System.currentTimeMillis();
//		List<User> userList = query.list();
		User user = getHibernateTemplate().load(User.class, userId);
		long afterTime = System.currentTimeMillis();
		
		System.out.println("Time taken : "+ (afterTime - currentTime));
		return user;
		//return userList.get(0);
	}

	@Override
	public void updateUser(User user) {
		getHibernateTemplate().update(user);
	}

	@Override
	public void deleteUser(int userId) {
		User user = new User();
		user.setId(userId);
		
		getHibernateTemplate().delete(user);
	}

	@Override
	public boolean isAuthenticUser(String userEmail, String userPassword) {
		String queryString = "FROM "+ User.class.getName() +" WHERE email = :Email AND password = :Password";
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(queryString);

		query.setParameter("Email", userEmail);
		query.setParameter("Password", userPassword);

		List<User> userList = query.list();
		if(userList == null || userList.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}

}

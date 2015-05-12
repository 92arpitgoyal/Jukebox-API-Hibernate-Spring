package com.townscript.musicapi.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.townscript.musicapi.dao.user.UserDao;
//import com.townscript.musicapi.dao.user.UserDaoHnateImpl;
//import com.townscript.musicapi.dao.user.UserDaoImpl;
import com.townscript.musicapi.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	//private UserDao userDao = new UserDaoImpl();
	@Autowired
	private UserDao userDao;
	
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public int newUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public User accessUser(int userId) {
		return userDao.readUser(userId);
	}

	@Override
	public void editUser(User user) {
		userDao.updateUser(user);
		
	}

	@Override
	public void removeUser(int userId) {
		userDao.deleteUser(userId);
	}

	@Override
	public boolean authenticUser(String userEmail, String UserPassword) {
		return userDao.isAuthenticUser(userEmail, UserPassword);
	}

}

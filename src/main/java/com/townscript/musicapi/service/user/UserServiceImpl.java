package com.townscript.musicapi.service.user;

import com.townscript.musicapi.dao.user.UserDao;
import com.townscript.musicapi.dao.user.UserDaoImpl;
import com.townscript.musicapi.model.User;

public class UserServiceImpl implements UserService {

	UserDao userDao = new UserDaoImpl();
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

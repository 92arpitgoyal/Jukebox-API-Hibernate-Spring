package com.townscript.musicapi.service.users;

import com.townscript.musicapi.dao.users.UserDao;
import com.townscript.musicapi.dao.users.UserDaoImpl;
import com.townscript.musicapi.model.Users;

public class UsersServiceImpl implements UsersService {

	UserDao userDao = new UserDaoImpl();
	@Override
	public int newUser(Users user) {
		return userDao.addUser(user);
	}

	@Override
	public Users accessUser(int userId) {
		return userDao.readUser(userId);
	}

	@Override
	public void editUser(Users user) {
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

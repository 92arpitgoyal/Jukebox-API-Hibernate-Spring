package com.townscript.musicapi.dao.user;

import com.townscript.musicapi.model.User;

public interface UserDao {
	public int addUser(User user);
	public User readUser(int userId);
	public void updateUser(User user);
	public void deleteUser(int userId);
	public boolean isAuthenticUser(String userEmail, String userPassword);
}

package com.townscript.musicapi.dao.users;

import com.townscript.musicapi.model.Users;

public interface UserDao {
	public int addUser(Users user);
	public Users readUser(int userId);
	public void updateUser(Users user);
	public void deleteUser(int userId);
	public boolean isAuthenticUser(String userEmail, String UserPassword);
}

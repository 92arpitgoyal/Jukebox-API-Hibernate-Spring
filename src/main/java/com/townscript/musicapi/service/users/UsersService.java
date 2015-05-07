package com.townscript.musicapi.service.users;

import com.townscript.musicapi.model.Users;

public interface UsersService {
	public int newUser(Users user);
	public Users accessUser(int userId);
	public void editUser(Users user);
	public void removeUser(int userId);
	public boolean authenticUser(String userEmail, String UserPassword);
}

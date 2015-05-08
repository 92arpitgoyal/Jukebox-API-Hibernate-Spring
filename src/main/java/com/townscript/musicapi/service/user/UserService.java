package com.townscript.musicapi.service.user;

import com.townscript.musicapi.model.User;

public interface UserService {
	public int newUser(User user);
	public User accessUser(int userId);
	public void editUser(User user);
	public void removeUser(int userId);
	public boolean authenticUser(String userEmail, String UserPassword);
}

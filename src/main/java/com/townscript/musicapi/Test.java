package com.townscript.musicapi;

import com.townscript.musicapi.dao.users.UserDao;
import com.townscript.musicapi.dao.users.UserDaoImpl;
import com.townscript.musicapi.model.Users;

public class Test {
	public static void main(String[] args){
		Users user = new Users();
		user.setName("shyam Singh");
		user.setEmail("shyamsingh@ts.com");
		user.setPassword("qwerty1234");
		UserDao dao = new UserDaoImpl();
		dao.addUser(user);
	}
}

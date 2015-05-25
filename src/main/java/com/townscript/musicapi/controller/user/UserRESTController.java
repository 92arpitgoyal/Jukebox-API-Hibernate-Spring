package com.townscript.musicapi.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.townscript.musicapi.model.User;
import com.townscript.musicapi.service.user.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserRESTController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/get", method=RequestMethod.GET)
	public User accessUser(@RequestParam(value="id",required=true) int id){
		return userService.accessUser(id);
	}

}

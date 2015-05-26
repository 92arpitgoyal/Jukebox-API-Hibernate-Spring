package com.townscript.musicapi.controller.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.townscript.musicapi.model.User;
import com.townscript.musicapi.service.user.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserRESTController {

	public UserRESTController() {
		super();
        if (userService == null) {
                ApplicationContext context = new ClassPathXmlApplicationContext(
                                "com/townscript/musicapi/main-beans.xml");
                userService = (UserService) context
                                .getBean("UserServiceImpl");
        }
	}
	
	private UserService userService;
	
	@RequestMapping(value = "/get/{id}", method=RequestMethod.GET)
	public User accessUser(@PathVariable("id") int id){
	//public User accessUser(@RequestParam(value="id",required=true) int id){
		return userService.accessUser(id);
	}

}

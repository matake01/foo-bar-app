package com.miskowskij.app.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miskowskij.app.service.user.UserDTO;
import com.miskowskij.app.service.user.UserService;
  
@RestController
public class UserController extends BaseController {
 
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;

    public UserController() { }
    
    @Autowired
    public void setUserService(UserService userService) {
    	this.userService = userService;
    }
    
    @RequestMapping(value = "/user/{username}", produces = "application/json")
    public ResponseEntity<UserDTO> addUser(@PathVariable String username) {
		logger.debug("[addUser] " + username);
		
		UserDTO user = new UserDTO();
		user.setUsername(username);
		
		user = userService.addUser(user);
		
		return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
    }
 
}
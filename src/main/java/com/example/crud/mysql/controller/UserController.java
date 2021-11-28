package com.example.crud.mysql.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.mysql.model.entity.User;
import com.example.crud.mysql.service.inf.UserServiceInf;
import com.example.crud.mysql.utils.ResponseBean;

@RestController
@RequestMapping("user")
public class UserController {

private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	public UserServiceInf userServiceInf;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseBean> saveUser(@RequestBody User user) {
		return userServiceInf.save(user);
	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseBean> getAll() {
		return userServiceInf.getAll();
	}
	
	@PostMapping("/login")
	public ResponseEntity<ResponseBean> findByNameAndPassword(@RequestBody User user) {
		logger.info("Name:"+user.getName() +"password :"+user.getPassword());
		return userServiceInf.findByNameAndPassword(user.getName(), user.getPassword());
	}
}

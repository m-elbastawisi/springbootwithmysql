package com.example.crud.mysql.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.crud.mysql.exception.MscObjectSaveException;
import com.example.crud.mysql.model.entity.User;
import com.example.crud.mysql.reposiroty.inf.UserReposatoryInf;
import com.example.crud.mysql.service.inf.UserServiceInf;
import com.example.crud.mysql.utils.ResponseBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class UserServiceImpl implements UserServiceInf {

	public static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserReposatoryInf userReposatoryInf;
	
	
	
	public ResponseEntity<ResponseBean>  save(User user) {
		try {
			logger.info("Entering inside of save method info");
			logger.debug("Entering inside of save method debug");
			userReposatoryInf.save(user);
			logger.info("existed inside of save method info");
			return new ResponseEntity<>(new ResponseBean(200, "SUCCESS", user),HttpStatus.OK);
				}catch(Exception ex) {
		throw new MscObjectSaveException("Error occured while save user",user);
	}
		// TODO Auto-generated method stub
		
	}
	
	

	public ResponseEntity<ResponseBean> getAll(){
		
		try {
			List<User> list = (List<User>)userReposatoryInf.findAll();
			return new  ResponseEntity<>(new ResponseBean(200, "success", list),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseBean(500,"fail",e.getMessage(),null),HttpStatus.EXPECTATION_FAILED);
			// TODO: handle exception
		}
	}
	@Override
	public ResponseEntity<ResponseBean> findByNameAndPassword(String name , String address){
		try {
			List<User> list = (List<User>)userReposatoryInf.findByNameAndAddress(name, address);
			return new ResponseEntity<>(new ResponseBean(000, "success", list),HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(new ResponseBean(500, "failed", e.getMessage()),HttpStatus.EXPECTATION_FAILED);
		}
	}



	


	
	
}

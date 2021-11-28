package com.example.crud.mysql.service.inf;

import org.springframework.http.ResponseEntity;

import com.example.crud.mysql.model.entity.User;
import com.example.crud.mysql.utils.ResponseBean;

public interface UserServiceInf {
	ResponseEntity<ResponseBean> save(User employee);
	ResponseEntity<ResponseBean> getAll();
	ResponseEntity<ResponseBean> findByNameAndPassword(String name,String password);
}

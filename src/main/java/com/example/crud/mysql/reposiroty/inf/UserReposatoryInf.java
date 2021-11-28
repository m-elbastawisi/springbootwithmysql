package com.example.crud.mysql.reposiroty.inf;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.mysql.model.entity.Employee;
import com.example.crud.mysql.model.entity.User;

@Repository
public interface UserReposatoryInf extends CrudRepository<User, Long> {
public 	List<User> findByNameAndAddress(String name , String address);
	

}

package com.example.crud.mysql.reposiroty.inf;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.mysql.model.entity.Employee;

@Repository
public interface EmployeeReposatoryInf extends CrudRepository<Employee, Long>{
	
	List<Employee> findByNameAndAddress(String name , String address);

}

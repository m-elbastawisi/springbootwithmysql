package com.example.crud.mysql.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.mysql.model.entity.Employee;
import com.example.crud.mysql.service.inf.EmployeeServiceInf;
import com.example.crud.mysql.utils.ResponseBean;

@RestController
@RequestMapping("employee")
public class EmployeeController {

	@Autowired
	public EmployeeServiceInf employeeServiceInf;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseBean> saveEmployee(@Valid @RequestBody Employee employee) {
		return employeeServiceInf.save(employee);
	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseBean> getAll() {
		return employeeServiceInf.getAll();
	}
	
	@DeleteMapping("/{id}")
	public Object deleteById(@PathVariable (value="id")long id) {
		return employeeServiceInf.deleteById(id);
	}
	
	@GetMapping("/findByNameAndAddress/{name}/{address}")
	public List<Employee> findByNameAndAddress(@PathVariable(value="name")String name,@PathVariable(value="address") String address) {
		return employeeServiceInf.findByNameAndAddress(name, address);
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<ResponseBean> findById(@PathVariable (value="id")long id) {
		return employeeServiceInf.findById(id);
	}

}

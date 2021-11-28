package com.example.crud.mysql.service;

import com.example.crud.mysql.exception.MscException;
import com.example.crud.mysql.exception.MscObjectSaveException;
import com.example.crud.mysql.model.entity.Employee;
import com.example.crud.mysql.reposiroty.inf.EmployeeReposatoryInf;
import com.example.crud.mysql.service.inf.EmployeeServiceInf;
import com.example.crud.mysql.utils.ConstaintInfo;
import com.example.crud.mysql.utils.DateAndTimeUtils;
import com.example.crud.mysql.utils.ResponseBean;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeServiceInf {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	
	@Value("${env}")
	private String env;
	
	@Autowired
	private EmployeeReposatoryInf employeeReposatoryInf;
	

	@Override
	public ResponseEntity<ResponseBean> save(Employee employee){
		try {
			logger.info("entering inside save method info");
			logger.debug("entering inside save method debug");
			employee.setDepartment(ConstaintInfo.DEPARTMENT);
			employee.setEntryDate(DateAndTimeUtils.getCurrentDateAndTime());
			employee.setEnv(env);
			employeeReposatoryInf.save(employee);
			return new ResponseEntity<>(new ResponseBean(200, "SUCCESS", employee),HttpStatus.OK);

			
		}catch(Exception e) {
			throw new MscObjectSaveException("error occured during save employee", employee);
		}
	}
	
	@Override
	public ResponseEntity<ResponseBean> getAll(){
		try {
			List<Employee> list = (List<Employee>)employeeReposatoryInf.findAll();
			return new ResponseEntity<>(new ResponseBean(200, "SUCCESS", list),HttpStatus.OK);
			
		}catch (Exception e) {
			return new ResponseEntity<>(new ResponseBean(500,"fail",e.getMessage(),null),HttpStatus.EXPECTATION_FAILED);

			// TODO: handle exception
		}
	}
	
	
	@Override
	public Object deleteById(long id) {
		
			Optional<Employee> employee = employeeReposatoryInf.findById(id);
			Object obj = new Object();
			if(employee.isPresent()) {
				employeeReposatoryInf.delete(employee.get());
				obj = employee.get();
			}else {
				obj = "please enter a correct employee ID";
			}
			return obj;
	}
	
	@Override
	public List<Employee> findByNameAndAddress(String name , String address){
		return employeeReposatoryInf.findByNameAndAddress(name, address);
	}
	
	@Override
	public ResponseEntity<ResponseBean> findById(long id){
		
		Optional<Employee> employee = employeeReposatoryInf.findById(id);
		Object obj = new Object();
		if(employee.isPresent()) {
			obj = employee.get();
			return new ResponseEntity<>(new ResponseBean(200, "success", obj),HttpStatus.OK);
		}else
		{
			throw new MscException("no record foudn by this id :", id);
		}
	}


	
	
}

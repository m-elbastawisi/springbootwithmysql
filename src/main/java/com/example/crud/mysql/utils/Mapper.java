package com.example.crud.mysql.utils;

import java.text.SimpleDateFormat;

import com.example.crud.mysql.model.entity.Employee;
import com.example.crud.mysql.model.form.EmployeeVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;

public class Mapper {

	static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	static Gson gson = new Gson();
	
	public static Employee converFormToEntity(EmployeeVO employeeVo) {
		System.out.println("EmployeeVO >:"+employeeVo.getName());
		String json = gson.toJson(employeeVo);
		Employee employee = null;
		try {
			
			employee = gson.fromJson(json, Employee.class);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return employee;
	}
	
	public static EmployeeVO converEntityToForm(Object employee) {
		String json = gson.toJson(employee);
		EmployeeVO employeevo = null;
		try {
			employeevo = gson.fromJson(json, EmployeeVO.class);
			
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return employeevo;
	}
}

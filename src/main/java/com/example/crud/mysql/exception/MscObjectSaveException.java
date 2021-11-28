package com.example.crud.mysql.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MscObjectSaveException  extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private Object obj;
	
	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public MscObjectSaveException(String message , Object obj) {
		super(message);
		this.obj = obj;
		// TODO Auto-generated constructor stub
	}
	
	

}

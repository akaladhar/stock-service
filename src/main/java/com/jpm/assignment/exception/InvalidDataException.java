package com.jpm.assignment.exception;

public class InvalidDataException extends Exception{

	
	private static final long serialVersionUID = 1307208523521952959L;
	private String message;
	
	public InvalidDataException(String message){
		this.message = message;
	}
}

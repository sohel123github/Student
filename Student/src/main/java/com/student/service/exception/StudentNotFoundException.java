package com.student.service.exception;

public class StudentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StudentNotFoundException() {
		super("Student Is Not Found !");
	}

	public StudentNotFoundException(String msg) {
		super(msg);
	}

}

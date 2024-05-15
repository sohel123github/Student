package com.student.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.student.entity.Student;
import com.student.service.StudentServiceImpl;

@RestController
public class StudentController {

	@Autowired
	private StudentServiceImpl studentServiceImpl;

	@GetMapping("/student")
	public ResponseEntity<List<Student>> getAllStudent() {
		List<Student> listOfStudent = studentServiceImpl.getAllStudent();
		return new ResponseEntity<>(listOfStudent, HttpStatus.OK);
	}

	@GetMapping("/student/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable int id) {
		Student stu = studentServiceImpl.getStudent(id);
		return new ResponseEntity<>(stu, HttpStatus.OK);
	}

	@PutMapping("/student")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		Student stuDemo = studentServiceImpl.upsert(student);
		return new ResponseEntity<>(stuDemo, HttpStatus.OK);
	}

	@PostMapping("/student")
	public ResponseEntity<Student> addStudent(@RequestBody Student student) {
		Student studInfo = studentServiceImpl.upsert(student);
		return new ResponseEntity<>(studInfo, HttpStatus.OK);
	}

	@DeleteMapping("/student/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable int id) {
		String status = studentServiceImpl.deleteStudent(id);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

}

package com.student.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.student.dao.StudentRepository;
import com.student.entity.Student;
import com.student.service.exception.StudentNotFoundException;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> getAllStudent() {
		List<Student> listOfStudent = studentRepository.findAll();
		return listOfStudent;
	}

	@Override
	public Student getStudent(int id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException());
		return student;
	}

	@Override
	public Student upsert(Student student) {
		Student stu = studentRepository.save(student);
		return stu;
	}

	@Override
	public String deleteStudent(int id) {
		studentRepository.deleteById(id);
		return "Deleted successfully..";
	}

}

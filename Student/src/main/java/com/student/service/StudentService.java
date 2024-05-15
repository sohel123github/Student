package com.student.service;

import java.util.List;
import com.student.entity.Student;

public interface StudentService {

	public List<Student> getAllStudent();

	public Student getStudent(int id);

	public Student upsert(Student student);

	public String deleteStudent(int id);

}

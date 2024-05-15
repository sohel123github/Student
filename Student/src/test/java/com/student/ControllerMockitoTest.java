package com.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import com.student.controller.StudentController;
import com.student.entity.Student;
import com.student.service.StudentServiceImpl;

@SpringBootTest(classes = { ControllerMockitoTest.class })
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControllerMockitoTest {

	@Mock
	StudentServiceImpl studentServiceImpl;

	@InjectMocks
	StudentController studentController;

	@Test
	@Order(1)
	public void testGetAllStudent() {
		when(studentServiceImpl.getAllStudent()).thenReturn(Stream.of(new Student(1, "Abhishek", "France"), new Student(2, "Rohit", "Banglore")).collect(Collectors.toList()));
		ResponseEntity<List<Student>> responseStudent = studentController.getAllStudent();
		List<Student> students = responseStudent.getBody();
		assertEquals(2, students.size());
	}

	@Test
	@Order(2)
	public void testGetStudent() {
		int id = 101;
		Student stud = new Student(101, "Jack", "Mumbai");
		when(studentServiceImpl.getStudent(id)).thenReturn(stud);
		ResponseEntity<Student> resStud = studentController.getStudent(id);
		Student studDemo = resStud.getBody();
		assertEquals(id, studDemo.getId());
	}

	@Test
	@Order(3)
	public void testUpdateStudent() {
		Student student = new Student(501, "Sham", "Lucknow");
		when(studentServiceImpl.upsert(student)).thenReturn(student);
		ResponseEntity<Student> studentData = studentController.updateStudent(student);
		Student studentInfo = studentData.getBody();
		assertEquals(student, studentInfo);
	}

	@Test
	@Order(4)
	public void testAddStudent() {
		Student student = new Student(1001, "Roger", "Italy");
		when(studentServiceImpl.upsert(student)).thenReturn(student);
		ResponseEntity<Student> resStudent = studentController.addStudent(student);
		Student studentData = resStudent.getBody();
		assertEquals(student, studentData);
	}

	@Test
	@Order(5)
	public void testDeleteStudent() {
		int id = 201;
		String succMsg = "Successfully deleted..";
		when(studentServiceImpl.deleteStudent(id)).thenReturn(succMsg);
		ResponseEntity<String> studentDel = studentController.deleteStudent(id);
		String studData = studentDel.getBody();
		assertEquals(succMsg, studData);
	}

}

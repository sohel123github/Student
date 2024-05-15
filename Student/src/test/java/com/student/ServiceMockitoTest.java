package com.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.student.dao.StudentRepository;
import com.student.entity.Student;
import com.student.service.StudentServiceImpl;

@SpringBootTest(classes = { ServiceMockitoTest.class })
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceMockitoTest {

	@Mock
	StudentRepository studentRepository;

	@InjectMocks
	StudentServiceImpl studentServiceImpl;

	@Test
	@Order(1)
	public void testGetAllStudent() {
		when(studentRepository.findAll()).thenReturn(Stream.of(new Student(1, "Raj", "UK"),new Student(2, "Karan", "USA")).collect(Collectors.toList()));
		assertEquals(2, studentServiceImpl.getAllStudent().size());
	}

	@Test
	@Order(2)
	public void testGetStudent() {
		int id = 1;
		Student student = new Student(1, "Rahul", "Poland");
		when(studentRepository.findById(id)).thenReturn(Optional.of(student));
		assertEquals(id, studentServiceImpl.getStudent(id).getId());
	}

	@Test
	@Order(3)
	public void testUpsert() {
		Student student = new Student(101, "Ram", "Poland");
		when(studentRepository.save(student)).thenReturn(student);
		assertEquals(student, studentServiceImpl.upsert(student));
	}

	@Test
	@Order(4)
	public void testDeleteStudent() {
		int id = 201;
		doNothing().when(studentRepository).deleteById(id);
		studentServiceImpl.deleteStudent(id);
		verify(studentRepository, times(1)).deleteById(id);
	}

}

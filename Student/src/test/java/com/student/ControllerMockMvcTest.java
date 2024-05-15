package com.student;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.controller.StudentController;
import com.student.entity.Student;
import com.student.service.StudentServiceImpl;

@ComponentScan(basePackages = "com.student")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = { ControllerMockitoTest.class })
@TestMethodOrder(OrderAnnotation.class)
public class ControllerMockMvcTest {

	@Autowired
	MockMvc mockMvc;

	@Mock
	StudentServiceImpl studentServiceImpl;

	@InjectMocks
	StudentController studentController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
	}

	List<Student> listOfStudents;

	Student student;

	@Test
	@Order(1)
	public void testGetAllStudent() throws Exception {
		listOfStudents = new ArrayList<>();
		listOfStudents.add(new Student(1, "Rock", "Germany"));
		listOfStudents.add(new Student(2, "John", "France"));

		when(studentServiceImpl.getAllStudent()).thenReturn(listOfStudents);
		this.mockMvc.perform(get("/student")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	@Order(2)
	public void testGetStudent() throws Exception {
		int id = 101;
		student = new Student(101, "Aman", "Pune");

		when(studentServiceImpl.getStudent(id)).thenReturn(student);

		this.mockMvc.perform(get("/student/{id}", id)).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(101)).andExpect(jsonPath("$.name").value("Aman")).andDo(print());
	}

	@Test
	@Order(3)
	public void testUpdateStudent() throws Exception {
		student = new Student(1001, "Raj", "Europe");
		when(studentServiceImpl.upsert(student)).thenReturn(student);

		ObjectMapper mapper = new ObjectMapper(); // We here use (ObjectMapper) to convert [java object] into [Json format]..
		String jsonBody = mapper.writeValueAsString(student);

		this.mockMvc.perform(put("/student").content(jsonBody).contentType(MediaType.APPLICATION_JSON)) // perform method completed at this Line..
				.andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	@Order(4)
	public void testAddStudent() throws Exception {
		student = new Student(51, "Razer", "Morroco");
		when(studentServiceImpl.upsert(student)).thenReturn(student);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(student);
		
		this.mockMvc.perform(post("/student").content(jsonData).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	@Order(5)
	public void testDeleteStudent() throws Exception {
		int id = 2001;
		student = new Student(2001, "Henry", "Japan");
		when(studentServiceImpl.getStudent(id)).thenReturn(student);
		
		this.mockMvc.perform(delete("/student/{id}", id)).andExpect(status().isOk()).andDo(print());
	}
	
	
	
	
	
	
	
	
	
	
	
	

}

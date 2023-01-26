package com.student.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.demo.Exception.ResourceNotFoundException;
import com.student.demo.Repository.StudentRepository;
import com.student.demo.model.Student;

@CrossOrigin
@RestController
@RequestMapping("api/v1")
public class StudentController {
	
	@Autowired
	 private StudentRepository studentRepo;
	
	//get all students Rest api
	@GetMapping("/students")
	public List<Student> getAllStudents()
	{
		 
		return studentRepo.findAll();
		
	}
	
	// create students Rest api
	@PostMapping("/students")
	public Student createStudent(@RequestBody Student student)
	{
		return studentRepo.save(student);
		
	}
	
	
	// update student Rest api
	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails)
	{
		Student student = studentRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not exists with id : "+id ));
		
		student.setFirstName(studentDetails.getFirstName());
				
		student.setLastName(studentDetails.getLastName());
		
		student.setEmail(studentDetails.getEmail());
		
		Student updatedStudent = studentRepo.save(student);
		return ResponseEntity.ok(updatedStudent);		
		
	}
	
	
	// Delete Student Rest api
	@DeleteMapping("/students/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee( @PathVariable Long id)
	{
		Student student = studentRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student not exists with id : "+id ));
		
		studentRepo.delete(student);
		Map<String,Boolean> response = new HashMap <>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}

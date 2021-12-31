package com.gl.Student.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gl.Student.Entity.Student;

@Service
public interface StudentService {

	public void save(Student student);
	
	public List<Student> findAll();
	
	public Student findById(int id);
	
	public void deleteById(int id);
	
	public void print(List<Student> student);
}

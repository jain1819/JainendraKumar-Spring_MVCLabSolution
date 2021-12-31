package com.gl.Student.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.Student.Entity.Student;
import com.gl.Student.Service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	//add mapping for "/list"
	
	@RequestMapping("/list")
	public String listStudents (Model theModel) {
		
		//get Students from DB(from the service)
		List<Student> theStudents = studentService.findAll();
		//add to the spring model
		theModel.addAttribute("Students", theStudents);
		//send over to our form
		return "list-Students";
		
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		//create model attribute to bind form data
		Student theStudent = new Student();
		//set student
		theModel.addAttribute("Student", theStudent);
		return "Student-form";
	}
	
	@RequestMapping("/showFormForUpadte")
	public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel)
	{
		//get the student from service
		Student student = studentService.findById(theId);
		//set student as a model attribute to pre-populate the form
		theModel.addAttribute("students", student);
		System.out.println("Show for update "+student);
		//send over to our form
		return "student-form";
	}
		
	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("name") String name ,
			@RequestParam("department") String department, @RequestParam ("country") String country) {
		
		System.out.println("Trying to save with Id :" +id);
		Student theStudent;
		if(id!=0) {
			//update operation
			theStudent = studentService.findById(id);
			
			//put updated values to the 
			theStudent.setName(name);
			theStudent.setDepartment(department);
			theStudent.setCountry(country);
					
		}else {
			//create operation
			theStudent = new Student(name,department,country);
		}
		//save the student
		studentService.save(theStudent);
		//use a redirect to prevent duplicate submission
		return "redirect:/students/list";
				
		
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam ("studentId")int theId) {
		
		//delete the student
		studentService.deleteById(theId);
		
		//redirect to /students/list
		return "redirect:/students/list";
	}

}

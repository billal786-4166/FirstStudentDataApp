package in.ashokit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.Entity.StudentEntity;
import in.ashokit.binding.Student;

@Controller
public class StudentController {
	@Autowired
	private StudentRepository repo;

	//method to load the student form
	@GetMapping("/")
	public String loadForm(Model model) {
		
		loadFormData(model);
		
		
		return "index";
	}

	private void loadFormData(Model model) {
		List<String> coursesList = new ArrayList<String>();
		coursesList.add("Java");
		coursesList.add("Devops");
		coursesList.add("aws");
		coursesList.add("Python");
		
		
		List<String> timingsList =new ArrayList<String>();
		timingsList.add("Morning");
		timingsList.add("Afternoon");
		timingsList.add("evening");
		
		Student student = new Student();
		
		model.addAttribute("courses",coursesList);
		model.addAttribute("timings",timingsList);
		model.addAttribute("student",student);
	}
	
	// student to save student form data
	@PostMapping("/save")
	public String handleSubmit(Student s,Model model) {
		System.out.println(s);
		
		//logic to save
		StudentEntity entity = new StudentEntity();
		//copy the data from binding obj to entity obj
		
		BeanUtils.copyProperties(s, entity);
		entity.setTimings(Arrays.toString(s.getTimings()));
		
		
		repo.save(entity);
		model.addAttribute("msg", "student saved");
		
		
		loadFormData(model);
		
		return "index";
		
		
	}
	
	//method to display saved students data
	@GetMapping("/viewStudents")
	public String getStudentsData(Model model) {
		//login to fetch and send students data
		List<StudentEntity> studentList = repo.findAll();
		model.addAttribute("students",studentList);
		return "data";
	}
}

package com.laowang.datasource.controller;

import com.laowang.datasource.beans.Student;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: wangc
 * @Date: 2021/6/4 18:56
 * @Description:
 */
@RestController("/stu")
public class StudentsManagementController {

	@GetMapping("/getStuById")
	public Student getStudentById(@PathVariable("id")String id) {
		// id verification
		// search logic
		Student student = studentService.getStudentById(id);
		return student;
	}

	@PutMapping("/updateStuStat")
	public String updateStudentState(@RequestBody Student student, @PathVariable String id){
		return "success";
	}

	@PostMapping("/addStu")
	public String addStudent(@RequestBody Student student){
		return "success";
	}

	@GetMapping("/getStuByGender")
	public List<Student> getStudentByGender(@PathVariable("gender")String gender) {
		List<Student> list = studentService.getStudentByGender(gender);
		return list;
	}
}

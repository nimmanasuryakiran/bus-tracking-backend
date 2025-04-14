
package com.college.bus.controller;

import com.college.bus.model.Student;
import com.college.bus.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin("*")

public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public Student registerStudent(@RequestBody Student student) {
        return studentService.registerStudent(student);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STUDENT')")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }
    
    

    @GetMapping("/registration/{regNumber}")
    public Student getStudentByRegistrationNumber(@PathVariable String regNumber) {
        return studentService.getStudentByRegistrationNumber(regNumber);
    }
    
    @GetMapping("/sorted")
    public List<Student> getAllStudentsSorted(
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        return studentService.getAllStudentsSorted(sortBy, order);
    }
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        return studentService.updateStudent(id, updatedStudent);
    }

    


    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
    @GetMapping("/bus/{busId}")
    public List<Student> getStudentsByBusId(@PathVariable Long busId) {
        return studentService.getStudentsByBusId(busId);
    }


    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}




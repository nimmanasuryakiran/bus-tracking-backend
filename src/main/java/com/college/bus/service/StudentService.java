package com.college.bus.service;

import com.college.bus.model.Student;
import java.util.List;

public interface StudentService {
    Student registerStudent(Student student);
    Student getStudentById(Long id);
    Student getStudentByRegistrationNumber(String registrationNumber);
    List<Student> getAllStudents();
    List<Student> getStudentsByBusId(Long busId);
    Student updateStudent(Long id, Student updatedStudent);

    List<Student> getAllStudentsSorted(String sortBy, String order);

    
    void deleteStudent(Long id);
}

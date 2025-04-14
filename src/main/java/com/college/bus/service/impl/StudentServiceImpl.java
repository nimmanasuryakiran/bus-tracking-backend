
package com.college.bus.service.impl;
import com.college.bus.model.Bus;
import com.college.bus.repository.BusRepository;

import com.college.bus.model.Student;
import com.college.bus.repository.StudentRepository;
import com.college.bus.service.StudentService;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
	

	@Autowired
	private BusRepository busRepository;


    @Autowired
    private StudentRepository studentRepository;
    
    
    @Override
    public Student registerStudent(Student student) {
        Optional<Student> existingStudent = studentRepository.findByEmail(student.getEmail());
        if (existingStudent.isPresent()) {
            throw new RuntimeException("Email is already registered: " + student.getEmail());
        }

        // Fetch the bus details from the database if a bus is assigned
        if (student.getBus() != null && student.getBus().getId() != null) {
            Optional<Bus> bus = busRepository.findById(student.getBus().getId());
            bus.ifPresent(student::setBus); // Set the fetched bus
        }

        return studentRepository.save(student);
    }
    




   

    
    
    
    @Override
    public Student getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student Not Found"));

        if (student.getBus() != null && student.getBus().getRoute() != null) {
            Hibernate.initialize(student.getBus().getRoute()); // Force loading route
        }

        return student;
    }
    
    
    

    

    
    @Override
    public List<Student> getStudentsByBusId(Long busId) {
        return studentRepository.findByBusId(busId);
    }
    @Override
    public List<Student> getAllStudentsSorted(String sortBy, String order) {
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        return studentRepository.findAll(Sort.by(direction, sortBy));
    }
    
    @Override
    public Student updateStudent(Long id, Student updatedStudent) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setPassword(updatedStudent.getPassword());
        existingStudent.setRegistrationNumber(updatedStudent.getRegistrationNumber());
        existingStudent.setBus(updatedStudent.getBus()); // Update bus if needed

        return studentRepository.save(existingStudent);
    }
    
    
    




    @Override
    public Student getStudentByRegistrationNumber(String registrationNumber) {
        return studentRepository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new RuntimeException("Student not found with registration number: " + registrationNumber));
    }


    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}

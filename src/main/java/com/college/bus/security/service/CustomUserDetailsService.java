package com.college.bus.security.service;

import com.college.bus.model.Student;
import com.college.bus.model.Driver;
import com.college.bus.model.Admin;
import com.college.bus.repository.StudentRepository;
import com.college.bus.repository.DriverRepository;
import com.college.bus.repository.AdminRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final DriverRepository driverRepository;
    private final AdminRepository adminRepository;
    

    public CustomUserDetailsService(StudentRepository studentRepository, DriverRepository driverRepository, AdminRepository adminRepository) {
        this.studentRepository = studentRepository;
        this.driverRepository = driverRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to find user in each role category
        Optional<Student> student = studentRepository.findByRegistrationNumber(username);
        if (student.isPresent()) {
        	System.out.println("Logging in as STUDENT: " + student.get().getRegistrationNumber());
            return User.withUsername(student.get().getRegistrationNumber())
                    .password(student.get().getPassword())
                    .roles("ROLE_STUDENT")
                    .build();
        }

        Optional<Driver> driver = driverRepository.findByPhone(username);
        if (driver.isPresent()) {
            return User.withUsername(driver.get().getPhone())
                    .password(driver.get().getPassword())
                    .roles("ROLE_DRIVER")
                    .build();
        }

        Optional<Admin> admin = adminRepository.findByEmail(username);
        if (admin.isPresent()) {
            return User.withUsername(admin.get().getEmail())
                    .password(admin.get().getPassword())
                    .roles("ROLE_ADMIN")
                    .build();
        }

        throw new UsernameNotFoundException("User not found");
    }
}





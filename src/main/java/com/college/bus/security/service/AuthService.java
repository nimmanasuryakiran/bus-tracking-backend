package com.college.bus.security.service;

import com.college.bus.model.Student;
import com.college.bus.model.Driver;
import com.college.bus.model.Admin;
import com.college.bus.repository.StudentRepository;
import com.college.bus.repository.DriverRepository;
import com.college.bus.repository.AdminRepository;
import com.college.bus.security.dto.AuthRequest;
import com.college.bus.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AuthService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    
    

	/*
	 * @Autowired private PasswordEncoder passwordEncoder;
	 */
    
    

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String encodedPassword = passwordEncoder.encode("your_plain_password");


    public String authenticateUser(AuthRequest authRequest) {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();
        String role = authRequest.getRole().toUpperCase();

        boolean isValidUser = false;

        switch (role) {
            case "STUDENT":
                Optional<Student> student = studentRepository.findByRegistrationNumber(username);
                isValidUser = student.isPresent() && passwordEncoder.matches(password, student.get().getPassword());
                break;

			/*
			 * case "DRIVER": Optional<Driver> driver =
			 * driverRepository.findByPhone(username); isValidUser = driver.isPresent() &&
			 * passwordEncoder.matches(password, driver.get().getPassword()); break;
			 */

            case "DRIVER":
                Optional<Driver> driver = driverRepository.findByPhone(username);

                // Debugging prints
                System.out.println("Username (Phone): " + username);
                if (driver.isPresent()) {
                    System.out.println("Stored Hash: " + driver.get().getPassword());
                    System.out.println("Raw Password: " + password);
                    System.out.println("Password Match: " + passwordEncoder.matches(password, driver.get().getPassword()));
                } else {
                    System.out.println("Driver not found!");
                }

                isValidUser = driver.isPresent() && passwordEncoder.matches(password, driver.get().getPassword());
                break;

            case "ADMIN":
                Optional<Admin> admin = adminRepository.findByEmail(username); // Updated here
                isValidUser = admin.isPresent() && passwordEncoder.matches(password, admin.get().getPassword());
                break;

            default:
                throw new IllegalArgumentException("Invalid role specified");
        }

        if (!isValidUser) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(username, role);
    }
}

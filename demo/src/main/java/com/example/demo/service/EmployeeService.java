package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public String addEmployee(Employee employee) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String encryptedPassword = bcrypt.encode(employee.getPassword());
        employee.setPassword(encryptedPassword);
        employeeRepository.save(employee);
        return employee.getName()+" added Successfully";
    }

    public String loginEmployee(String email, String password){
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        Employee employee = employeeRepository.findById(email).get();
            if(bcrypt.matches(password,employee.getPassword())){
                return employee.getName()+" Login Successfully";
            }
            else return "Incorrect Password";
    }
}


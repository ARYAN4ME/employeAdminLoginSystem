package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


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

    public String loginEmployee(String email, String password) throws Exception{
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        Optional<Employee> e = employeeRepository.findById(email);
        if(e.isPresent()){
            Employee employee = e.get();
            if(bcrypt.matches(password,employee.getPassword())){
                return employee.getName()+" Login Successfully";
            }
            else return "Incorrect Password";
        }

        throw new Exception(email +" is not exist");
    }
}


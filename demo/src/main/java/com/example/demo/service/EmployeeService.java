package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public String addEmployee(Employee employee) {
//        List<Employee> employees = employeeRepository.findAll();
//        for(Employee e : employees){
//            if(e.getEmail().equals(employee.getEmail())){
//                return "Employee already exist";
//            }
//        }
        employeeRepository.save(employee);
        return "Employee added Successfully";
    }

    public Employee loginEmployee(String email, String password) throws Exception{
        List<Employee> employees = employeeRepository.findAll();
        for(Employee e : employees){
            if(e.getEmail().equals(email)){
                if(e.getPassword().equals(password)){
                    return e;
                }

            }

        }

        return null;
    }
}


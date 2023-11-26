package com.example.demo.controller;


import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping("/add")
    public String addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }
    @GetMapping("/login/{email}/{password}")
    public Employee loginEmployee(@PathVariable("email") String email, @PathVariable("password") String password) throws Exception {
        return employeeService.loginEmployee(email,password);
    }
}


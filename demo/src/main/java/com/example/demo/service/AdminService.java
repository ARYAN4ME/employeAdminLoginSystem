package com.example.demo.service;


import com.example.demo.entity.Admin;
import com.example.demo.entity.Employee;
import com.example.demo.enums.Degree;
import com.example.demo.enums.Status;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    public String addAdmin(Admin admin) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String encryptedPassword = bcrypt.encode(admin.getPassword());
        admin.setPassword(encryptedPassword);
        adminRepository.save(admin);
        return admin.getName()+" added successfully";
    }

    public String loginAdmin(String email, String password) throws Exception {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        Admin admin = adminRepository.findById(email).get();
        if(bcrypt.matches(password, admin.getPassword())){
            return admin.getName()+" login successfully";
        }
        else return "Incorrect Password";
    }

    public void updateEmployeeStatusByEmail(String email) {
        Employee employee = employeeRepository.findById(email).get();
        if(employee.getStatus().equals("PENDING")){
            if((employee.getDegree().equals("BBA") || employee.getDegree().equals("BCA") || employee.getDegree().equals("BTECH")) && employee.getIsExperienceLetter().equals("yes")){
                employee.setStatus(Status.valueOf("SUCCESS"));
            }
            else{
                employee.setStatus(Status.valueOf("NOTELIGIBLE"));
            }
        }
    }
}

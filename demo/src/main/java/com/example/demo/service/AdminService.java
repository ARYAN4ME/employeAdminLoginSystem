package com.example.demo.service;


import com.example.demo.entity.Admin;
import com.example.demo.entity.Employee;
import com.example.demo.enums.Degree;
import com.example.demo.enums.Status;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    public String addAdmin(Admin admin) {
//        List<Admin> adminList = adminRepository.findAll();
//        for(Admin e : adminList){
//            if(e.getEmail().equals(admin.getEmail())){
//                return "Admin already exist";
//            }
//
//        }
        adminRepository.save(admin);
        return "Admin added successfully";
    }

    public Admin loginAdmin(String email, String password) throws Exception {
        List<Admin> adminList = adminRepository.findAll();
        for(Admin e : adminList){
            if(e.getEmail().equals(email)){
                if(e.getPassword().equals(password)){
                    return e;
                }
            }
        }
        return null;
    }

    public void updateEmployeeStatusByEmail(String email) {
        Employee employee = employeeRepository.findById(email).get();
        if(employee.getStatus().equals(Status.PENDING)){
            if((employee.getDegree().equals(Degree.BBA) || employee.getDegree().equals(Degree.BCA) || employee.getDegree().equals(Degree.BTECH)) && employee.getIsExperienceLetter().equals("yes")){
                employee.setStatus(Status.SUCCESS);
            }
            else{
                employee.setStatus(Status.NOTELIGIBLE);
            }
        }
    }
}

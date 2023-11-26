package com.example.demo.entity;

import com.example.demo.enums.Degree;
import com.example.demo.enums.Designation;
import com.example.demo.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {
    @Id
    private String email;
    private String name;
    private String password;
    private String phoneNo;
    @Enumerated(EnumType.STRING)
    private Designation designation;
    @Enumerated(EnumType.STRING)
    private Degree degree;
    private String isExperienceLetter;
    @Enumerated(EnumType.STRING)
    private Status status;
}

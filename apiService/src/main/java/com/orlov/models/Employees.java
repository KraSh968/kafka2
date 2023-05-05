package com.orlov.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private int age;
    private int selary;

    private String gender;


    public Employees() {

    }

    public Employees(String fullName, int age, int selary, String gender) {
        this.fullName = fullName;
        this.age = age;
        this.selary = selary;
        this.gender = gender;
    }

    public Employees(String fullName, int age, int selary) {
        this.fullName = fullName;
        this.age = age;
        this.selary = selary;
    }

    public Employees(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getSelary() {
        return selary;
    }

    public void setSelary(int selary) {
        this.selary = selary;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private final List<Department> departments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public List<Department> getDepartment() {
        return departments;
    }
}

package com.orlov.models;

import javax.persistence.*;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Department(String name) {
        this.name = name;
    }

    public Department() {

    }

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_department")
    private Employees employees;

    public long getId_empl() {
        return id_empl;
    }

    public void setId_empl(long id_stud) {
        this.id_empl = id_empl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employee) {
        this.employee = employee;
    }
}

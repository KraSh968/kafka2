package com.orlov.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class MessageListener {
    @Autowired
    com.orlov.repositories.EmployeesRepository employeesRepository;
    @Autowired
    com.orlov.repositories.DepartmentRepository departmentRepository;

    @KafkaListener(topics = "${kafka.topic.name}", containerFactory = "kafkaListenerContainerFactory")
    public void listenerEmployee(String data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Employees employee = mapper.readValue(data, Employees.class);
        employeesRepository.save(employee);
    }

    @KafkaListener(topics = "topic", containerFactory = "kafkaListenerContainerFactory")
    public void listenerDepartment(String data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Department departments = mapper.readValue(data, Department.class);
        Employees employee = employeesRepository.findEmployeeById(departments.getId_empl());
        departments.setEmployees(employee);
        employee.getDepartment().add(departments);
        employeesRepository.save(employee);
        departmentRepository.save(departments);
    }
}

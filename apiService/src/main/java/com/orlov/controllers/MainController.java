package com.orlov.controllers;

import com.orlov.api.ApiClient;
import com.orlov.service.MessageProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;
import java.util.List;


@Controller
public class MainController {
    @Autowired
    MessageProducer messageProducer;
    @Autowired
    ApiClient apiClient;

    @GetMapping("/")
    public String mainpage(Model model) {
        return "mainpage";
    }

    @GetMapping("/addEmployees")
    public String addEmployees(Model model) {
        return "addEmployees";
    }

    @GetMapping("/addDepartment")
    public String addDepartment(Model model) {
        return "addDepartment";
    }

    @GetMapping("/allEmployees")
    public String allEmployees(Model model) throws IOException {
        List<Employees> employees = employeesRepository.findAll();
        model.addAttribute("employees", employees);
        return "allEmployees";
    }

    @GetMapping("/allEmployeesGender")
    public String allEmployeesGender(Model model) {
        model.addAttribuEmployeeste("str", apiClient.getTemplate("http://service:8082/allEmployeesGender"));
        return "allEmployeesGender";
    }

    @GetMapping("/findEmployeesDepartment/{department}")
    public String getEmployeesDepartment(@PathVariable(value = "department") int department, Model model) {
        Iterable<Employees> employees = employeesRepository.findAllEmployeeByDepartment(department);
        model.addAttribute("employees", employees);
        return "allEmployeesDepartment";
    }

    @GetMapping("/selaryEmployees/{id}")
    public String selaryEmployees(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("str", apiClient.getTemplateWithPathVariable("http://service:8082/selaryEmployees/{id}", Math.toIntExact(id)));
        return "selaryEmployees";
    }

    @PostMapping("/addEmployees")
    public String addPostEmployees(@RequestParam String name, @RequestParam String surname, @RequestParam int age, @RequestParam int sellary, @RequestParam String gender, Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Employees employees = new Employees(name + " " + surname, age, sellary, gender);
        String jsonEmployee = mapper.writeValueAsString(employees);
        messageProducer.sendMessage(jsonEmployee);
        return "addEmployees";
    }

    @PostMapping("/addDepartment")
    public String addPostDepartment(@RequestParam String fullName, @RequestParam int idEmployee, Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Department department = new Department(fullName, idEmployee);
        String jsonDepartment = mapper.writeValueAsString(department);
        messageProducer.sendDepartment(jsonDepartment);
        return "addDepartment";
    }
}

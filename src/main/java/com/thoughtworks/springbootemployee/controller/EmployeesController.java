package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private final List<Employee> employees = new ArrayList<>();

    public EmployeesController() {
        employees.add(new Employee(1,"Yuto",23,"Male",15000));
        employees.add(new Employee(2,"JC",22,"Male",10000));
        employees.add(new Employee(3,"Khali",21,"Female",10000));
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @GetMapping(path = "/{employeeId}")
    public Employee getEmployeeById(@PathVariable Integer employeeId) {
        return employees.stream()
                .filter(employee -> employeeId.equals(employee.getId()))
                .findFirst()
                .orElse(null);
    }
    @GetMapping(params="gender")
    public List<Employee> getAllMaleEmployees(@RequestParam String gender) {
        return employees.stream()
                .filter(employee -> gender.equals(employee.getGender()))
                .collect(Collectors.toList());


    }
}

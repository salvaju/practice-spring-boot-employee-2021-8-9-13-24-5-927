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
        employees.add(new Employee(1, "Yuto", 23, "Male", 15000));
        employees.add(new Employee(2, "JC", 22, "Male", 10000));
        employees.add(new Employee(3, "Khali", 21, "Female", 10000));
        employees.add(new Employee(4, "Donald", 21, "Male", 104400));
        employees.add(new Employee(5, "Bob", 69, "Male", 10000));
        employees.add(new Employee(6, "Zagu", 25, "Female", 16900));
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

    @GetMapping(params = "gender")
    public List<Employee> getAllGenderEmployees(@RequestParam String gender) {
        return employees.stream()
                .filter(employee -> gender.equals(employee.getGender()))
                .collect(Collectors.toList());
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Employee> getEmployeesByPagination(@RequestParam Long pageIndex, @RequestParam Long pageSize) {
        return employees.stream()
                .skip((pageIndex - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

}

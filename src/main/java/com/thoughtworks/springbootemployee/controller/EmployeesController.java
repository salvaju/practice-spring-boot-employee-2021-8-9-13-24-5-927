package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private final List<Employee> employees = new ArrayList<>();
    private EmployeeService employeeService;

    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping(path = "/{employeeId}")
    public Employee getEmployeeById(@PathVariable Integer employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping(params = "gender")
    public List<Employee> getAllEmployeesByGender(@RequestParam String gender) {
        return employeeService.getAllEmployeesByGender(gender);
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Employee> getEmployeesByPagination(@RequestParam Long pageIndex, @RequestParam Long pageSize) {
        return employeeService.getEmployeesByPagination(pageIndex, pageSize);
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        employees.add(new Employee(employee.getId(), employee.getName(), employee.getAge(), employee.getGender(), employee.getSalary()));
    }

    @PutMapping(path = "/{employeeId}")
    public Employee updateEmployeeInformation(@PathVariable Integer employeeId, @RequestBody Employee employeeToBeUpdated) {
        return employees.stream()
                .filter(employee -> employeeId.equals(employee.getId()))
                .findFirst()
                .map(employee -> updateEmployeeInfo(employee, employeeToBeUpdated))
                .get();
    }

    private Employee updateEmployeeInfo(Employee employee, Employee employeeToBeUpdated) {

        if (employeeToBeUpdated.getName() != null) {
            employee.setName(employeeToBeUpdated.getName());
        }

        if (employeeToBeUpdated.getAge() != null) {
            employee.setAge(employeeToBeUpdated.getAge());
        }

        if (employeeToBeUpdated.getGender() != null) {
            employee.setGender(employeeToBeUpdated.getGender());
        }

        if (employeeToBeUpdated.getSalary() != null) {
            employee.setSalary(employeeToBeUpdated.getSalary());
        }
        return employee;
    }

}

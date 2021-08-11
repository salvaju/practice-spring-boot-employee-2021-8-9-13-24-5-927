package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.getEmployees();
    }

    public Employee getEmployeeById(Integer employeeId) {
        return employeeRepository.getEmployees().stream()
                .filter(employee -> employeeId.equals(employee.getId()))
                .findFirst()
                .orElse(null);
    }

    public List<Employee> getAllEmployeesByGender(String gender) {
        return employeeRepository.getEmployees().stream()
                .filter(employee -> gender.equals(employee.getGender()))
                .collect(Collectors.toList());
    }

}

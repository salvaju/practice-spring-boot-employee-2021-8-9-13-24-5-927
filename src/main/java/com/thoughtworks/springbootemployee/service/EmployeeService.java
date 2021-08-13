package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee id not found."));
    }

    public List<Employee> getAllEmployeesByGender(String gender) {
        return employeeRepository.findAllByGender(gender);
    }

    public List<Employee> getEmployeesByPagination(Integer pageIndex, Integer pageSize) {
        return employeeRepository.findAll((PageRequest.of(pageIndex - 1, pageSize))).getContent();
    }

    public Employee addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public Employee updateEmployeeInformation(Integer employeeId, Employee updatedEmployeeInfo) {
        Employee currentEmployee = getEmployeeById(employeeId);
        Employee updatedEmployee = updateEmployeeInfo(currentEmployee, updatedEmployeeInfo);
        employeeRepository.save(updatedEmployee);
        return updatedEmployee;
    }

    private Employee updateEmployeeInfo(Employee employee, Employee updatedEmployeeInfo) {

        if (updatedEmployeeInfo.getName() != null) {
            employee.setName(updatedEmployeeInfo.getName());
        }

        if (updatedEmployeeInfo.getAge() != null) {
            employee.setAge(updatedEmployeeInfo.getAge());
        }

        if (updatedEmployeeInfo.getGender() != null) {
            employee.setGender(updatedEmployeeInfo.getGender());
        }

        if (updatedEmployeeInfo.getSalary() != null) {
            employee.setSalary(updatedEmployeeInfo.getSalary());
        }

        if (updatedEmployeeInfo.getCompanyId() != null) {
            employee.setCompanyId(updatedEmployeeInfo.getCompanyId());
        }

        return employee;
    }

    public void deleteEmployee(Integer employeeId) {
        Employee willBeDeletedEmployee = getEmployeeById(employeeId);
        employeeRepository.delete(willBeDeletedEmployee);
    }
}

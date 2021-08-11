package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
        employees.add(new Employee(1, "Yuto", 23, "Male", 15000));
        employees.add(new Employee(2, "JC", 22, "Male", 10000));
        employees.add(new Employee(3, "Khali", 21, "Female", 10000));
        employees.add(new Employee(4, "Donald", 21, "Male", 104400));
        employees.add(new Employee(5, "Bob", 69, "Male", 10000));
        employees.add(new Employee(6, "Zagu", 25, "Female", 16900));
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public Employee updateEmployeeInformation(Integer employeeId, Employee employeeToBeUpdated) {
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

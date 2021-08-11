package com.thoughtworks.springbootemployee.service;


import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void should_return_all_employees_when_get_all_employees_given_all_employees(){
        // Given
            List<Employee> employees = new ArrayList<>();
            employees.add(new Employee(1, "Yuto", 23, "Male", 15000));
            employees.add(new Employee(2, "JC", 22, "Male", 10000));
            employees.add(new Employee(3, "Khali", 21, "Female", 10000));
            employees.add(new Employee(4, "Donald", 21, "Male", 104400));
            employees.add(new Employee(5, "Bob", 69, "Male", 10000));
            employees.add(new Employee(6, "Zagu", 25, "Female", 16900));
            given(employeeRepository.getEmployees()).willReturn(employees);

        // When
         List<Employee> actualEmployees = employeeService.getEmployees();

        // Then
        assertEquals(employees, actualEmployees);
        assertEquals(employees.size(), actualEmployees.size());
        assertIterableEquals(employees, actualEmployees);

    }
}

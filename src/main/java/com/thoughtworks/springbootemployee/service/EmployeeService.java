package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService{

    private RetiringEmployeeRepository retiringEmployeeRepository;

    public EmployeeService(RetiringEmployeeRepository retiringEmployeeRepository) {
        this.retiringEmployeeRepository = retiringEmployeeRepository;
    }

    public List<Employee> getEmployees() {
        return retiringEmployeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer employeeId) {
        return retiringEmployeeRepository.findById(employeeId).orElse(null);
    }

    public List<Employee> getAllEmployeesByGender(String gender) {
        return retiringEmployeeRepository.findAllByGender(gender);
    }

    public List<Employee> getEmployeesByPagination(Integer pageIndex, Integer pageSize) {
         return retiringEmployeeRepository.findAll((PageRequest.of(pageIndex - 1, pageSize))).getContent();
    }

    public void addEmployee(Employee employee) {
        retiringEmployeeRepository.save(employee);
    }

    public Employee updateEmployeeInformation(Integer employeeId, Employee updatedEmployeeInfo) {
        Employee currentEmployee = getEmployeeById(employeeId);
        Employee updatedEmployee = updateEmployeeInfo(currentEmployee, updatedEmployeeInfo);
        retiringEmployeeRepository.save(updatedEmployee);
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

        return employee;
    }

//    public void deleteEmployee(Integer employeeId) {
//        retiringEmployeeRepository.deleteEmployee(employeeId);
//    }
}

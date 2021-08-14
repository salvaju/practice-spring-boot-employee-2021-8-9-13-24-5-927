package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.dto.EmployeeReponse;
import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeRequest, employee);

        return employee;
    }

    public EmployeeReponse toResponse(Employee employee){
        EmployeeReponse employeeReponse = new EmployeeReponse();
        BeanUtils.copyProperties(employee, employeeReponse);

        return employeeReponse;
    }

    public List<EmployeeReponse> toResponse(List<Employee> employees){
        List<EmployeeReponse> employeeResponses = new ArrayList<>();

        while(employeeResponses.size() < employees.size()) employeeResponses.add(new EmployeeReponse());

        for (int i = 0; i < employees.size(); i++) {
            BeanUtils.copyProperties(employees.get(i), employeeResponses.get(i));
        }

        return employeeResponses;
    }

}

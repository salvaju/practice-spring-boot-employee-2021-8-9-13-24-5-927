package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.EmployeeReponse;
import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.model.Employee;
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

    public EmployeeReponse toResponse(Employee employee) {
        EmployeeReponse employeeReponse = new EmployeeReponse();
        BeanUtils.copyProperties(employee, employeeReponse);

        return employeeReponse;
    }

    public List<EmployeeReponse> toResponse(List<Employee> employees) {
        List<EmployeeReponse> employeeResponses = new ArrayList<>();

        employees.stream()
                .map(this::toResponse)
                .forEach(employeeResponses::add);

        return employeeResponses;
    }

}

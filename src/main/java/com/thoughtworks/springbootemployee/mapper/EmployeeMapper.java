package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.EmployeeReponse;
import com.thoughtworks.springbootemployee.model.EmployeeRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

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

}

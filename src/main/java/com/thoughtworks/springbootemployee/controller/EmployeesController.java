package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.dto.EmployeeReponse;
import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeesController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<EmployeeReponse> getAllEmployees() {
        return employeeMapper.toResponse(employeeService.getEmployees());
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeReponse getEmployeeById(@PathVariable Integer employeeId) {
        return employeeMapper.toResponse(employeeService.getEmployeeById(employeeId));
    }

    @GetMapping(params = "gender")
    public List<EmployeeReponse> getAllEmployeesByGender(@RequestParam String gender) {
        return employeeMapper.toResponse(employeeService.getAllEmployeesByGender(gender));
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<EmployeeReponse> getEmployeesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
         return employeeMapper.toResponse(employeeService.getEmployeesByPagination(pageIndex, pageSize));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EmployeeReponse addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeService.addEmployee(employeeMapper.toEntity(employeeRequest));
       return employeeMapper.toResponse(employee);
    }

    @PutMapping(path = "/{employeeId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public EmployeeReponse updateEmployeeInformation(@PathVariable Integer employeeId, @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeService.updateEmployeeInformation(employeeId, employeeMapper.toEntity(employeeRequest));
        return employeeMapper.toResponse(employee);
    }

    @DeleteMapping(path = "/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId) {
         employeeService.deleteEmployee(employeeId);
    }

}

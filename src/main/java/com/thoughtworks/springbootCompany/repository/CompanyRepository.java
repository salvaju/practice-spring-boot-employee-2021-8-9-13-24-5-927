package com.thoughtworks.springbootCompany.repository;

import com.thoughtworks.springbootCompany.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {

    private List<Company> companies;

    public CompanyRepository() {
        List<Employee> ooclEmployees = new ArrayList<>();
        ooclEmployees.add(new Employee(1,"JC", 23, "Male", 10 ));

        List<Employee> coscoEmployees = new ArrayList<>();
        ooclEmployees.add(new Employee(1,"Pekora", 23, "Female", 10 ));

        List<com.thoughtworks.springbootCompany.model.Company> companies = new ArrayList<>();
        companies.add(new com.thoughtworks.springbootCompany.model.Company("OOCL", 10, ooclEmployees));
        companies.add(new com.thoughtworks.springbootCompany.model.Company("COSCO", 10, coscoEmployees));
    }

    public List<Company> getCompanies() {
        return companies;
    }
}

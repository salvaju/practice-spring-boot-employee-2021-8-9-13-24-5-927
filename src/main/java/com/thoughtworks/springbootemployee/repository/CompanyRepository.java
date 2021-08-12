package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CompanyRepository {
    private List<Company> companies;

    public CompanyRepository() {
        List<Employee> ooclEmployees = new ArrayList<>();
        ooclEmployees.add(new Employee(1,"JC", 23, "Male", 10 ));

        List<Employee> coscoEmployees = new ArrayList<>();
        coscoEmployees.add(new Employee(1,"Pekora", 23, "Female", 10 ));

        companies = new ArrayList<>();
        companies.add(new Company(1, "OOCL", 10, ooclEmployees));
        companies.add(new Company(2, "COSCO", 10, coscoEmployees));
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public Company getCompanyByID(Integer companyId) {
        return companies.stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .findFirst()
                .orElse(null);
    }

    public List<Employee> getCompanyEmployees(Integer companyId) {
        return companies.stream()
                .filter(company -> company.getCompanyId().equals(companyId))
                .map(company -> company.getEmployees())
                .findFirst()
                .orElse(null);
    }

    public List<Company> getCompaniesByPagination(Long pageIndex, Long pageSize) {
        return null;
    }
}

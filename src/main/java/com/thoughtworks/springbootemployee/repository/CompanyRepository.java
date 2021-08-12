package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CompanyRepository {
    private List<Company> companies;

    public CompanyRepository() {
        List<Employee> ooclEmployees = new ArrayList<>();
        ooclEmployees.add(new Employee(1,"JC", 23, "Male", 10 ));

        List<Employee> coscoEmployees = new ArrayList<>();
        coscoEmployees.add(new Employee(1,"Pekora", 23, "Female", 1023 ));

        List<Employee> holoEmployees = new ArrayList<>();
        holoEmployees.add(new Employee(1,"Coco", 23, "Female", 1044 ));

        List<Employee> nijisanjiEmployees = new ArrayList<>();
        nijisanjiEmployees.add(new Employee(1,"Gura", 23, "Female", 666 ));

        List<Employee> vtuberEmployees = new ArrayList<>();
        vtuberEmployees.add(new Employee(1,"TMT", 23, "Female", 10000 ));

        List<Employee> xiaomiEmployees = new ArrayList<>();
        xiaomiEmployees.add(new Employee(1,"Peko", 23, "Male", 10 ));


        companies = new ArrayList<>();
        companies.add(new Company(1, "OOCL", 10, ooclEmployees));
        companies.add(new Company(2, "COSCO", 10, coscoEmployees));
        companies.add(new Company(3, "HOLOLIVE", 10, holoEmployees));
        companies.add(new Company(4, "NIJISANJI", 10, nijisanjiEmployees));
        companies.add(new Company(5, "VTUBER", 10, vtuberEmployees));
        companies.add(new Company(6, "XIAOMI", 10, xiaomiEmployees));
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
        return companies.stream()
                .skip((pageIndex - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public void addCompany(Company company) {
        //do nothing
    }
}

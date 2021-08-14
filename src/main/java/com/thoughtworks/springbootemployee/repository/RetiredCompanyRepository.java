package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RetiredCompanyRepository {
    private List<Company> companies;

    public RetiredCompanyRepository() {
        List<Employee> ooclEmployees = new ArrayList<>();
        ooclEmployees.add(new Employee("JC", 23, "Male", 10 ));

        List<Employee> coscoEmployees = new ArrayList<>();
        coscoEmployees.add(new Employee("Pekora", 23, "Female", 1023 ));

        List<Employee> holoEmployees = new ArrayList<>();
        holoEmployees.add(new Employee("Coco", 23, "Female", 1044 ));

        List<Employee> nijisanjiEmployees = new ArrayList<>();
        nijisanjiEmployees.add(new Employee("Gura", 23, "Female", 666 ));

        List<Employee> vtuberEmployees = new ArrayList<>();
        vtuberEmployees.add(new Employee("TMT", 23, "Female", 10000 ));

        List<Employee> xiaomiEmployees = new ArrayList<>();
        xiaomiEmployees.add(new Employee("Peko", 23, "Male", 10 ));


        companies = new ArrayList<>();
        companies.add(new Company(1, "OOCL",  ooclEmployees));
        companies.add(new Company(2, "COSCO",coscoEmployees));
        companies.add(new Company(3, "HOLOLIVE",  holoEmployees));
        companies.add(new Company(4, "NIJISANJI",  nijisanjiEmployees));
        companies.add(new Company(5, "VTUBER",  vtuberEmployees));
        companies.add(new Company(6, "XIAOMI",  xiaomiEmployees));
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public Company getCompanyByID(Integer companyId) {
        return companies.stream()
                .filter(company -> company.getId().equals(companyId))
                .findFirst()
                .orElse(null);
    }

    public List<Employee> getCompanyEmployees(Integer companyId) {
        return companies.stream()
                .filter(company -> company.getId().equals(companyId))
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
        companies.add(company);
    }

    public Company updateCompanyInformation(Integer companyId, Company companyToBeUpdated) {
        return companies.stream()
                .filter(company -> companyId.equals(company.getId()))
                .findFirst()
                .map(company -> updateCompanyInfo(company, companyToBeUpdated))
                .get();
    }

    private Company updateCompanyInfo(Company company, Company companyToBeUpdated) {

        if (companyToBeUpdated.getCompanyName() != null) {
            company.setCompanyName(companyToBeUpdated.getCompanyName());
        }

        if (companyToBeUpdated.getEmployees() != null) {
            company.setEmployees(companyToBeUpdated.getEmployees());
        }

        return company;
    }

    public void deleteEmployee(Integer companyId) {
        Company deletedCompany = companies.stream()
                .filter(company -> company.getId().equals(companyId))
                .findFirst()
                .orElse(null);

        companies.remove(deletedCompany);
    }
}

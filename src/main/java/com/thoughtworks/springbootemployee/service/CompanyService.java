package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getCompanies() {
        return companyRepository.getCompanies();
    }

    public Company getCompanyByID(Integer companyID) {
        return companyRepository.getCompanyByID(companyID);
    }

    public List<Employee> getCompanyEmployees(Integer companyId) {
        return companyRepository.getCompanyEmployees(companyId);
    }

    public List<Company> getCompaniesByPagination(Long pageIndex, Long pageSize) {
        return companyRepository.getCompaniesByPagination(pageIndex, pageSize);
    }

    public void addCompany(Company company) {
        companyRepository.addCompany(company);
    }

    public Company updateCompanyInformation(Integer companyID, Company updatedCompany) {
        return companyRepository.updateCompanyInformation(companyID, updatedCompany);
    }
}

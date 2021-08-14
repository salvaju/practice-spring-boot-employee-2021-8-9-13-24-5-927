package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.RetiredCompanyRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private RetiredCompanyRepository retiredCompanyRepository;
    private CompanyRepository companyRepository;

    public CompanyService(RetiredCompanyRepository retiredCompanyRepository, CompanyRepository companyRepository) {
        this.retiredCompanyRepository = retiredCompanyRepository;
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Integer companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    public List<Employee> getCompanyEmployees(Integer companyId) {
        Company company = companyRepository.findById(companyId).orElse(null);
        return company.getEmployees();
    }

    public List<Company> getCompaniesByPagination(Integer pageIndex, Integer pageSize) {
        return companyRepository.findAll((PageRequest.of(pageIndex - 1, pageSize))).getContent();
    }

    public Company addCompany(Company company) {
        companyRepository.save(company);
        return company;
    }

    public Company updateCompanyInformation(Integer companyID, Company updatedCompany) {
        return retiredCompanyRepository.updateCompanyInformation(companyID, updatedCompany);
    }

    public void deleteCompany(Integer companyId) {
        retiredCompanyRepository.deleteEmployee(companyId);
    }
}

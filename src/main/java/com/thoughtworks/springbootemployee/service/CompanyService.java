package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Integer companyId) {
        return companyRepository.findById(companyId).orElseThrow(() -> new CompanyNotFoundException("Company not found"));
    }

    public List<Employee> getCompanyEmployees(Integer companyId) {
        return getCompanyById(companyId).getEmployees();
    }

    public List<Company> getCompaniesByPagination(Integer pageIndex, Integer pageSize) {
        return companyRepository.findAll((PageRequest.of(pageIndex - 1, pageSize))).getContent();
    }

    public Company addCompany(Company company) {
        companyRepository.save(company);
        return company;
    }

    public Company updateCompanyInformation(Integer companyId, Company updatedCompanyInfo) {
        return Optional.ofNullable(getCompanyById(companyId))
                .map(company -> updateCompanyInfo(company, updatedCompanyInfo))
                .map(this::addCompany)
                .orElse(null);
    }

    private Company updateCompanyInfo(Company company, Company updatedCompanyInfo) {
        if (updatedCompanyInfo.getCompanyName() != null) {
            company.setCompanyName(updatedCompanyInfo.getCompanyName());
        }
        return company;
    }

    public void deleteCompany(Integer companyId) {
        Company company = getCompanyById(companyId);
        companyRepository.delete(company);
    }
}

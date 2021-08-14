package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;
    private final CompanyMapper companyMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @GetMapping
    public List<CompanyResponse> getAllCompanies() {
        return companyMapper.toResponse(companyService.getAllCompanies());
    }

    @GetMapping(path = "/{companyId}")
    public Company getCompanyById(@PathVariable Integer companyId) {
        return companyService.getCompanyById(companyId);
    }

    @GetMapping(path = "/{companyId}/employees")
    public List<Employee> getCompanyEmployees(@PathVariable Integer companyId) {
        return companyService.getCompanyEmployees(companyId);
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Company> getCompaniesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return companyService.getCompaniesByPagination(pageIndex, pageSize);
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

    @PutMapping(path = "/{companyId}")
    public Company updateCompanyInformation(@PathVariable Integer companyId, @RequestBody Company updatedCompanyInfo) {
        return companyService.updateCompanyInformation(companyId, updatedCompanyInfo);
    }

    @DeleteMapping(path = "/{companyId}")
    public void deleteCompany(@PathVariable Integer companyId) {
        companyService.deleteCompany(companyId);
    }
}

package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeReponse;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;
    private final CompanyMapper companyMapper;
    private final EmployeeMapper employeeMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper, EmployeeMapper employeeMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<CompanyResponse> getAllCompanies() {
        return companyMapper.toResponse(companyService.getAllCompanies());
    }

    @GetMapping(path = "/{companyId}")
    public CompanyResponse getCompanyById(@PathVariable Integer companyId) {
        return companyMapper.toResponse(companyService.getCompanyById(companyId));
    }

    @GetMapping(path = "/{companyId}/employees")
    public List<EmployeeReponse> getCompanyEmployees(@PathVariable Integer companyId) {
        return employeeMapper.toResponse(companyService.getCompanyEmployees(companyId));
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<CompanyResponse> getCompaniesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return companyMapper.toResponse(companyService.getCompaniesByPagination(pageIndex, pageSize));
    }

    @PostMapping
    public CompanyResponse addCompany(@RequestBody CompanyRequest companyRequest) {
        Company company = companyService.addCompany(companyMapper.toEntity(companyRequest));
        return companyMapper.toResponse(company);
    }

    @PutMapping(path = "/{companyId}")
    public CompanyResponse updateCompanyInformation(@PathVariable Integer companyId, @RequestBody CompanyRequest companyRequest) {
        Company company = companyService.updateCompanyInformation(companyId, (companyMapper.toEntity(companyRequest)));
        return companyMapper.toResponse(company);
    }

    @DeleteMapping(path = "/{companyId}")
    public void deleteCompany(@PathVariable Integer companyId) {
        companyService.deleteCompany(companyId);
    }
}

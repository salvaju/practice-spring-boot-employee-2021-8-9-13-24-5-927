package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    @Test
    void should_return_all_Company_when_get_all_company_given_all_companies(){
        // Given
        List<Employee> ooclEmployees = new ArrayList<>();
        ooclEmployees.add(new Employee(1,"JC", 23, "Male", 10 ));

        List<Employee> coscoEmployees = new ArrayList<>();
        coscoEmployees.add(new Employee(1,"Pekora", 23, "Female", 10 ));

        List<Company> companies = new ArrayList<>();
        companies.add(new Company("OOCL", 10, ooclEmployees));
        companies.add(new Company("COSCO", 10, coscoEmployees));
        when(companyRepository.getCompanies()).thenReturn(companies);

        // When
        List<Company> actualCompany = companyService.getCompanies();

        // Then
        assertEquals(companies, actualCompany);
        assertEquals(companies.size(), actualCompany.size());
        assertIterableEquals(companies, actualCompany);
    }
}

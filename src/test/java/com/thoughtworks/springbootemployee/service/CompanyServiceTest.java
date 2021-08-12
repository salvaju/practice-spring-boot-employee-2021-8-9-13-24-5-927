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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

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
        companies.add(new Company(1, "OOCL", 10, ooclEmployees));
        companies.add(new Company(2,"COSCO", 10, coscoEmployees));
        when(companyRepository.getCompanies()).thenReturn(companies);

        // When
        List<Company> actualCompany = companyService.getCompanies();

        // Then
        assertEquals(companies, actualCompany);
        assertEquals(companies.size(), actualCompany.size());
        assertIterableEquals(companies, actualCompany);
    }

        @Test
    void should_return_correct_company_when_get_company_by_id_given_a_company_id(){
        // Given
        List<Employee> ooclEmployees = new ArrayList<>();
        ooclEmployees.add(new Employee(1,"JC", 23, "Male", 10 ));

        Company company = new Company(1, "OOCL", 10, ooclEmployees);
        given(companyRepository.getCompanyByID(1)).willReturn(company);

        // When
        Company actualCompany = companyService.getCompanyByID(1);

        // Then
        assertEquals(company, actualCompany);
    }

    @Test
    void should_return_correct_company_employees_when_get_company_employees_given_all_company_and_company_id(){
        // Given
        List<Employee> ooclEmployees = new ArrayList<>();
        ooclEmployees.add(new Employee(1,"JC", 23, "Male", 10 ));
        given(companyRepository.getCompanyEmployees(1)).willReturn(ooclEmployees);

        // When
        List<Employee> actualOoclEmployees = companyService.getCompanyEmployees(1);

        // Then
        assertEquals(ooclEmployees, actualOoclEmployees);
    }

    @Test
    void should_return_correct_list_of_Companies_by_Pagination_when_get_companies_by_pagination_given_all_companies_and_pageIndex_and_page_size(){
        // Given
        List<Employee> xiaomiEmployees = new ArrayList<>();
        xiaomiEmployees.add(new Employee(1,"Peko", 23, "Male", 10 ));

        List<Company> companies = new ArrayList<>();
        companies.add(new Company(1, "Xiamoi", 10, xiaomiEmployees));

        given(companyRepository.getCompaniesByPagination(2L,5L)).willReturn(companies);

        // When
        List<Company> actualCompanies = companyService.getCompaniesByPagination(2L, 5L);

        // Then
        assertEquals(companies, actualCompanies);
    }

    @Test
    void should_add_company_when_add_company_given_a_new_Company(){
        // Given
        List<Employee> samsungEmployees = new ArrayList<>();
        samsungEmployees.add(new Employee(1,"Tenshi", 23, "Female", 10 ));

        Company samsungCompany = new Company(7, "Samsung", 10, samsungEmployees);

        // When
        companyService.addCompany(samsungCompany);

        // Then
        verify(companyRepository, times(1)).addCompany(samsungCompany);
    }

}

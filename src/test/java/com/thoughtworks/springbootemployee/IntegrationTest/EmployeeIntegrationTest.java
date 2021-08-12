package com.thoughtworks.springbootemployee.IntegrationTest;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void should_return_all_employees_when_call_get_employees_api() throws Exception {
        //given
        final Employee employee = new Employee(1,"JC", 21, "Male", 999);
        employeeRepository.save(employee);

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("JC"))
                .andExpect(jsonPath("$[0].age").value(21))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[0].salary").value(999));
    }

    @Test
    void should_add_employee_when_call_add_employee_api() throws Exception {
        //given
        String employee = "{\n" +
                "\"name\": \"JC\",\n" +
                "\"age\": 21,\n" +
                "\"gender\": \"Male\",\n" +
                "\"salary\": 999\n" +
                "}";

        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employee))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("JC"))
                .andExpect(jsonPath("$.age").value(21))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.salary").value(999));
    }

    @Test
    void should_return_correct_employee_when_call_get_employees_by_id_api() throws Exception {
        //given
        final Employee firstEmployee = new Employee(1,"Rushia", 21, "Female", 999);
        employeeRepository.save(firstEmployee);

        final Employee secondEmployee = new Employee(2,"Pekora", 21, "Female", 999);
        employeeRepository.save(secondEmployee);

        //when

        //then

        int employeeId = 2;

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{employeeId}", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pekora"))
                .andExpect(jsonPath("$.age").value(21))
                .andExpect(jsonPath("$.gender").value("Female"))
                .andExpect(jsonPath("$.salary").value(999));
    }
}

package com.thoughtworks.springbootemployee.IntegrationTest;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void should_return_all_employees_when_call_get_employees_api() throws Exception {
        //given
        final Employee employee = new Employee("JC", 21, "Male", 999);
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
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("JC"))
                .andExpect(jsonPath("$.age").value(21))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.salary").value(999));
    }

    @Test
    void should_return_correct_employee_when_call_get_employees_by_id_api() throws Exception {
        //given
        final Employee firstEmployee = new Employee("Rushia", 21, "Female", 999);
        employeeRepository.save(firstEmployee);

        final Employee secondEmployee = new Employee("Pekora", 21, "Female", 999);
        employeeRepository.save(secondEmployee);

        //when

        //then

        int employeeId = secondEmployee.getId();

        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{employeeId}", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pekora"))
                .andExpect(jsonPath("$.age").value(21))
                .andExpect(jsonPath("$.gender").value("Female"))
                .andExpect(jsonPath("$.salary").value(999));
    }

    @Test
    void should_return_list_of_male_employees_when_call_get_all_employees_by_gender_api() throws Exception {
        //given
        final Employee firstEmployee = new Employee(1,"Rushia", 21, "Female", 999);
        employeeRepository.save(firstEmployee);

        final Employee secondEmployee = new Employee(2,"Nijisanji", 21, "Male", 999);
        employeeRepository.save(secondEmployee);

        final Employee thirdEmployee = new Employee(3,"Cong", 21, "Male", 999);
        employeeRepository.save(thirdEmployee);

        //when

        //then

        String gender = "Male";

        mockMvc.perform(MockMvcRequestBuilders.get("/employees?gender={gender}", gender))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Nijisanji"))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[1].name").value("Cong"))
                .andExpect(jsonPath("$[1].gender").value("Male"));
    }

    @Test
    void should_return_list_of_employees_when_call_get_all_employees_by_pagination_api() throws Exception {
        //given
        List<Employee> employees = new ArrayList<>();
        final Employee firstEmployee = new Employee(1,"Rushia", 21, "Female", 999);
        employeeRepository.save(firstEmployee);

        final Employee secondEmployee = new Employee(2,"Nijisanji", 21, "Male", 999);
        employeeRepository.save(secondEmployee);

        final Employee thirdEmployee = new Employee(3,"Cong", 21, "Male", 999);
        employeeRepository.save(thirdEmployee);

        final Employee fourthEmployee = new Employee(4,"Razor", 23, "Female", 999);
        employeeRepository.save(fourthEmployee);

        final Employee fifthEmployee = new Employee(5,"Gon", 26, "Female", 999);
        employeeRepository.save(fifthEmployee);

        final Employee sixthEmployee = new Employee(6,"Kuku", 24, "Male", 999);
        employeeRepository.save(sixthEmployee);

        final Employee seventhEmployee = new Employee(7,"Whitemon", 69, "Male", 999);
        employeeRepository.save(seventhEmployee);

        //when

        //then

        Integer pageIndex = 2;
        Integer pageSize = 5;

        mockMvc.perform(MockMvcRequestBuilders.get("/employees?pageIndex={pageIndex}&pageSize={pageSize}", pageIndex, pageSize))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Kuku"))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[1].name").value("Whitemon"))
                .andExpect(jsonPath("$[1].gender").value("Male"));
    }

    @Test
    void should_update_employee_when_call_update_employee_api() throws Exception {
        //given
        final Employee firstEmployee = new Employee(1,"Rushia", 21, "Female", 999);
        final Employee savedEmployee = employeeRepository.save(firstEmployee);
        String employeeWithNewInfo = "{\n" +
                "\"name\": \"JC\",\n" +
                "\"age\": 21,\n" +
                "\"gender\": \"Male\",\n" +
                "\"salary\": 999\n" +
                "}";

        //when

        //then
        Integer employeeId = savedEmployee.getId();
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/{employeeId}", employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeWithNewInfo))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("JC"))
                .andExpect(jsonPath("$.age").value(21))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.salary").value(999));
    }

    @Test
    void should_return_delete_employee_when_call_delete_employee_api() throws Exception {
        //given
        final Employee firstEmployee = new Employee("Rushia", 21, "Female", 999);
        employeeRepository.save(firstEmployee);

        //when

        //then

        int employeeId = firstEmployee.getId();

        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{employeeId}", employeeId))
                .andExpect(status().isOk());
    }
}

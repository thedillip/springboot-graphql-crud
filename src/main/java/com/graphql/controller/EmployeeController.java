package com.graphql.controller;

import com.graphql.request.EmployeeRequest;
import com.graphql.response.EmployeeResponse;
import com.graphql.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @QueryMapping(value = "employees")
    public List<EmployeeResponse> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @QueryMapping(value = "employee")
    public EmployeeResponse getEmployeeById(@Argument(name = "employeeId") final int id) {
        return employeeService.getEmployeeById(id);
    }

    @QueryMapping(value = "employeesByCountry")
    public List<EmployeeResponse> getEmployeesByCountry(@Argument(name = "country") final String country) {
        return employeeService.getEmployeesByCountry(country);
    }

    @MutationMapping(value = "employee")
    public EmployeeResponse addEmployee(@Argument(name = "employeeRequest") final EmployeeRequest employeeRequest) {
        return employeeService.addEmployee(employeeRequest);
    }

    @MutationMapping(value = "updateEmployeeById")
    public EmployeeResponse updateEmployeeById(@Argument(name = "employeeRequest") final EmployeeRequest employeeRequest) {
        return employeeService.updateEmployeeById(employeeRequest);
    }

    @MutationMapping(value = "deleteEmployeeById")
    public String deleteEmployeeById(@Argument(name = "employeeId") final int id){
        return employeeService.deleteEmployeeById(id);
    }
}

package com.graphql.service;

import com.graphql.request.EmployeeRequest;
import com.graphql.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> getAllEmployee();

    EmployeeResponse getEmployeeById(int id);

    List<EmployeeResponse> getEmployeesByCountry(String country);

    EmployeeResponse addEmployee(EmployeeRequest employeeRequest);

    EmployeeResponse updateEmployeeById(EmployeeRequest employeeRequest);
    String deleteEmployeeById(int id);
}

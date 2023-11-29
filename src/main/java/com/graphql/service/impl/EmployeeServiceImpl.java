package com.graphql.service.impl;

import com.graphql.entity.Employee;
import com.graphql.exception.ResourceNotFound;
import com.graphql.repository.EmployeeRepository;
import com.graphql.request.EmployeeRequest;
import com.graphql.response.EmployeeResponse;
import com.graphql.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<EmployeeResponse> getAllEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeResponse> response = employeeList.parallelStream().map(employee -> modelMapper.map(employee, EmployeeResponse.class)).toList();
        return response;
    }

    @Override
    public EmployeeResponse getEmployeeById(final int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Employee Details not found with id = " + id));
        return modelMapper.map(employee, EmployeeResponse.class);
    }

    @Override
    public List<EmployeeResponse> getEmployeesByCountry(final String country) {
        List<Employee> employeeList = employeeRepository.findByCountryIgnoreCase(country);
        return employeeList.parallelStream().map(employee -> modelMapper.map(employee, EmployeeResponse.class)).toList();
    }

    @Override
    public EmployeeResponse addEmployee(final EmployeeRequest employeeRequest) {
        Employee employee = modelMapper.map(employeeRequest, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return modelMapper.map(savedEmployee, EmployeeResponse.class);
    }

    @Override
    public EmployeeResponse updateEmployeeById(final EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(employeeRequest.getId()).orElseThrow(() -> new ResourceNotFound("Employee Details not found with id = " + employeeRequest.getId()));
        Employee employeeToUpdate = modelMapper.map(employeeRequest, Employee.class);
        Employee updatedEmployee = employeeRepository.save(employeeToUpdate);
        return modelMapper.map(updatedEmployee, EmployeeResponse.class);
    }

    @Override
    public String deleteEmployeeById(final int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Employee Details not found with id = " + id));
        employeeRepository.delete(employee);
        return "Employee details deleted with id = " + id;
    }
}

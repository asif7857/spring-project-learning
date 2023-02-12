package com.fissionlabs.coe.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fissionlabs.coe.entity.Employee;
import com.fissionlabs.coe.entity.UserInfo;
import com.fissionlabs.coe.exception.ResourceNotFoundException;
import com.fissionlabs.coe.service.EmployeeService;
import com.fissionlabs.coe.service.UserInfoService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/welcome")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN','ROLE_USER')")
	public String welcome() {
		return "Welcome to COE Base project";
	}

	@GetMapping("/employees")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/employees/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/employees")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Employee createEmployee(@Validated @RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}

	@PutMapping("/employees/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Validated @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeService.updateEmployee(employeeId, employeeDetails);
		return ResponseEntity.ok(employee);
	}

	@DeleteMapping("/employees/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Map<String, Boolean> response = employeeService.deleteEmployee(employeeId);
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}

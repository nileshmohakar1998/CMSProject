package com.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.Credentials;
import com.app.dtos.EmployeeDto;
import com.app.dtos.Response;
import com.app.dtos.UpdateEmployeeDto;
import com.app.service.EmployeeServiceImpl;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/employee")
public class EmployeeControllerImpl {
	@Autowired
	private EmployeeServiceImpl employeeService;
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody Credentials cred) {
		EmployeeDto employeeDto = employeeService.authenticateEmployee(cred);
		if (employeeDto == null)
			return Response.error("Employee not found");
		return Response.success(employeeDto);
	}
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody EmployeeDto employeeDto) {
		Map<String, Object> result = employeeService.registerEmployee(employeeDto);
		if (result.containsKey("error"))
			return Response.error(result);
		return Response.success(result);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody UpdateEmployeeDto updateEmployeeDto) {
		Map<String, Object> result = employeeService.updateEmployee(id, updateEmployeeDto);
		if (result.containsKey("error"))
			return Response.error(result);
		return Response.success(result);
	}
	@PostMapping("/deactivate")
	public ResponseEntity<?> deactivate(@RequestBody UpdateEmployeeDto updateEmployeeDto) {
		Map<String, Object> result = employeeService.deactivateEmployee(updateEmployeeDto);
		if (result.containsKey("error"))
			return Response.error(result);
		return Response.success(result);
	}
	@GetMapping("/getall")
	public ResponseEntity<?> showAllEmployees() {
		Map<String, Object> result = employeeService.findAllEmployees();
		if (result.containsKey("error"))
			return Response.error(result);
		return Response.success(result);
	}
	@GetMapping("/assignedShipments/{id}")
	public ResponseEntity<?> showAllEmployees(@PathVariable("id") int id) {
		Map<String, Object> result = employeeService.findAssignedShipments(id);
		if (result.containsKey("error"))
			return Response.error(result);
		return Response.success(result);
	}

}

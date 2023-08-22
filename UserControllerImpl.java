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
import com.app.dtos.Response;
import com.app.dtos.ShipmentUserDto;
import com.app.dtos.UserDto;
import com.app.service.UserServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserControllerImpl {
	
	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody Credentials cred){
		UserDto userDto = userService.authenticateUser(cred);
		if(userDto==null)
			return Response.error("user not found");
		return Response.success(userDto);
	}

	@PostMapping("/register")
	public ResponseEntity<?> signup(@RequestBody UserDto userDto){
		Map<String, Object> result= userService.registerUser(userDto);
		if(result.containsKey("result"))
			return Response.error(result);
		return Response.success(result);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ShipmentUserDto userDto){
		Map<String,Object> result=userService.updateUser(id, userDto);
		if(result.containsKey("error"))
			return Response.error(result);
		return Response.success(result);
	}
	
	@GetMapping("/viewAll/{id}")
	public ResponseEntity<?> getShipments(@PathVariable("id") int id) {
		Map<String, Object> result = userService.findAll(id);
		if (result.containsKey("error"))
			return Response.error(result);
		return Response.success(result);
	}

	@GetMapping("/getUserById")
	public ResponseEntity<?> getUser(@RequestBody UserDto userId) {
		Map<String, Object> result = userService.getUserById(userId);
		if (result.containsKey("error"))
			return Response.error(result);
		return Response.success(result);
	}
	

}

package com.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.Response;
import com.app.entities.Enquiry;
import com.app.service.EnquiryServiceImpl;


@RestController
@RequestMapping("/enquiry")
@CrossOrigin(origins = "http://localhost:3000")
public class EnquiryController {

	@Autowired
	private EnquiryServiceImpl enquiryService;

	@PostMapping("/addQuery")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> enquiry(@RequestBody Enquiry enquiry) {
		Map<String, Object> result = enquiryService.postEnquiry(enquiry);
		if (result.containsKey("error"))
			return Response.error(result);
		return Response.success(result);
	}

	@GetMapping("/viewAll")
	public ResponseEntity<?> getShipments() {
		Map<String, Object> result = enquiryService.findAll();
		if (result.containsKey("error"))
			return Response.error(result);
		return Response.success(result);
	}

}

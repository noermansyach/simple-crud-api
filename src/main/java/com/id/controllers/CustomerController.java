package com.id.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.id.dto.CustomerDto;
import com.id.enums.ResponseEnum;
import com.id.responses.BaseResponse;
import com.id.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/get-customer")
	public ResponseEntity<BaseResponse> getCustomer(
			@RequestParam(required=false) String custId,
			@RequestParam(required=false, defaultValue = "1") int page, 
			@RequestParam(required=false, defaultValue = "5") int pageSize) {
		
		try {
			BaseResponse customer = customerService.getCustomer(custId, page, pageSize);
			
			// write log here
			return ResponseEntity.status(customer.getCode()).body(customer);
		} catch (Exception e) {
			BaseResponse response = new BaseResponse();
			response.setCode(ResponseEnum.technicalError.getCode());
			response.setStatus(ResponseEnum.technicalError.getStatus());
			response.setMessage(ResponseEnum.technicalError.getMessage());
			
			// write log here
			return ResponseEntity.status(response.getCode()).body(response);
		}	
	}
	
	@PostMapping("/add-customer")
	public ResponseEntity<BaseResponse> addCustomer(@RequestBody CustomerDto body) {
		try {
			BaseResponse response = customerService.addCustomer(body);
			
			// write log here
			return ResponseEntity.status(response.getCode()).body(response);
		} catch (Exception e) {
			BaseResponse response = new BaseResponse();
			response.setCode(ResponseEnum.technicalError.getCode());
			response.setStatus(ResponseEnum.technicalError.getStatus());
			response.setMessage(ResponseEnum.technicalError.getMessage());
			
			// write log here
			return ResponseEntity.status(response.getCode()).body(response);
		}
	}
	
	@PutMapping("/update-customer")
	public ResponseEntity<BaseResponse> updateCustomer(@RequestParam String custId, 
			@RequestBody CustomerDto body) {
		try {
			BaseResponse response = customerService.updateCustomer(custId, body);
			
			// write log here
			return ResponseEntity.status(response.getCode()).body(response);
		} catch (Exception e) {
			BaseResponse response = new BaseResponse();
			response.setCode(ResponseEnum.technicalError.getCode());
			response.setStatus(ResponseEnum.technicalError.getStatus());
			response.setMessage(ResponseEnum.technicalError.getMessage());
			
			// write log here
			return ResponseEntity.status(response.getCode()).body(response); 
		}
	}
	
	@DeleteMapping("/delete-customer")
	public ResponseEntity<BaseResponse> deleteCustomer(@RequestParam String custId) {
		try {
			BaseResponse response = customerService.deleteCustomer(custId);
			
			// write log here
			return ResponseEntity.status(response.getCode()).body(response);
		} catch (Exception e) {
			BaseResponse response = new BaseResponse();
			response.setCode(ResponseEnum.technicalError.getCode());
			response.setStatus(ResponseEnum.technicalError.getStatus());
			response.setMessage(ResponseEnum.technicalError.getMessage());
			
			// write log here
			return ResponseEntity.status(response.getCode()).body(response); 
		}
	}
	
}

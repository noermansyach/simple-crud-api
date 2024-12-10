package com.id.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.id.dto.CustomerDto;
import com.id.enums.ResponseEnum;
import com.id.models.Customer;
import com.id.repository.CustomerRepo;
import com.id.responses.BaseResponse;
import com.id.responses.DataResponse;
import com.id.responses.WithTotalDataResponse;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;
	
	public BaseResponse getCustomer(String custId, int page, int pageSize) {
		if (custId != null) {
			Customer customer = customerRepo.findById(custId);
			if (customer == null) {
				BaseResponse response = new BaseResponse();
				response.setCode(ResponseEnum.notFound.getCode());
				response.setStatus(ResponseEnum.notFound.getStatus());
				response.setMessage(ResponseEnum.notFound.getMessage());
				
				// write log here
				return response;
			}
			
			DataResponse response = new DataResponse();
			response.setCode(ResponseEnum.retrieveSuccess.getCode());
			response.setStatus(ResponseEnum.retrieveSuccess.getStatus());
			response.setMessage(ResponseEnum.retrieveSuccess.getMessage());
			response.setData(customer);
			
			// write log here
			return response;
		} else {
			List<Customer> customer = customerRepo.findAll();
			int totalData = customer.size();
			if (totalData == 0) {
				BaseResponse response = new BaseResponse();
				response.setCode(ResponseEnum.stillEmpty.getCode());
				response.setStatus(ResponseEnum.stillEmpty.getStatus());
				response.setMessage(ResponseEnum.stillEmpty.getMessage());
				
				// write log here
				return response;
			}
			
			// pagination
			int fromIndex = (page-1)* pageSize;
			int toIndex = Math.min(fromIndex + pageSize, customer.size());
			int totalPages = (int) Math.ceil((double) customer.size() / pageSize);
			
			if (page > totalPages) {
				BaseResponse response = new BaseResponse();
				response.setCode(ResponseEnum.noContent.getCode());
				response.setStatus(ResponseEnum.noContent.getStatus());
				response.setMessage(ResponseEnum.noContent.getMessage());	
				
				// write log here
				return response;
			} else {
				customer = customer.subList(fromIndex, toIndex);
			}
			
			WithTotalDataResponse response = new WithTotalDataResponse();
			response.setCode(ResponseEnum.retrieveSuccess.getCode());
			response.setStatus(ResponseEnum.retrieveSuccess.getStatus());
			response.setMessage(ResponseEnum.retrieveSuccess.getMessage());
			response.setTotalData(totalData);
			response.setData(customer);
			
			// write log here
			return response;
		}
	}
	
	public BaseResponse addCustomer(CustomerDto customerDto) {
		Customer customer = customerRepo.findByName(customerDto.getName());
		
		if (customer != null) {
			BaseResponse response = new BaseResponse(ResponseEnum.dataDuplicate.getCode(), 
					ResponseEnum.dataDuplicate.getStatus(), 
					ResponseEnum.dataDuplicate.getMessage());
			
			// write log here
			return response;
		} else {
			customer = customerRepo.addCustomer(customerDto);
			DataResponse response = new DataResponse(ResponseEnum.addSuccess.getCode(),
					ResponseEnum.addSuccess.getStatus(), 
					ResponseEnum.addSuccess.getMessage(), customer);
			
			// write log here
			return response;
		}
		
	}
	
	public BaseResponse updateCustomer(String id, CustomerDto customerDto) {
		Customer customer = customerRepo.updateCustomer(id, customerDto);
		
		if (customer == null) {
			BaseResponse response = new BaseResponse();
			response.setCode(ResponseEnum.notFound.getCode());
			response.setStatus(ResponseEnum.notFound.getStatus());
			response.setMessage("There's no data with id '" + id + "'.");
			
			// write log here
			return response;
		} else {
			DataResponse response = new DataResponse(ResponseEnum.updateSuccess.getCode(), 
					ResponseEnum.updateSuccess.getStatus(), ResponseEnum.updateSuccess.getMessage(), 
					customer);
			
			// write log here
			return response;
		}
	}
	
	public BaseResponse deleteCustomer(String id) {
		Customer customer = customerRepo.findById(id);
		
		if (customer == null) {
			BaseResponse response = new BaseResponse();
			response.setCode(ResponseEnum.notFound.getCode());
			response.setStatus(ResponseEnum.notFound.getStatus());
			response.setMessage("There's no data with id '" + id + "'.");
			
			// write log here
			return response;
		} else {
			customerRepo.deleteById(id);
			
			BaseResponse response = new BaseResponse();
			response.setCode(ResponseEnum.deleteSuccess.getCode());
			response.setStatus(ResponseEnum.deleteSuccess.getStatus());
			response.setMessage(ResponseEnum.deleteSuccess.getMessage());
			
			// write log here
			return response;
		}
	}
	
}

package com.id.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.id.dto.CustomerDto;
import com.id.models.Customer;

@Repository
public class CustomerRepo {

	private List<Customer> customers = new ArrayList<>();
	
	public CustomerRepo() {
		// Set dummy data
		customers.add(new Customer("cust001", "alexander", "male", "new york, usa", "unemployment"));
		customers.add(new Customer("cust002", "iqbal", "female", "jambi, indonesia", "front end"));
		customers.add(new Customer("cust003", "maria", "female", "tokyo, japan", "actor"));
		customers.add(new Customer("cust004", "sugiono", "male", "jember, indonesia", "artist"));
		customers.add(new Customer("cust005", "jaidi", "male", "kuala lumpur, malaysia", "commedian"));
		customers.add(new Customer("cust006", "apredo", "male", "aceh, indonesia", "fullstack developer"));
		customers.add(new Customer("cust007", "anwar", "male", "bogor, indonesia", "fraudster"));
		customers.add(new Customer("cust008", "rizal", "male", "medan, indonesia", "back end developer"));
		customers.add(new Customer("cust009", "nami", "femal", "swedia", "navigator"));
		customers.add(new Customer("cust010", "robin", "female", "saudi arabia", "archeolog"));
		customers.add(new Customer("cust011", "chopper", "male", "swiss", "doctor"));
		customers.add(new Customer("cust012", "sanji", "male", "france", "chef"));
		customers.add(new Customer("cust013", "zoro", "male", "japan", "swordman"));
		customers.add(new Customer("cust014", "usopp", "male", "brazil", "snipper"));
		customers.add(new Customer("cust015", "brook", "male", "italy", "musician"));
	}
	
	public List<Customer> findAll() {
		return customers;
	}
	
	public Customer findById(String id) {
		return customers.stream()
				.filter(customer -> customer.getCustId().equals(id))
				.findFirst()
				.orElse(null);
	}
	
	public Customer findByName(String name) {
		return customers.stream()
				.filter(customer -> customer.getName().equals(name))
				.findFirst()
				.orElse(null);
	}
	
	public Customer addCustomer(CustomerDto customerDto) {
		Customer customer = new Customer();
		customer.setCustId(UUID.randomUUID().toString());
		customer.setName(customerDto.getName());
		customer.setGender(customerDto.getGender());
		customer.setAddress(customerDto.getAddress());
		customer.setOccupation(customerDto.getOccupation());
		customers.add(customer);
		
		return customer;
	}
	
	public Customer updateCustomer(String id, CustomerDto customerDto) {
		Customer customer =  customers.stream()
									  .filter(c -> c.getCustId().equals(id))
									  .findFirst()
									  .orElse(null);
		
		if (customer == null) {
			return customer;
		} else {
			customer.setName(customerDto.getName());
			customer.setGender(customerDto.getGender());
			customer.setAddress(customerDto.getAddress());
			customer.setOccupation(customerDto.getOccupation());
			
			return customer;
		}
	}
	
	public void deleteById(String id) {
		customers.removeIf(customer -> customer.getCustId().equals(id));
	}
	
}

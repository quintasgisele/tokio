package com.example.api.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;



@Service
public class CustomerService {

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public List<Customer> findAll() {
		return repository.findAllByOrderByNameAsc();
	}

	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}
	
	public void cadastrarCliente(@Valid Customer customer) {
		repository.save(customer);
	}
	
	public void alterarCliente (@Valid Customer customer) {
		Optional<Customer> customerEntity = repository.findById(customer.getId());
		
		if (customerEntity.isPresent()) {
			customerEntity.get().setName(customer.getName());
			customerEntity.get().setEmail(customer.getEmail());
			repository.save(customerEntity.get());
		}
	}
	
	public Customer buscarCustomer(long id) {
		throw new IllegalArgumentException("Identificador inválido:" + id);
	}
	
	public void apagarCustomer(@Valid Customer customer) {
		repository.delete(customer);
	}

}

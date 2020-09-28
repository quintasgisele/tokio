package com.example.api.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;


@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@GetMapping
	public List<Customer> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}
	
	@GetMapping("incluir")
	public String incluirCliente(Customer customer) {
		return "cadastrar-cliente";
	}
	
	@GetMapping("editar/{id}")
	public String alterarCliente(long id, Model model) {
		Customer customer = service.buscarCustomer(id);
		model.addAttribute("cliente", customer);
		return "alterar-cliente";
	}
	
	@GetMapping("excluir/{id}")
	public String excluirCliente(@PathVariable("id") long id, Model model) {
		Customer customer = service.buscarCustomer(id);
		service.apagarCustomer(customer);
		
		model.addAttribute("clientes", service.findAll());
		return "index";
	}
	
}

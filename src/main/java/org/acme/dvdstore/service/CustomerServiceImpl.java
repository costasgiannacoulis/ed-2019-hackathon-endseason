package org.acme.dvdstore.service;

import org.acme.dvdstore.model.Customer;
import org.acme.dvdstore.repository.CustomerRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl extends AbstractService<Customer> implements CustomerService {
	private final CustomerRepository customerRepository;

	@Override
	public JpaRepository<Customer, Long> getRepository() {
		return customerRepository;
	}
}

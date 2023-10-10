package org.example;

import org.example.clients.fraud.FraudCheckResponse;
import org.example.clients.fraud.FraudClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate, FraudClient fraudClient) {

	public void registerCustomer(CustomerRegistrationRequest request) {
		Customer customer = Customer.builder()
				.firstName(request.firstName())
				.lastName(request.lastName())
				.email(request.email())
				.build();
		customerRepository.saveAndFlush(customer);
		FraudCheckResponse response = fraudClient.isFraudster(customer.getId());
		if (response.isFraudster()) {
			throw new IllegalStateException("Customer is a fraudster!");
		}

	}

}

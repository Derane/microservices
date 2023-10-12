package org.example;

import org.example.clients.notification.NotificationRequest;
import org.example.clients.fraud.FraudCheckResponse;
import org.example.clients.fraud.FraudClient;
import org.example.clients.notification.NotificationClient;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository, FraudClient fraudClient,
							  NotificationClient notificationClient) {

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
		notificationClient.send(new NotificationRequest(customer.getId(), customer.getFirstName(),
				"Hello %s, thank you for registering!".formatted(customer.getFirstName())));


	}


}

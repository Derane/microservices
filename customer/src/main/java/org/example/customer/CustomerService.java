package org.example.customer;

import org.example.amqp.RabbitMQMessageProducer;
import org.example.clients.notification.NotificationRequest;
import org.example.clients.fraud.FraudCheckResponse;
import org.example.clients.fraud.FraudClient;
import org.example.clients.notification.NotificationClient;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository, FraudClient fraudClient,
							   RabbitMQMessageProducer rabbitMQMessageProducer) {

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
		NotificationRequest notificationRequest = new NotificationRequest(customer.getId(), customer.getFirstName(),
				"Hello %s, thank you for registering!".formatted(customer.getFirstName()));
		rabbitMQMessageProducer.publish(notificationRequest,
				"internal-exchange",
				"internal.notification.routing.key");


	}


}

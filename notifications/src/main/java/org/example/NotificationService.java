package org.example;

import lombok.RequiredArgsConstructor;
import org.example.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

	private final NotificationRepository notificationRepository;

	public void send(NotificationRequest request) {
		notificationRepository.save(
				Notification.builder()
						.customerId(request.toCustomerId())
						.message(request.message())
						.sentAt(LocalDateTime.now())
						.sender("notification service")
						.toCustomerEmail(request.toCustomerName() + "@example.com")
						.build()
		);
	}
}

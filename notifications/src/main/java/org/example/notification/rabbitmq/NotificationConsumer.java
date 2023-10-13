package org.example.notification.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.clients.notification.NotificationRequest;
import org.example.notification.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

	private final NotificationService notificationService;

	@RabbitListener(queues = "${rabbitmq.queue.notification}")
	public void consumer(NotificationRequest request) {
		log.info("New notification request: {}", request);
		notificationService.send(request);
	}
}

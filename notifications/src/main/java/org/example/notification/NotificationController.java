package org.example.notification;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.clients.notification.NotificationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/notification")
public class NotificationController {
	private final NotificationService notificationService;

	@PostMapping
	public void send(@RequestBody NotificationRequest request) {
		log.info("New notification request: {}", request);
		notificationService.send(request);
	}
}

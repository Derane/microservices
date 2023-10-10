package org.example.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("notifications")
public interface NotificationClient {
	@PostMapping("/api/v1/notification")
	void send(NotificationRequest request);
}

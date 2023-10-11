package org.example;

import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class NotificationConfig {
	@Value("${rabbitmq.exchanges.internal}")
	private String internalExchange;
	@Value("${rabbitmq.queue.notification}")
	private String notificationQueue;
	@Value("${rabbitmq.routing-keys.internal-notification}")
	private String notificationKey;

	@Bean
	public TopicExchange internalExchange() {
		return new TopicExchange(internalExchange);
	}

	@Bean
	public Queue notificationQueue() {
		return new Queue(notificationQueue);
	}

	@Bean
	public Binding internalToNotificationBinding() {
		return BindingBuilder.bind(notificationQueue())
				.to(internalExchange())
				.with(notificationKey);
	}
}

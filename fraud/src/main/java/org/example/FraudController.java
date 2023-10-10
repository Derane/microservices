package org.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.clients.fraud.FraudCheckResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fraud-check/")
@RequiredArgsConstructor
@Slf4j
public class FraudController {
	private final FraudCheckService fraudCheckService;

	@GetMapping("/{customerId}")
	public FraudCheckResponse isFraudster(@PathVariable Integer customerId) {
		boolean isFraudulentCustomer = fraudCheckService.isFraudulent(customerId);
		log.info("Customer {} is fraudulent: {}", customerId, isFraudulentCustomer);
		log.info("1");
		return new FraudCheckResponse(isFraudulentCustomer);
	}
}

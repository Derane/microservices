package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fraud-check")
@RequiredArgsConstructor
public class FraudController {
	private final FraudCheckService fraudCheckService;
	@GetMapping("{customerId}")
	public FraudCheckResponse isFraudster(@PathVariable Integer customerId) {
		boolean isFraudulentCustomer = fraudCheckService.isFraudulent(customerId);

		return new FraudCheckResponse(isFraudulentCustomer);
	}
}

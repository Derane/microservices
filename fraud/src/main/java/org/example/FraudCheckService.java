package org.example;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FraudCheckService {

	private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

	public boolean isFraudulent(Integer customerId) {
		fraudCheckHistoryRepository.save(
				FraudCheckHistory.builder()
						.customerId(customerId)
						.isFraudster(false)
						.createdAt(LocalDateTime.now())
						.build()
		);
		return false;
	}
}

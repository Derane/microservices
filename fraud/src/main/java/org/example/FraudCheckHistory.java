package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity
public class FraudCheckHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	private Integer customerId;

	private Boolean isFraudster;
	private LocalDateTime createdAt;
}

package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity
@Table(schema = "public", name = "fraud")
public class FraudCheckHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	@Column(name = "customer_id")
	private Integer customerId;
	@Column(name = "is_fraudster")
	private Boolean isFraudster;
	private LocalDateTime createdAt;
}

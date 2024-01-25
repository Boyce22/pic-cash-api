package br.com.piccash.api.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pic_transaction")
public class Transaction {
	
	@Id
	@Column(name = "pic_tra_cd_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "pic_tra_dt")
	private LocalDateTime timeOfTransaction;

	@ManyToOne
	@JoinColumn(name = "pic_tra_payer_cd_id", referencedColumnName = "pic_user_cd_id")
	private User payer;

	@ManyToOne
	@JoinColumn(name = "pic_tra_payee_cd_id", referencedColumnName = "pic_user_cd_id")
	private User payee;

	@Column(name = "pic_tra_nm_amount")
	private BigDecimal amount;
	
	@PrePersist
	void prePersist() {
		this.timeOfTransaction = LocalDateTime.now();
	}

	public Transaction createTransaction(@NotBlank BigDecimal value, User userSender, User userReceiver) {
		this.payer = userSender;
		this.payee = userReceiver;
		this.amount = value;
		return this;
	}
}

package br.com.piccash.api.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.piccash.api.dto.ClientRegisterDTO;
import br.com.piccash.api.dto.ClientUpdateDTO;
import br.com.piccash.api.dto.ShopkeeperRegisterDTO;
import br.com.piccash.api.dto.ShopkeeperUpdateDTO;
import br.com.piccash.api.enuns.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pic_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pic_user_cd_id")
	private Long id;

	@Column(name = "pic_user_tx_name")
	private String nome;

	@Column(name = "pic_user_tx_last_name")
	private String sobreNome;

	@Column(name = "pic_user_tx_email", unique = true, nullable = false)
	private String email;

	@Column(name = "pic_user_tx_cpf", unique = true)
	private String cpf;

	@Column(name = "pic_user_tx_cnpj", unique = true)
	private String cnpj;

	@Column(name = "pic_user_nm_balance")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private BigDecimal saldo;

	@Column(name = "pic_user_dt_registration ")
	private LocalDateTime dtCadastro;

	@Column(name = "pic_user_dt_change ")
	private LocalDateTime dtAlteracao;

	@Enumerated(EnumType.STRING)
	@Column(name = "pic_user_type")
	private UserType tipo;

	public User registerClient(ClientRegisterDTO dto) {
		this.cpf = (dto.cpf());
		this.email = (dto.email());
		this.nome = (dto.nome());
		this.sobreNome = (dto.sobreNome());
		this.tipo = UserType.CLIENTE;
		return this;
	}

	public User registerShopkeeper(ShopkeeperRegisterDTO dto) {
		this.cnpj = (dto.cnpj());
		this.email = (dto.email());
		this.nome = (dto.nome());
		this.sobreNome = (dto.sobreNome());
		this.tipo = UserType.LOJISTA;
		return this;
	}

	public User clientUpdate(ClientUpdateDTO dto) {
		this.cpf = (dto.cpf());
		this.email = (dto.email());
		this.nome = (dto.nome());
		this.sobreNome = (dto.sobreNome());
		this.dtAlteracao = LocalDateTime.now();
		return this;
	}

	public User shopkeeperUpdate(ShopkeeperUpdateDTO dto) {
		this.cnpj = (dto.cnpj());
		this.email = (dto.email());
		this.nome = (dto.nome());
		this.sobreNome = (dto.sobreNome());
		this.dtAlteracao = LocalDateTime.now();
		return this;
	}

	@PrePersist
	public void prePersist() {
		this.dtCadastro = LocalDateTime.now();
	}

	public void transfer(User userReceiver, BigDecimal value) {
		this.saldo = saldo.subtract(value);
		userReceiver.saldo = saldo.add(value);
	}

	public User depositar(BigDecimal value) {
		this.saldo = saldo != null ? saldo.add(value) : value;
		return this;
	}

}

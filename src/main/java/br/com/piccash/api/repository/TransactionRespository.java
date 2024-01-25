package br.com.piccash.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.piccash.api.entity.Transaction;

public interface TransactionRespository extends JpaRepository<Transaction, Long> {

}

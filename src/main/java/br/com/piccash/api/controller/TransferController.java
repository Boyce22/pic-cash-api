package br.com.piccash.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.piccash.api.dto.TransactionDTO;
import br.com.piccash.api.service.TransferService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/transferencia")
public class TransferController {

	@Autowired
	private TransferService transferService;

	@PostMapping
	@Transactional
	public ResponseEntity<String> createTransaction(@RequestBody TransactionDTO transactionDTO) {
		return ResponseEntity.ok(transferService.createTransaction(transactionDTO));
	}

}

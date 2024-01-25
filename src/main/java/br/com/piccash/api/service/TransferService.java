package br.com.piccash.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.piccash.api.dto.TransactionDTO;
import br.com.piccash.api.entity.Transaction;
import br.com.piccash.api.entity.User;
import br.com.piccash.api.repository.TransactionRespository;
import jakarta.transaction.Transactional;

@Service
public class TransferService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionRespository transactionRepository;
	
	@Autowired
	private TransferAuthorizerService authorizerService;
	
	@Autowired
	private NotificationService notificationService;

	@Transactional
	public String createTransaction(TransactionDTO transactionDTO) {
		User userSender = userService.findUserById(transactionDTO.payerID());
		User userReceiver = userService.findUserById(transactionDTO.payeeID());

		userService.verifyUser(userSender, transactionDTO.value());

		if (authorizerService.transferAuthorizer()) {
			userSender.transfer(userReceiver, transactionDTO.value());
			transactionRepository.save(new Transaction().createTransaction(transactionDTO.value(), userSender, userReceiver));
			return notificationService.sendNotification(userReceiver);
		}
		return "Transação não realizada";
	}
	
}

package br.com.piccash.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.piccash.api.service.TransferService;

@RestController
@RequestMapping
public class TransferController {
	
	@Autowired
	private TransferService transferService;

}

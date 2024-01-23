package br.com.piccash.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.piccash.api.dto.ClientDetailsDTO;
import br.com.piccash.api.dto.ClientRegisterDTO;
import br.com.piccash.api.dto.ClientUpdateDTO;
import br.com.piccash.api.dto.ShopkeeperDetailsDTO;
import br.com.piccash.api.dto.ShopkeeperRegisterDTO;
import br.com.piccash.api.dto.ShopkeeperUpdateDTO;
import br.com.piccash.api.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UserController {
	
	@Autowired
	private UserService usuarioService;
	
	@GetMapping("/cliente/{id}")
	public ResponseEntity<ClientDetailsDTO> findShopkeeperById(@PathVariable Long id) {
		return ResponseEntity.ok(usuarioService.findClientById(id));
	}
	
	@GetMapping("/lojistas")
	public ResponseEntity<List<ShopkeeperDetailsDTO>> findAllShopkeepers() {
		List<ShopkeeperDetailsDTO> result = usuarioService.findAllShopkeepers();
		return result.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
	}
	
	@GetMapping("/lojista/{id}")
	public ResponseEntity<ShopkeeperDetailsDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(usuarioService.findShopkeeperById(id));
	}
	
	@PostMapping("/registrar-cliente")
	public ResponseEntity<ClientDetailsDTO> registerClient(@RequestBody @Valid ClientRegisterDTO dto, UriComponentsBuilder uriBuilder) {
		ClientDetailsDTO usuario = usuarioService.registerClient(dto);
	    return ResponseEntity.created(uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.id()).toUri())
	            .body(usuario);
	}
	
	@PostMapping("/registrar-lojista")
	public ResponseEntity<ShopkeeperDetailsDTO> reisterShopkeeper(@RequestBody @Valid ShopkeeperRegisterDTO dto, UriComponentsBuilder uriBuilder) {
		ShopkeeperDetailsDTO usuario = usuarioService.registerShopkeeper(dto);
	    return ResponseEntity.created(uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.id()).toUri())
	            .body(usuario);
	}
	
	@PutMapping("/cliente/{id}")
	public ResponseEntity<ClientDetailsDTO> updateCliente(@PathVariable Long id, @RequestBody @Valid ClientUpdateDTO dto) {
		return ResponseEntity.ok(usuarioService.updateClient(id, dto));
	}
	
	@PutMapping("/lojista/{id}")
	public ResponseEntity<ShopkeeperDetailsDTO> updateShopkeeper(@PathVariable Long id, @RequestBody @Valid ShopkeeperUpdateDTO dto) {
		return ResponseEntity.ok(usuarioService.updateShopkeeper(id, dto));
	}
	
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
		usuarioService.deleteClientById(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/lojista/{id}")
	public ResponseEntity<Void> deleteShopkeeper(@PathVariable Long id) {
		usuarioService.deleteShopkeeperById(id);
		return ResponseEntity.noContent().build();
	}
	
}

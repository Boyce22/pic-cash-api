package br.com.piccash.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.piccash.api.dto.ClientDetailsDTO;
import br.com.piccash.api.dto.ClientRegisterDTO;
import br.com.piccash.api.dto.ClientUpdateDTO;
import br.com.piccash.api.dto.ShopkeeperDetailsDTO;
import br.com.piccash.api.dto.ShopkeeperRegisterDTO;
import br.com.piccash.api.dto.ShopkeeperUpdateDTO;
import br.com.piccash.api.entity.User;
import br.com.piccash.api.enuns.UserType;
import br.com.piccash.api.repository.UserRepository;
import jakarta.validation.Valid;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public ClientDetailsDTO findClientById(Long id) {
		return new ClientDetailsDTO(findClient(id));
	}
	
	public ShopkeeperDetailsDTO findShopkeeperById(Long id) {
		return new ShopkeeperDetailsDTO(findShopkeeper(id));
	}

	public ClientDetailsDTO registerClient(ClientRegisterDTO dto) {
		return new ClientDetailsDTO(userRepository.save(new User().registerClient(dto)));
	}
	
	public ShopkeeperDetailsDTO registerShopkeeper(ShopkeeperRegisterDTO dto) {
		return new ShopkeeperDetailsDTO(userRepository.save(new User().registerShopkeeper(dto)));
	}
	
	public List<ShopkeeperDetailsDTO> findAllShopkeepers() {
		return userRepository.findAll().stream().filter(user -> user.getTipo() == UserType.LOJISTA).map(ShopkeeperDetailsDTO::new).toList();
	}

	public ClientDetailsDTO updateClient(Long id, @Valid ClientUpdateDTO dto) {
		return new ClientDetailsDTO(userRepository.save(findClient(id).clientUpdate(dto)));
	}

	public ShopkeeperDetailsDTO updateShopkeeper(Long id, @Valid ShopkeeperUpdateDTO dto) {
		return new ShopkeeperDetailsDTO(userRepository.save(findShopkeeper(id).shopkeeperUpdate(dto)));
	}

	public void deleteClientById(Long id) {
		userRepository.delete(findClient(id));
	}
	
	public void deleteShopkeeperById(Long id) {
		userRepository.delete(findShopkeeper(id));
	}
	
	private User findClient(Long id) {
		return userRepository.findByClientId(id)
					.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
	}
	
	private User findShopkeeper(Long id) {
		return userRepository.findByShopkeeperId(id)
					.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
	}

}

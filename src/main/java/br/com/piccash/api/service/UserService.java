package br.com.piccash.api.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.piccash.api.dto.ClientDetailsDTO;
import br.com.piccash.api.dto.ClientRegisterDTO;
import br.com.piccash.api.dto.ClientUpdateDTO;
import br.com.piccash.api.dto.DepositDTO;
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
	
	public String depositar(DepositDTO dto) {
		userRepository.save(findUserById(dto.id()).depositar(dto.value()));
		return "Valor depositado";
	}
	
	public ClientDetailsDTO findClientById(Long id) {
		return new ClientDetailsDTO(findClient(id));
	}
	
	public ShopkeeperDetailsDTO findShopkeeperById(Long id) {
		return new ShopkeeperDetailsDTO(findShopkeeper(id));
	}

	public ClientDetailsDTO registerClient(ClientRegisterDTO dto) {
		return new ClientDetailsDTO(userRepository.save(new User().registerClient(dto)));
	}
	
	public List<ClientDetailsDTO> findAllClient(){
		return userRepository.findAllClient().stream().map(ClientDetailsDTO::new).toList();
	}
	
	public ShopkeeperDetailsDTO registerShopkeeper(ShopkeeperRegisterDTO dto) {
		return new ShopkeeperDetailsDTO(userRepository.save(new User().registerShopkeeper(dto)));
	}
	
	public List<ShopkeeperDetailsDTO> findAllShopkeepers() {
		return userRepository.findAllShopkeeper().stream().map(ShopkeeperDetailsDTO::new).toList();
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
	
	public User findUserById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
	}

	public boolean verifyUser(User user) {
		return (user.getTipo().equals(UserType.CLIENTE));
	}
	
	public void verifyUser(User user, BigDecimal value) {
		if (!verifyUser(user)) {
			throw new RuntimeException(
					"Sinto muito, o seu cadastro está como lojista, não é possível realizar transferências");
		}

		if (user.getSaldo() == null || user.getSaldo().compareTo(value) < 0) {
			throw new RuntimeException("Não há saldo suficiente para realizar a transferência");
		}
	}

}

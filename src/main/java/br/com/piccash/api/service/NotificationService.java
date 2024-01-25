package br.com.piccash.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.piccash.api.dto.NotificationRequestDTO;
import br.com.piccash.api.entity.User;

@Service
public class NotificationService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${piccash.notification.url}")
	String url;
	
	private static final String MESSAGE = "Transferência concluída";

	public String sendNotification(User user) {

		NotificationRequestDTO notificationRequestDTO = new NotificationRequestDTO(user.getEmail(), MESSAGE);

		ResponseEntity<String> notificationResponse = restTemplate.postForEntity(url, notificationRequestDTO,String.class);

		if (!notificationResponse.getStatusCode().equals(HttpStatus.OK)) {
			throw new RuntimeException("Serviço de notificação fora do ar!");
		} else {
			return notificationRequestDTO.message();
		}
	}

}

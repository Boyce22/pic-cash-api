package br.com.piccash.api.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransferAuthorizerService {

	@Value("${piccash.autorization.url}")
	String url;

	@Autowired
	private RestTemplate restTemplate;

	public boolean transferAuthorizer() {
	    ResponseEntity<Map>  authorizationResponse = restTemplate.getForEntity(url, Map.class);

	    if (authorizationResponse.getStatusCode().equals(HttpStatus.OK)) {
	        var responseBody = authorizationResponse.getBody();
	        return responseBody != null && ((String) responseBody.get("message")).equalsIgnoreCase("autorizado");
	    }
	    
	    return false;
	}


}

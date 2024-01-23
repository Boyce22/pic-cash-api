package br.com.piccash.api.service;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TransferAuthorizer {

	@Value("${piccash.autorization.url}")
	static String url;

	public boolean autorizarTransferencia() {
		try {
			URL endpoint = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();
			connection.setRequestMethod("GET");

			return connection.getResponseMessage() != null ? true : false;

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}

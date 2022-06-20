package br.com.fateczl.p3_siga2_client.consumer;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RelatorioFaltaConsumer {
public final String HTTP_URL = "http://localhost:8080/p3_siga2/siga/relatorio/faltas/";
	
	public InputStream getRelatorioFalta() throws IOException {
		String urlFinal = HTTP_URL;
		URL url = new URL(urlFinal);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		
		if(conn.getResponseCode() != 200) {
			throw new IOException("Erro Código HTTP: " + conn.getResponseCode());
		}
		InputStream inputStream = conn.getInputStream();
		return inputStream;
	}
}

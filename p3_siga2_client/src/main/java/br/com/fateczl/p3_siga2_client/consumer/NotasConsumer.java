package br.com.fateczl.p3_siga2_client.consumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.fateczl.p3_siga2_client.model.Notas;

public class NotasConsumer implements INotasConsumer {
	private final String HTTP_URL = "http://localhost:8080/p3_siga2/siga/notas/";
	@Override
	public List<Notas> listarNotas() throws IOException {
		String json = getNotasJson(null);
		List<Notas> listaNotas = converteJsonToListNotas(json);
		return listaNotas;
	}

	@Override
	public List<Notas> consultaNota(Notas n) throws IOException {
		String json = getNotasJson(n);
		List<Notas> nota = converteJsonToListNotas(json);
		
		return nota;
	}

	@Override
	public String iudNotas(Notas n, String operacao) throws IOException {
		if(n == null) {
			throw new IOException("Operação Invalida");
		}
		else {
			if(operacao.equals("Inserir")) {
				String retorno = postNotas(n,"insert");
				return retorno;
			}
			else if(operacao.equals("Alterar")) {
				String retorno = postNotas(n,"update");
				return retorno;
			}
			else {
				String retorno = postNotas(n,"delete");
				return retorno;
			}
		}
	}
	
	private String getNotasJson(Notas n) throws IOException {
		Charset charset = Charset.forName("UTF-8");
		String urlFinal = HTTP_URL;
		if(n != null) {
			urlFinal = urlFinal + n.getAluno().getRa();
		}
		URL url = new URL(urlFinal);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");
		
		if(conn.getResponseCode() != 200) {
			throw new IOException("Erro Código HTTP: " + conn.getResponseCode());
		}
		InputStream inputStream = conn.getInputStream();
		InputStreamReader isr = new InputStreamReader(inputStream,charset);
		BufferedReader br = new BufferedReader(isr);
		String saida = br.readLine();
		StringBuffer buffer = new StringBuffer();
		while(saida != null) {
			buffer.append(saida);
			saida = br.readLine();
		}
		br.close();
		isr.close();
		inputStream.close();
		return buffer.toString();
	}
	private String postNotas(Notas n, String operacao) throws IOException{
		URL url = new URL(HTTP_URL + operacao);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(20000);
		conn.setReadTimeout(20000);
		conn.setDoOutput(true);
		conn.setUseCaches(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Accept", "text/plain");
		
		OutputStream outputStream = (OutputStream) conn.getOutputStream();
		String json = converteNotasToJson(n);
		byte[] bytes = json.getBytes("UTF-8");
		outputStream.write(bytes);
		outputStream.flush();
		outputStream.close();
		
		InputStream inputStream = conn.getInputStream();
		byte[] bytesResponse = new byte[2048];
		StringBuffer bufferResponse =  new StringBuffer();
		int cont = 0;
		
		while((cont = inputStream.read(bytesResponse)) != -1) {
			bufferResponse.append(new String(bytesResponse, 0, cont));
		}
		inputStream.close();
		
		return bufferResponse.toString();
	}
	
	private String converteNotasToJson(Notas n) {
		Gson gson = new Gson();
		String json = gson.toJson(n);
		
		return json;
	}
	
	private List<Notas> converteJsonToListNotas(String json){
		List<Notas> listaNotas = new ArrayList<Notas>();
		Gson gson = new Gson();
		
		TypeToken<List<Notas>> token = new TypeToken<List<Notas>>(){};
		Type tipo = token.getType();
		listaNotas = gson.fromJson(json, tipo);
		
		return listaNotas;
	}
}

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

import br.com.fateczl.p3_siga2_client.model.Falta;

public class FaltaConsumer implements IFaltaConsumer {
	private final String HTTP_URL = "http://localhost:8080/p3_siga2/siga/falta/";

	public List<Falta> listarFaltas() throws IOException {
		String json = getFaltaJson(null);
		List<Falta> listaFalta = converteJsonToListFalta(json);
		return listaFalta;
	}
	public List<Falta> consultaFalta(Falta f) throws IOException {
		String json = getFaltaJson(f);
		List<Falta> falta = converteJsonToListFalta(json);

		return falta;
	}

	public String iudFalta(Falta f, String operacao) throws IOException {
		if(f == null) {
			throw new IOException("Operação Invalida");
		}
		else {
			if(operacao.equals("Inserir")) {
				String retorno = postFalta(f,"insert");
				return retorno;
			}
			else if(operacao.equals("Alterar")) {
				String retorno = postFalta(f,"update");
				return retorno;
			}
			else {
				String retorno = postFalta(f,"delete");
				return retorno;
			}
		}
	}

	private String getFaltaJson(Falta f) throws IOException {
		Charset charset = Charset.forName("UTF-8");
		String urlFinal = HTTP_URL;
		if(f != null) {
			urlFinal = urlFinal + f.getAluno().getRa();
		}
		URL url = new URL(urlFinal);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if(conn.getResponseCode() != 200) {
			throw new IOException("Erro Código HTTP: " + conn.getResponseCode());
		}
		InputStream inputStream = conn.getInputStream();
		InputStreamReader isr =new InputStreamReader(inputStream,charset);
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
	private String postFalta(Falta f, String operacao) throws IOException{
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
		String json = converteFaltaToJson(f);
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
	
	private String converteFaltaToJson(Falta f) {
		Gson gson = new Gson();
		String json = gson.toJson(f);

		return json;
	}

	private List<Falta> converteJsonToListFalta(String json){
		List<Falta> listaFalta = new ArrayList<Falta>();
		Gson gson = new Gson();

		TypeToken<List<Falta>> token = new TypeToken<List<Falta>>(){};
		Type tipo = token.getType();
		listaFalta = gson.fromJson(json, tipo);

		return listaFalta;
	}
}

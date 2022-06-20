package br.com.fateczl.p3_siga2_client.consumer;

import java.io.IOException;
import java.util.List;

import br.com.fateczl.p3_siga2_client.model.Notas;

public interface INotasConsumer {
	public List<Notas> listarNotas() throws IOException;
	public List<Notas> consultaNota(Notas n) throws IOException;
	public String iudNotas(Notas n, String operacao) throws IOException;
}

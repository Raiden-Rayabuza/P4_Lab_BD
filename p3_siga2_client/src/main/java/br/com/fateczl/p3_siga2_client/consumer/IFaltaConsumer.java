package br.com.fateczl.p3_siga2_client.consumer;

import java.io.IOException;
import java.util.List;

import br.com.fateczl.p3_siga2_client.model.Falta;

public interface IFaltaConsumer {
	public List<Falta> listarFaltas() throws IOException;
	public List<Falta> consultaFalta(Falta f) throws IOException;
	public String iudFalta(Falta f, String operacao) throws IOException;
}
package br.com.fateczl.p3_siga2.controller;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import br.com.fateczl.p3_siga2.dto.FaltaDTO;
import br.com.fateczl.p3_siga2.model.Falta;

public interface IFaltaController{
    public List<FaltaDTO> listarFaltas();
	public List<FaltaDTO> consultarFalta(String ra) throws ResourceNotFoundException;
	public ResponseEntity<String> insereFalta(Falta aluno);
	public ResponseEntity<String> atualizaFalta(Falta aluno);
	public ResponseEntity<String> excluirFalta(Falta aluno);
}
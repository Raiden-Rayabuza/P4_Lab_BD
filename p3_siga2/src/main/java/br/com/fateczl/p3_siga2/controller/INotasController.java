package br.com.fateczl.p3_siga2.controller;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import br.com.fateczl.p3_siga2.dto.NotasDTO;
import br.com.fateczl.p3_siga2.model.Notas;

public interface INotasController {
	public List<NotasDTO> listarNotas();
	public List<NotasDTO> consultarNotas(String ra) throws ResourceNotFoundException;
	public ResponseEntity<String> insereNotas(Notas notas);
	public ResponseEntity<String> atualizaNotas(Notas notas);
	public ResponseEntity<String> excluirNotas(Notas notas);
}

package br.com.fateczl.p3_siga2.controller;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import br.com.fateczl.p3_siga2.dto.AvaliacaoDTO;
import br.com.fateczl.p3_siga2.model.Avaliacao;

public interface IAvaliacaoController {
    public List<AvaliacaoDTO> listarAvaliacoes();
	public ResponseEntity<AvaliacaoDTO> consultarAvaliacao(int cod_avaliacao) throws ResourceNotFoundException;
	public ResponseEntity<String> insereAvaliacao(Avaliacao avaliacao);
	public ResponseEntity<String> atualizaAvaliacao(Avaliacao avaliacao);
	public ResponseEntity<String> excluiAvaliacao(Avaliacao avaliacao);
}

package br.com.fateczl.p3_siga2.controller;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import br.com.fateczl.p3_siga2.dto.DisciplinaDTO;
import br.com.fateczl.p3_siga2.model.Disciplina;

public interface IDisciplinaController {
    public List<DisciplinaDTO> listarDisciplinas();
	public ResponseEntity<DisciplinaDTO> consultarDisciplina(String cod_disciplina) throws ResourceNotFoundException;
	public ResponseEntity<String> insereDisciplina(Disciplina disciplina);
	public ResponseEntity<String> atualizaDisciplina(Disciplina disciplina);
	public ResponseEntity<String> excluiDisciplina(Disciplina disciplina);
}

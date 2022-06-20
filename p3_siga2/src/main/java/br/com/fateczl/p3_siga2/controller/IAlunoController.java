package br.com.fateczl.p3_siga2.controller;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import br.com.fateczl.p3_siga2.dto.AlunoDTO;
import br.com.fateczl.p3_siga2.model.Aluno;

public interface IAlunoController {
    public List<AlunoDTO> listarAlunos();
	public ResponseEntity<AlunoDTO> consultarAluno(String ra) throws ResourceNotFoundException;
	public ResponseEntity<String> insereAluno(Aluno aluno);
	public ResponseEntity<String> atualizaAluno(Aluno aluno);
	public ResponseEntity<String> excluiAluno(Aluno aluno);
}

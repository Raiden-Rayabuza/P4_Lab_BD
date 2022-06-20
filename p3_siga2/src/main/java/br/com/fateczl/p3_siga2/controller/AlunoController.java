package br.com.fateczl.p3_siga2.controller;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fateczl.p3_siga2.dto.AlunoDTO;
import br.com.fateczl.p3_siga2.model.Aluno;
import br.com.fateczl.p3_siga2.repository.AlunoRepository;

@RestController
@RequestMapping("/siga")
public class AlunoController implements IAlunoController {

	@Autowired
	AlunoRepository aRep;
	
	@GetMapping("/aluno")
	public List<AlunoDTO> listarAlunos() {
		List<Aluno> alunos = aRep.findAll();
		List<AlunoDTO> alunosDTO = alunosToDTOS(alunos);
		return alunosDTO;
	}
	@GetMapping("/aluno/{ra}")
	public ResponseEntity<AlunoDTO> consultarAluno (@PathVariable(value = "ra") String ra) throws ResourceNotFoundException {
		Aluno aluno = aRep.findById(ra).orElseThrow(() -> new ResourceNotFoundException(ra + " invalido"));
		AlunoDTO alunoDTO = alunoToDTO(aluno);
		return ResponseEntity.ok().body(alunoDTO);
	}

	@PostMapping("/aluno")
	public ResponseEntity<String> insereAluno(
			@Valid @RequestBody Aluno aluno) {
		aRep.save(aluno);
		String saida = "Aluno inserido com sucesso";
		return ResponseEntity.ok().body(saida);
	}

	@PutMapping("/aluno")
	public ResponseEntity<String> atualizaAluno(
			@Valid @RequestBody Aluno aluno) {
		aRep.save(aluno);
		String saida = "Aluno atualizado com sucesso";
		return ResponseEntity.ok().body(saida);
	}

	@DeleteMapping("/aluno")
	public ResponseEntity<String> excluiAluno(
			@Valid @RequestBody Aluno aluno) {
		aRep.delete(aluno);
		String saida = "Aluno excluido com sucesso";
		return ResponseEntity.ok().body(saida);
	}

	public List<AlunoDTO> alunosToDTOS(List<Aluno> alunos) {
		List<AlunoDTO> alunosDTO = new ArrayList<AlunoDTO>();
		for (Aluno a : alunos) {
			AlunoDTO alunoDTO = new AlunoDTO();
			alunoDTO = alunoToDTO(a);
			
			alunosDTO.add(alunoDTO);
		}
		return alunosDTO;
	}

	public AlunoDTO alunoToDTO(Aluno aluno) {
		AlunoDTO alunoDTO = new AlunoDTO();
		alunoDTO.setRa(aluno.getRa());
		alunoDTO.setNome(aluno.getNome());
		
		return alunoDTO;
	}
	
}
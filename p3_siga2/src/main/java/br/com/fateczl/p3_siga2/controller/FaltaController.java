package br.com.fateczl.p3_siga2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fateczl.p3_siga2.dto.FaltaDTO;
import br.com.fateczl.p3_siga2.model.Falta;
import br.com.fateczl.p3_siga2.repository.FaltaRepository;

@RestController
@RequestMapping("/siga")
public class FaltaController implements IFaltaController {
	@Autowired
	FaltaRepository fRep;

	@PostMapping("/falta/update")
	public ResponseEntity<String> atualizaFalta(@Valid @RequestBody Falta falta) {
		fRep.alterarFalta(falta.getAluno().getRa(), falta.getDisciplina().getCod_disciplina(), falta.getData(), falta.getPresenca(), falta.getAluno().getRa(), falta.getDisciplina().getCod_disciplina());
		String saida = "Chamada atualizada com sucesso";
		return ResponseEntity.ok().body(saida);
	}

	@GetMapping("/falta/{ra}")
	public List<FaltaDTO> consultarFalta(@PathVariable(value="ra")String ra) throws ResourceNotFoundException {
		List<Falta> falta = fRep.consultaFalta(ra);
		List<FaltaDTO> faltaDTO = faltasToDtos(falta);
		return faltaDTO;
	}

	@PostMapping("/falta/delete")
	public ResponseEntity<String> excluirFalta(@Valid @RequestBody Falta falta) {
		fRep.deletarFalta(falta.getAluno().getRa(), falta.getDisciplina().getCod_disciplina());;
		String saida = "Chamada deletada com sucesso";
		return ResponseEntity.ok().body(saida);
	}

	@PostMapping("/falta/insert")
	public ResponseEntity<String> insereFalta(@Valid @RequestBody Falta falta) {
		fRep.inserirFalta(falta.getAluno().getRa(), falta.getDisciplina().getCod_disciplina(), falta.getData(), falta.getPresenca());
		String saida = "Chamada inserida com sucesso";
		return ResponseEntity.ok().body(saida);
	}

	@GetMapping("/falta")
	public List<FaltaDTO> listarFaltas() {
		List<Falta> falta = fRep.findAll();
		List<FaltaDTO> faltaDTO = faltasToDtos(falta);
		return faltaDTO;
	}
	private List<FaltaDTO> faltasToDtos(List<Falta> faltas) {
		List<FaltaDTO> faltasDTO = new ArrayList<FaltaDTO>();
		for (Falta f : faltas) {
			FaltaDTO faltaDTO = new FaltaDTO();
			faltaDTO = faltaToDto(f);
			
			faltasDTO.add(faltaDTO);
		}
		return faltasDTO;
	}

	private FaltaDTO faltaToDto(Falta falta) {
		FaltaDTO faltaDTO = new FaltaDTO();
		AlunoController alunoCtrl = new AlunoController();
		DisciplinaController disciplinaCtrl = new DisciplinaController();
		faltaDTO.setAluno(alunoCtrl.alunoToDTO(falta.getAluno()));
		faltaDTO.setData(falta.getData());
		faltaDTO.setDisciplina(disciplinaCtrl.disciplinaToDTO(falta.getDisciplina()));
		faltaDTO.setPresenca(falta.getPresenca());
		
		return faltaDTO;
	}
}

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

import br.com.fateczl.p3_siga2.dto.DisciplinaDTO;
import br.com.fateczl.p3_siga2.model.Disciplina;
import br.com.fateczl.p3_siga2.repository.DisciplinaRepository;
@RestController
@RequestMapping("/siga")
public class DisciplinaController implements IDisciplinaController{
    @Autowired
    DisciplinaRepository dRep;

    @PutMapping("/disciplina")
    public ResponseEntity<String> atualizaDisciplina(@Valid @RequestBody Disciplina disciplina) {
        dRep.save(disciplina);   
        String saida = "Disciplina Atualizada com Sucesso"; 
        return ResponseEntity.ok().body(saida);
    }

    @GetMapping("/disciplina/{cod_disciplina}")
    public ResponseEntity<DisciplinaDTO> consultarDisciplina(@PathVariable(value="cod_disciplina")String cod_disciplina) throws ResourceNotFoundException {
        Disciplina disciplina = dRep.findById(cod_disciplina).orElseThrow(() -> new ResourceNotFoundException(cod_disciplina + " invalido"));
        DisciplinaDTO disciplinaDTO = disciplinaToDTO(disciplina);

        return ResponseEntity.ok().body(disciplinaDTO);
    }

    @DeleteMapping("/disciplina")
    public ResponseEntity<String> excluiDisciplina(@Valid @RequestBody Disciplina disciplina) {
        dRep.delete(disciplina);
        String saida = "Disciplina removida com sucesso";
        return ResponseEntity.ok().body(saida);
    }

    @PostMapping("/disciplina")
    public ResponseEntity<String> insereDisciplina(@Valid @RequestBody Disciplina disciplina) {
        dRep.save(disciplina);
        String saida = "Disciplina adicionada com sucesso";
        return ResponseEntity.ok().body(saida);
    }

    @GetMapping("/disciplina")
    public List<DisciplinaDTO> listarDisciplinas() {
        List<Disciplina> disciplinas = dRep.findAll();
        List<DisciplinaDTO> disciplinasDTO = disciplinasToDTOS(disciplinas); 
        return disciplinasDTO;
    }
    public List<DisciplinaDTO> disciplinasToDTOS(List<Disciplina> disciplinas) {
		List<DisciplinaDTO> disciplinasDTO = new ArrayList<DisciplinaDTO>();
		for (Disciplina d : disciplinas) {
			DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
			disciplinaDTO = disciplinaToDTO(d);
			
			disciplinasDTO.add(disciplinaDTO);
		}
		return disciplinasDTO;
	}

	public DisciplinaDTO disciplinaToDTO(Disciplina disciplina) {
		DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
		disciplinaDTO.setCod_disciplina(disciplina.getCod_disciplina());
		disciplinaDTO.setNome(disciplina.getNome());
		disciplinaDTO.setNum_aulas(disciplina.getNum_aulas());
        disciplinaDTO.setSigla(disciplina.getSigla());
        disciplinaDTO.setTurno(disciplina.getTurno());
		return disciplinaDTO;
	}
}
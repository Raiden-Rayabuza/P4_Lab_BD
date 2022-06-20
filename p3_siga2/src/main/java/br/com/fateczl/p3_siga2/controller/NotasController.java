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

import br.com.fateczl.p3_siga2.dto.NotasDTO;
import br.com.fateczl.p3_siga2.model.Notas;
import br.com.fateczl.p3_siga2.repository.NotasRepository;

@RestController
@RequestMapping("/siga")
public class NotasController implements INotasController {
    @Autowired
    NotasRepository nRep;

    @PostMapping("/notas/update")
    public ResponseEntity<String> atualizaNotas(@Valid @RequestBody Notas notas) {
        nRep.alterarNotas(notas.getAluno().getRa(), notas.getDisciplina().getCod_disciplina(), notas.getAvaliacao().getCod(), notas.getNota(), notas.getAluno().getRa(), notas.getDisciplina().getCod_disciplina(), notas.getAvaliacao().getCod());
        String saida = "Notas alteradas com sucesso";
        return ResponseEntity.ok().body(saida);
    }

    @GetMapping("/notas/{ra}")
    public List<NotasDTO> consultarNotas(@PathVariable(value="ra")String ra) throws ResourceNotFoundException {
        List<Notas> nota = nRep.consultaNotas(ra);
        List<NotasDTO> notaDTO = notasToDtos(nota);
        return notaDTO;
    }

    @PostMapping("/notas/delete")
    public ResponseEntity<String> excluirNotas(@Valid @RequestBody Notas notas) {
        nRep.deletarNotas(notas.getAluno().getRa(), notas.getDisciplina().getCod_disciplina(), notas.getAvaliacao().getCod());
        String saida = "Notas excluida com sucesso";
        return ResponseEntity.ok().body(saida);
    }

    @PostMapping("/notas/insert")
    public ResponseEntity<String> insereNotas(@Valid @RequestBody Notas notas) {
        nRep.inserirNotas(notas.getAluno().getRa(), notas.getDisciplina().getCod_disciplina(), notas.getAvaliacao().getCod(), notas.getNota());
        String saida = "Notas inseridas com sucesso";
        return ResponseEntity.ok().body(saida);
    }

    @GetMapping("/notas")
    public List<NotasDTO> listarNotas() {
        List<Notas> notas = nRep.findAll();
        List<NotasDTO> notasDTO = notasToDtos(notas);
        return notasDTO;
    }

    private List<NotasDTO> notasToDtos(List<Notas> notas) {
		List<NotasDTO> notasDTO = new ArrayList<NotasDTO>();
		for (Notas n : notas) {
			NotasDTO notaDTO = new NotasDTO();
			notaDTO = notaToDto(n);
			notasDTO.add(notaDTO);
		}
		return notasDTO;
	}

	private NotasDTO notaToDto(Notas notas) {
		NotasDTO notasDTO = new NotasDTO();
        AlunoController alunoCtrl = new AlunoController();
        AvaliacaoController avaliacaoCtrl = new AvaliacaoController();
        DisciplinaController disciplinaCtrl = new DisciplinaController();
        notasDTO.setAluno(alunoCtrl.alunoToDTO(notas.getAluno()));
        notasDTO.setAvaliacao(avaliacaoCtrl.avaliacaoToDTO(notas.getAvaliacao()));
        notasDTO.setDisciplina(disciplinaCtrl.disciplinaToDTO(notas.getDisciplina()));
        notasDTO.setNota(notas.getNota());
		
		return notasDTO;
	}
}

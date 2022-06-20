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

import br.com.fateczl.p3_siga2.dto.AvaliacaoDTO;
import br.com.fateczl.p3_siga2.model.Avaliacao;
import br.com.fateczl.p3_siga2.repository.AvaliacaoRepository;

@RestController
@RequestMapping("/siga")
public class AvaliacaoController implements IAvaliacaoController {
    @Autowired
    AvaliacaoRepository aRep;

    @PutMapping("/avaliacao")
    public ResponseEntity<String> atualizaAvaliacao(@Valid @RequestBody Avaliacao avaliacao) {
        aRep.save(avaliacao);
        String saida = "Avaliação Atualizada com Sucesso";
        return ResponseEntity.ok().body(saida);
    }

    @GetMapping("/avaliacao/{cod_avaliacao}")
    public ResponseEntity<AvaliacaoDTO> consultarAvaliacao(@PathVariable(value="cod_avaliacao")int cod_avaliacao) throws ResourceNotFoundException {
        Avaliacao avaliacao = aRep.findById(cod_avaliacao).orElseThrow(() -> new ResourceNotFoundException(cod_avaliacao + " invalido"));
        AvaliacaoDTO avaliacaoDTO = avaliacaoToDTO(avaliacao);
        return ResponseEntity.ok().body(avaliacaoDTO);
    }

    @DeleteMapping("/avaliacao")
    public ResponseEntity<String> excluiAvaliacao(@Valid @RequestBody Avaliacao avaliacao) {
        aRep.delete(avaliacao);
        String saida = "Avaliacao removida com sucessso";
        return ResponseEntity.ok().body(saida);
    }

    @PostMapping("/avaliacao")
    public ResponseEntity<String> insereAvaliacao(@Valid @RequestBody Avaliacao avaliacao) {
        aRep.save(avaliacao);
        String saida = "Avaliacao Inserida com Sucesso";
        return ResponseEntity.ok().body(saida);
    }

    @GetMapping("/avaliacao")
    public List<AvaliacaoDTO> listarAvaliacoes() {
        List<Avaliacao> avaliacao = aRep.findAll();
        List<AvaliacaoDTO> avaliacaoDTO = avaliacaoToDTOS(avaliacao);
        return avaliacaoDTO;
    }

    public List<AvaliacaoDTO> avaliacaoToDTOS(List<Avaliacao> avaliacoes) {
		List<AvaliacaoDTO> avaliacoesDTO = new ArrayList<AvaliacaoDTO>();
		for (Avaliacao a : avaliacoes) {
			AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
			avaliacaoDTO = avaliacaoToDTO(a);
			
			avaliacoesDTO.add(avaliacaoDTO);
		}
		return avaliacoesDTO;
	}

	public AvaliacaoDTO avaliacaoToDTO(Avaliacao avaliacao) {
		AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
		avaliacaoDTO.setCod(avaliacao.getCod());
		avaliacaoDTO.setTipo(avaliacao.getTipo());
		
		return avaliacaoDTO;
	}
}

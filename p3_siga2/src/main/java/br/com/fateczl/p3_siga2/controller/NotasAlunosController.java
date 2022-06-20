package br.com.fateczl.p3_siga2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fateczl.p3_siga2.dto.AlunoDTO;
import br.com.fateczl.p3_siga2.dto.AvaliacaoDTO;
import br.com.fateczl.p3_siga2.dto.DisciplinaDTO;
import br.com.fateczl.p3_siga2.dto.NotasDTO;
import br.com.fateczl.p3_siga2.model.NotasAlunos;
import br.com.fateczl.p3_siga2.repository.NotasAlunosRepository;

@RestController
@RequestMapping("/siga")
public class NotasAlunosController {
    @Autowired
    NotasAlunosRepository naRep;
    @GetMapping("/notas/alunos")
    public List<NotasDTO> udf_notas_alunos(){
        List<NotasAlunos> notasAlunos = naRep.udf_notas_alunos();
        List<NotasDTO> notasAlunosDTO = notasAlunosToDtos(notasAlunos);
        return notasAlunosDTO;
    }

    private List<NotasDTO> notasAlunosToDtos(List<NotasAlunos> notasAlunos){
        List<NotasDTO> listaNotas = new ArrayList<NotasDTO>();
        for(NotasAlunos na : notasAlunos){
            NotasDTO notasDTO = new NotasDTO();
            AlunoDTO alunoDTO = new AlunoDTO();
            AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();
            DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
            alunoDTO.setNome(na.getNome_aluno());
            alunoDTO.setRa(na.getRa());
            avaliacaoDTO.setTipo(na.getTipo_avaliacao());
            disciplinaDTO.setNome(na.getNome_disciplina());
            notasDTO.setAluno(alunoDTO);
            notasDTO.setAvaliacao(avaliacaoDTO);
            notasDTO.setDisciplina(disciplinaDTO);
            notasDTO.setNota(na.getNota());

            listaNotas.add(notasDTO);
        }
        
        return listaNotas;
    }
}

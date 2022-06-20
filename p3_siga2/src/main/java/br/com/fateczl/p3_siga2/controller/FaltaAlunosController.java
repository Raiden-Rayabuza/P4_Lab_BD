package br.com.fateczl.p3_siga2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fateczl.p3_siga2.dto.AlunoDTO;
import br.com.fateczl.p3_siga2.dto.DisciplinaDTO;
import br.com.fateczl.p3_siga2.dto.FaltaDTO;
import br.com.fateczl.p3_siga2.model.FaltaAlunos;
import br.com.fateczl.p3_siga2.repository.FaltaAlunosRepository;

@RestController
@RequestMapping("/siga")
public class FaltaAlunosController {
    @Autowired
    FaltaAlunosRepository faRep;
    @GetMapping("/falta/aluno")
    public List<FaltaDTO> udf_faltas_alunos(){
        List<FaltaAlunos> faltaAlunos =  faRep.udf_faltas_alunos();
        List<FaltaDTO> faltaAlunosDTO = faltaAlunosToDtos(faltaAlunos);
        return faltaAlunosDTO;
    }
    
    private List<FaltaDTO> faltaAlunosToDtos(List<FaltaAlunos> faltaAlunos){
        List<FaltaDTO> listaFaltas = new ArrayList<FaltaDTO>();
        for(FaltaAlunos fa : faltaAlunos){
            FaltaDTO faltaDTO = new FaltaDTO();
            AlunoDTO alunoDTO = new AlunoDTO();
            DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
            alunoDTO.setNome(fa.getNome_aluno());
            alunoDTO.setRa(fa.getRa());
            disciplinaDTO.setNome(fa.getNome_disciplina());
            faltaDTO.setAluno(alunoDTO);
            faltaDTO.setDisciplina(disciplinaDTO);
            faltaDTO.setData(fa.getData_falta());
            faltaDTO.setPresenca(fa.getFalta());

            listaFaltas.add(faltaDTO);
        }
        
        return listaFaltas;
    }
}

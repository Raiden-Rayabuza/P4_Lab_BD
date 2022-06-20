package br.com.fateczl.p3_siga2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fateczl.p3_siga2.model.NotasAlunos;

public interface NotasAlunosRepository extends JpaRepository<NotasAlunos,String>{
    
    List<NotasAlunos> udf_notas_alunos();
}

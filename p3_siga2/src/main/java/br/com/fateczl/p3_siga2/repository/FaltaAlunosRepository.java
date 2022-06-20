package br.com.fateczl.p3_siga2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fateczl.p3_siga2.model.FaltaAlunos;
public interface FaltaAlunosRepository extends JpaRepository<FaltaAlunos,String> {
	
    List<FaltaAlunos> udf_faltas_alunos();
}

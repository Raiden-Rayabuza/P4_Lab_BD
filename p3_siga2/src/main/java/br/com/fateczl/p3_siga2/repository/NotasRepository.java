package br.com.fateczl.p3_siga2.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fateczl.p3_siga2.model.Notas;
import br.com.fateczl.p3_siga2.model.NotasPK;

public interface NotasRepository extends JpaRepository<Notas,NotasPK>{
	@Query(value = "INSERT INTO notas(ra,cod_disciplina,cod_avaliacao,nota) VALUES(:ra,:cod_disciplina,:cod_avaliacao,:nota)", nativeQuery = true)
	@Modifying
	@Transactional
    void inserirNotas(@Param("ra")String ra, @Param("cod_disciplina")String disciplina, @Param("cod_avaliacao")int avaliacao, @Param("nota")int nota);
	
	@Query(value = "UPDATE notas SET ra = :ra, cod_disciplina = :cod_disciplina, cod_avaliacao = :cod_avaliacao, nota = :nota WHERE ra = :ra_notas AND cod_disciplina = :cod_disciplina_notas AND cod_avaliacao = :cod_avaliacao_notas", nativeQuery = true)
	@Modifying
	@Transactional
    void alterarNotas(@Param("ra")String ra, @Param("cod_disciplina")String disciplina, @Param("cod_avaliacao")int avaliacao, @Param("nota")int nota, @Param("ra_notas")String ra_notas, @Param("cod_disciplina_notas")String disciplina_notas, @Param("cod_avaliacao_notas")int avaliacao_notas);
	
	@Query(value = "DELETE FROM notas WHERE ra = :ra AND cod_disciplina = :cod_disciplina AND cod_avaliacao = :cod_avaliacao", nativeQuery = true)
	@Modifying
	@Transactional
    void deletarNotas(@Param("ra")String ra, @Param("cod_disciplina")String disciplina, @Param("cod_avaliacao")int avaliacao);
	
	@Query(value = "SELECT * FROM notas WHERE ra = :ra", nativeQuery = true)
    List<Notas> consultaNotas(@Param("ra")String ra);
}

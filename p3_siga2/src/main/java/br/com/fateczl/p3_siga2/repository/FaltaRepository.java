package br.com.fateczl.p3_siga2.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fateczl.p3_siga2.model.Falta;

public interface FaltaRepository extends JpaRepository<Falta,String> {
	@Query(value = "INSERT INTO falta(ra,cod_disciplina,data_falta,presenca) VALUES(:ra,:cod_disciplina,:data_falta,:presenca)", nativeQuery = true)
	@Modifying
	@Transactional
    void inserirFalta(@Param("ra")String ra, @Param("cod_disciplina")String disciplina, @Param("data_falta")String data, @Param("presenca")String presenta);
	
	@Query(value = "UPDATE falta SET ra = :ra, cod_disciplina = :cod_disciplina, data_falta = :data_falta, presenca = :presenca WHERE ra = :ra_falta AND cod_disciplina = :cod_disciplina_falta", nativeQuery = true)
	@Modifying
	@Transactional
    void alterarFalta(@Param("ra")String ra, @Param("cod_disciplina")String disciplina, @Param("data_falta")String data, @Param("presenca")String presenta, @Param("ra_falta")String ra_falta, @Param("cod_disciplina_falta")String disciplina_falta);
	
	@Query(value = "DELETE FROM falta WHERE ra = :ra AND cod_disciplina = :cod_disciplina", nativeQuery = true)
	@Modifying
	@Transactional
    void deletarFalta(@Param("ra")String ra, @Param("cod_disciplina")String disciplina);
	
	@Query(value = "SELECT * FROM falta WHERE ra = :ra", nativeQuery = true)
    List<Falta> consultaFalta(@Param("ra")String ra);
}

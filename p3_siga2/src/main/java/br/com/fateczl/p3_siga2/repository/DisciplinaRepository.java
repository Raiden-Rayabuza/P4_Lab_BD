package br.com.fateczl.p3_siga2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fateczl.p3_siga2.model.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina,String> {
    
}

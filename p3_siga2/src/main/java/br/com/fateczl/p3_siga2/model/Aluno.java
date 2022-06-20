package br.com.fateczl.p3_siga2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "aluno")
public class Aluno {
	@Id
	@Column(name = "ra", length = 13)
	@NonNull
	private String ra;
	
	@Column(name = "nome", length = 30)
	@NonNull
	private String nome;
	
	public String getRa() {
		return ra;
	}
	public void setRa(String ra) {
		this.ra = ra;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return "aluno [ra=" + ra + ", nome=" + nome + "]";
	}
	
}

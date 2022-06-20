package br.com.fateczl.p3_siga2.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "disciplina")
public class Disciplina {
	@Id
	@Column(name="cod_disciplina")
	@NonNull
	private String cod_disciplina;
	@Column(name="nome")
	@NonNull
	private String nome;
	@Column(name="sigla")
	@NonNull
	private String sigla;
	@Column(name="turno")
	@NonNull
	private String turno;
	@Column(name="num_aulas")
	@NonNull
	private int num_aulas;
	
	public String getCod_disciplina() {
		return cod_disciplina;
	}
	public void setCod_disciplina(String cod_disciplina) {
		this.cod_disciplina = cod_disciplina;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public int getNum_aulas() {
		return num_aulas;
	}
	public void setNum_aulas(int num_aulas) {
		this.num_aulas = num_aulas;
	}
	@Override
	public String toString() {
		return "disciplina [cod_disciplina=" + cod_disciplina + ", nome=" + nome + ", sigla=" + sigla + ", turno="
				+ turno + ", num_aulas=" + num_aulas + "]";
	}
	
}

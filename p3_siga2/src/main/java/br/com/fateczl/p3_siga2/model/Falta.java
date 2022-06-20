package br.com.fateczl.p3_siga2.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name="falta")
@IdClass(FaltaPK.class)
public class Falta {
	@Id
	@ManyToOne(targetEntity = Aluno.class)
	@JoinColumn(name = "ra")
	@NonNull
	private Aluno aluno;
	@Id
	@ManyToOne(targetEntity = Disciplina.class)
	@JoinColumn(name = "cod_disciplina")
	@NonNull
	private Disciplina disciplina;
	@Id
	@Column(name = "data_falta")
	@NonNull
	private String data;
	@Column(name = "presenca")
	@NonNull
	private String presenca;
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getPresenca() {
		return presenca;
	}
	public void setPresenca(String presenca) {
		this.presenca = presenca;
	}
	@Override
	public String toString() {
		return "Falta [aluno=" + aluno + ", data=" + data + ", disciplina=" + disciplina + ", presenca=" + presenca
				+ "]";
	}

	
}

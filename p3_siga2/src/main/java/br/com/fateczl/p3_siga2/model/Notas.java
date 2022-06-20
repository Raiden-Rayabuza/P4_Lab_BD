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
@Table(name="notas")
@IdClass(NotasPK.class)
public class Notas {
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
	@ManyToOne(targetEntity = Avaliacao.class)
	@JoinColumn(name = "cod_avaliacao")
	@NonNull
	private Avaliacao avaliacao;
	@Column(name = "nota")
	@NonNull
	private int nota;
	
	
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
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	
	@Override
	public String toString() {
		return "Notas [aluno=" + aluno + ", avaliacao=" + avaliacao + ", disciplina=" + disciplina + ", nota=" + nota + "]";
	}
	
}

package br.com.fateczl.p3_siga2_client.model;

public class Falta {
	private Aluno aluno;
	private Disciplina disciplina;
	private String data;
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
		return "Falta [aluno=" + aluno + ", disciplina=" + disciplina + ", data=" + data + ", presenca=" + presenca + "]";
	}
	
}
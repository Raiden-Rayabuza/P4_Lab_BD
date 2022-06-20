package br.com.fateczl.p3_siga2.dto;

public class DisciplinaDTO {
	private String cod_disciplina;
	private String nome;
	private String sigla;
	private String turno;
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

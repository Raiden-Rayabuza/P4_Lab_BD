package br.com.fateczl.p3_siga2.dto;
public class AlunoDTO {
	private String ra;
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

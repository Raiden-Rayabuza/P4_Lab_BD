package br.com.fateczl.p3_siga2.dto;
public class NotasDTO {
	private AlunoDTO aluno;
	private DisciplinaDTO disciplina;
	private AvaliacaoDTO avaliacao;	
	private int nota;

	public AlunoDTO getAluno() {
		return aluno;
	}
	public void setAluno(AlunoDTO aluno) {
		this.aluno = aluno;
	}
	public DisciplinaDTO getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(DisciplinaDTO disciplina) {
		this.disciplina = disciplina;
	}
	public AvaliacaoDTO getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(AvaliacaoDTO avaliacao) {
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
		return "Notas [aluno=" + aluno + ", avaliacao=" + avaliacao + ", disciplina=" + disciplina + ", nota=" + nota
				+ "]";
	}
	
}

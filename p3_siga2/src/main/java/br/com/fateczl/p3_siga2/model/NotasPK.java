package br.com.fateczl.p3_siga2.model;

import java.io.Serializable;

public class NotasPK implements Serializable {
    private static final long serialVersionUID = 1L;

	private Aluno aluno;
	private Disciplina disciplina;
	private Avaliacao avaliacao;
	
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
    @Override
    public String toString() {
        return "NotasPK [aluno=" + aluno + ", avaliacao=" + avaliacao + ", disciplina=" + disciplina + "]";
    }
	
}

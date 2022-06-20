package br.com.fateczl.p3_siga2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name="NotasAlunos")
@NamedNativeQuery(
    name = "NotasAlunos.udf_notas_alunos",
    query = "SELECT ra, nome_aluno, nome_disciplina, tipo_avaliacao, nota FROM udf_notas_alunos()",
    resultClass = NotasAlunos.class
)
public class NotasAlunos {
    @Id
    @Column
	private String ra;
	@Column
	private String nome_aluno;
	@Column
	private String nome_disciplina;
	@Column
	private String tipo_avaliacao;
    @Column
    private int nota;
    
    public String getRa() {
        return ra;
    }
    public void setRa(String ra) {
        this.ra = ra;
    }
    public String getNome_aluno() {
        return nome_aluno;
    }
    public void setNome_aluno(String nome_aluno) {
        this.nome_aluno = nome_aluno;
    }
    public String getNome_disciplina() {
        return nome_disciplina;
    }
    public void setNome_disciplina(String nome_disciplina) {
        this.nome_disciplina = nome_disciplina;
    }
    public String getTipo_avaliacao() {
        return tipo_avaliacao;
    }
    public void setTipo_avaliacao(String tipo_avaliacao) {
        this.tipo_avaliacao = tipo_avaliacao;
    }
    public int getNota() {
        return nota;
    }
    public void setNota(int nota) {
        this.nota = nota;
    }
    @Override
    public String toString() {
        return "NotasAlunos [nome_aluno=" + nome_aluno + ", nome_disciplina=" + nome_disciplina + ", nota=" + nota
                + ", ra=" + ra + ", tipo_avaliacao=" + tipo_avaliacao + "]";
    }
    
}

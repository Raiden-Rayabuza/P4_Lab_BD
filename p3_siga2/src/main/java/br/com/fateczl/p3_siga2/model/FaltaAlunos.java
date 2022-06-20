package br.com.fateczl.p3_siga2.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name="FaltaAlunos")
@NamedNativeQuery(
    name = "FaltaAlunos.udf_faltas_alunos",
    query = "SELECT ra, nome_aluno, nome_disciplina, data_falta, falta FROM udf_faltas_alunos()",
    resultClass = FaltaAlunos.class
)
public class FaltaAlunos {
	@Id
    @Column
	private String ra;
	@Column
	private String nome_aluno;
	@Column
	private String nome_disciplina;
	@Column
	private String data_falta;
    @Column
    private String falta;
    
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
    public String getData_falta() {
        return data_falta;
    }
    public void setData_falta(String data_falta) {
        this.data_falta = data_falta;
    }
    public String getFalta() {
        return falta;
    }
    public void setFalta(String falta) {
        this.falta = falta;
    }
	@Override
	public String toString() {
		return "FaltaAlunos [ra=" + ra + ", nome_aluno=" + nome_aluno + ", nome_disciplina=" + nome_disciplina
				+ ", data_falta=" + data_falta + ", falta=" + falta + "]";
	}

   
	
}

package br.com.fateczl.p3_siga2.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {
	@Id
	@Column(name="cod_avaliacao")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private int cod_avaliacao;

	@Column(name="tipo")
	@NonNull
	private String tipo;
	
	public int getCod() {
		return cod_avaliacao;
	}
	public void setCod(int cod_avaliacao) {
		this.cod_avaliacao = cod_avaliacao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "avaliacao [cod_avaliacao=" + cod_avaliacao + ", tipo=" + tipo + "]";
	}
	
}

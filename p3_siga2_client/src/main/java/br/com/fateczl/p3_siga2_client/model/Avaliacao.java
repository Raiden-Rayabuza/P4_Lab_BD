package br.com.fateczl.p3_siga2_client.model;
public class Avaliacao {
	
	private int cod_avaliacao;
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

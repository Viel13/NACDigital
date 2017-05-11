package br.com.fiap.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TB_PERFUME")
@SequenceGenerator(name="seqPerf", sequenceName="SQ_TB_PERFUME", allocationSize=1)
public class Perfume {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqPerf")
	private int cod;
	
	
	private String nome;
	
	
	private float valor;

	public Perfume(int cod, String nome, float valor) {
		super();
		this.cod = cod;
		this.nome = nome;
		this.valor = valor;
	}
	
	public Perfume(){
		
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	
	
}

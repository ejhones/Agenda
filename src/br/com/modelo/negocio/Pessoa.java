package br.com.modelo.negocio;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.modelo.enumerador.EstadoCivil;

public class Pessoa  implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;
	private String nome;
	private EstadoCivil estadoCivil;
	private String email;
	private String telefone;
	private Date dtaNasc;

	public Pessoa() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDtaNasc() {
		return dtaNasc;
	}

	public void setDtaNasc(Date dtaNasc) {
		this.dtaNasc = dtaNasc;
	}
	
	public String getFmtDtaNasc() {
		if (this.dtaNasc == null)
			return null;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(this.dtaNasc);
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Email: "+this.email+" Telefone:" +this.telefone;
	}
	
}

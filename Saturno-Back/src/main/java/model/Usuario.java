package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Usuario {
	private String id;
	private char genero;
	private String senha;
	private String email;
	private String nome;
	private int qtd_criadas;
	private int qtd_finalizadas;
	private Date dataNasc;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Usuario() throws ParseException {
		id = "-1";
		nome = "?";
		senha = "?";
		genero = 'N';
		email = "?";
		dataNasc = sdf.parse("01/01/1900");
		qtd_criadas = 0;
		qtd_finalizadas = 0;
		
	}

	public Usuario(String id, String nome, String senha, char genero, String email, String dataNasc, int qtd_criadas, int qtd_finalizadas) throws ParseException {
		setId(id);
		setNome(nome);
		setSenha(senha);
		setGenero(genero);
		setEmail(email);
		setDataNasc(dataNasc);
		setQtdCriadas(qtd_criadas);
		setQtdFinalizadas(qtd_finalizadas);
	}		
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDataNasc() {
		return sdf.format(dataNasc);
	}

	public void setDataNasc(String dataNasc) throws ParseException {
		this.dataNasc = sdf.parse(dataNasc);
	}
	
	public int getQtdCriadas() {
		return qtd_criadas;
	}

	public void setQtdCriadas(int qtd_criadas) {
		this.qtd_criadas = qtd_criadas;
	}
	
	public int getQtdFinalizadas() {
		return qtd_finalizadas;
	}

	public void setQtdFinalizadas(int qtd_finalizadas) {
		this.qtd_finalizadas = qtd_finalizadas;
	}

	public String toString() {
		return "Id: " + getId() + " Nome: " + getNome() + " Senha: " + getSenha() + " Genero: " + getGenero() + " Email: " + getEmail() + " Data de Nascimento: " + getDataNasc() + " Tarefas Criadas: "
	+ getQtdCriadas() + " Tarefas Finalizadas: " + getQtdFinalizadas();
	}
	
	public boolean equals(Object obj) {
		return (this.getId() == ((Usuario) obj).getId());
	}	
}
package br.com.zup.SpringBootTwo.dto;

public class Token {

	private String token;
	private String tipo;

	public Token(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}

}

package entities;

public class Conta {
	private Integer id;
	private String username;
	private String email;
	private String senha;
	
	public Conta (String username, String email, String senha) {
		this.username = username;
		this.email = email;
		this.senha = senha;
	}
	
	public Conta (Integer idconta, String username, String email, String senha) {
		this.id = idconta;
		this.username = username;
		this.email = email;
		this.senha = senha;
	}
	
	public Conta(Integer idconta, String username) {
		this.id = idconta;
		this.username = username;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer novoId) {
		this.id = novoId;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return "A conta " + username + ", cadastrada no email " + email + " e com senha " + senha + ", tem o ID "
				+ "igual a " + id;
	}
	
}
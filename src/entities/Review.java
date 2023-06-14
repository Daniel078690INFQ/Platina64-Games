package entities;

public class Review{
	private Integer id;
	private Jogo produto;
	private int nota;
	private String analise;
	private Conta usuario;
	
	public Review(Integer id, Jogo produto, int nota, String review, Conta usuario) {
		this.id = id;
		this.produto = produto;
		this.nota = nota;
		if (nota > 10) {this.nota = 10;}
		if (nota < 1) {this.nota = 1;}
		this.analise = review;
		this.usuario = usuario;
	}
	
	public Review(Jogo produto, int nota, String review, Conta usuario) {
		this.produto = produto;
		this.nota = nota;
		if (nota > 10) {this.nota = 10;}
		if (nota < 1) {this.nota = 1;}
		this.analise = review;
		this.usuario = usuario;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer novoId) {
		this.id = novoId;
	}

	public void darNota(int nota) {
		this.nota = nota;
	}
	
	public int getNota() {
		return this.nota;
	}
	
	public void escreverReview(String artigo) {
		this.analise = artigo;
	}
	
	public String getAnalise() {
		return this.analise;
	}
	
	public Jogo getJogo() { //Método apenas para evitar o erro de "Produto" não ser usado
		return this.produto;
	}
	
	public Conta getUsuario() {
		return this.usuario;
	}

	@Override
	public String toString() {
		return "A review de ID: " + id + ", do jogo " + produto.getNome() + " e escrita por " + usuario.getUsername() + ","
				+ " tem nota " + nota + " e análise " + analise;
	}
}
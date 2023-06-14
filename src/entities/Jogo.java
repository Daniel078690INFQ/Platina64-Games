package entities;

public class Jogo {
	private Integer id;
	private String nome;
	private String descricao;
	private String dataLancamento;
	private String Desenvolvedor;
	private String genero;
	private int conquistas;
	
	public Jogo(Integer id, String nome, String descricao, String dataLancamento, String desenvolvedor, String genero, int conquistas) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.dataLancamento = dataLancamento;
		this.genero = genero;
		Desenvolvedor = desenvolvedor;
		this.conquistas = conquistas;
	}
	
	public Jogo(String nome, String descricao, String dataLancamento, String desenvolvedor, String genero, int conquistas) {
		this.nome = nome;
		this.descricao = descricao;
		this.dataLancamento = dataLancamento;
		Desenvolvedor = desenvolvedor;
		this.genero = genero;
		this.conquistas = conquistas;
	}
	
	public Jogo(Integer idjogo, String nome) {
		this.id = idjogo;
		this.nome = nome;
	}

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer novoId) {
		this.id = novoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getDesenvolvedor() {
		return Desenvolvedor;
	}

	public void setDesenvolvedor(String desenvolvedor) {
		Desenvolvedor = desenvolvedor;
	}

	public int getConquistas() {
		return conquistas;
	}

	public void setConquistas(int conquistas) {
		this.conquistas = conquistas;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "O jogo " + nome + ", descrito como '" + descricao + "', lançado em " + dataLancamento
				+ ", desenvolvido pela empresa " + Desenvolvedor + ", do genero " + genero + ", possui" + conquistas + " conquistas e "
				+ "ID igual a " + id;
	}
}

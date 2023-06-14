package application;

import java.util.List;
import java.util.Scanner;

import db.DB;
import java.sql.Connection;
import model.dao.*;
import entities.*;

public class Main {
	static Conta contaAtiva;
	static Scanner teclado = new Scanner(System.in);
	static ContaDaoJDBC utilConta = new ContaDaoJDBC();
	static JogoDaoJDBC utilJogo = new JogoDaoJDBC();
	static ReviewDaoJDBC utilReview = new ReviewDaoJDBC();
		
	public static void main(String[] args) {
		System.out.println("| Conectando com o servidor. Por favor, aguarde.");
		Connection conn = DB.getConnection();
		if (conn != null) { System.out.println("| Conex�o com o servidor estabelecida com sucesso!\n"); }
		abertura(); 
		}
	
	//Fun��o Inicial do Programa
	public static void abertura() {
		System.out.println("\n|-------------------------------------|");
		System.out.println("|         Platina64 Reviews           |");
	    System.out.println("|-------------------------------------|");
	    System.out.println("|     1 - Cadastrar                   |");
	    System.out.println("|     2 - Entrar                      |");
	    System.out.println("|                                     |");
	    System.out.println("|     0 - Encerrar Programa           |");
	    System.out.println("|-------------------------------------|\n");
	    System.out.printf("| Sua op��o: "); int caso = teclado.nextInt();
	    System.out.println();
	      
	      switch (caso) {
	      case (0): programaEncerrado(); break;
	      case (1): cadastrarConta(); break;
	      case (2): fazerLogin(); break;
	      default: acaoInvalida(); abertura(); break;
	      }
	   }
	
	//Cadastra um usu�rio no sistema.
	public static void cadastrarConta() {
		System.out.println("\n|-------------------------------------|");
	    System.out.println("|           Fazer Cadastro            |");
	    System.out.println("|-------------------------------------|\n");
	    System.out.print("| Insira seu endere�o de email: "); String email = teclado.next();
		System.out.print("| Insira seu nome de usu�rio: "); String username = teclado.next();
		System.out.print("| Insira a sua senha (Max. 25 caracteres): "); String senha = teclado.next();
		if (!utilConta.disponibilidadeDeDados(email, username)) {
			erroDeCadastro();
		}
		else {
			Conta novoUsuario = new Conta(username, email, senha);
			utilConta.insert(novoUsuario);
			System.out.println("\n|-------------------------------------|");
		    System.out.println("|    Usu�rio Cadastrado com Sucesso!  |");
		    System.out.println("|-------------------------------------|\n");
		    abertura();
		}
    }
	
	//Mensagem de Erro de Cadastro
	public static void erroDeCadastro() {
		System.out.println("\n|-------------------------------------------------|");
	    System.out.println("|    Nome de Usu�rio ou Email j� cadastrados!     |");
		System.out.println("|                                                 |");
		System.out.println("|             1 - Tentar Novamente                |");
		System.out.println("|             0 - Encerrar Programa               |");
		System.out.println("|                                                 |");
	    System.out.println("|-------------------------------------------------|\n");
	    System.out.printf("| Sua op��o: "); int caso = teclado.nextInt(); teclado.close();
	    
	    switch (caso) {
	      case (0): programaEncerrado(); break;
	      case (1): cadastrarConta(); break;
	      default: acaoInvalida(); erroDeCadastro(); break;
	      }
	}
	
	//Verifica se o usu�rio est� cadastrado no sistema.
	public static void fazerLogin() {
		System.out.println("\n|-------------------------------------|");
	    System.out.println("|             Fazer Login             |");
	    System.out.println("|-------------------------------------|\n");
		System.out.print("Insira seu nome de usu�rio: "); String username = teclado.next();
		System.out.print("Insira a sua senha: "); String senha = teclado.next();
		if (utilConta.autenticarConta(username, senha)) {
			contaAtiva = utilConta.findByUsername(username);
			System.out.println("\n|-------------------------------------|");
		    System.out.println("|          Welcome, Stranger!         |");
		    System.out.println("|-------------------------------------|\n");
		    paginaInicial();
		}
		else {
		    loginIncorreto();
		}
	}
	
	//Mensagem de Erro de Login
	public static void loginIncorreto() {
		System.out.println("\n|--------------------------------------------|");
	    System.out.println("|    Nome de Usu�rio ou Senha Incorretos!    |");
		System.out.println("|                                            |");
		System.out.println("|        1 - Tentar Novamente                |");
		System.out.println("|        2 - Criar Cadastro                  |");
		System.out.println("|                                            |");
		System.out.println("|        0 - Encerrar Programa               |");
		System.out.println("|                                            |");
	    System.out.println("|--------------------------------------------|\n");
	    System.out.printf("| Sua op��o: "); int caso = teclado.nextInt();
	    
	    switch (caso) {
	      case (0): programaEncerrado(); break;
	      case (1): fazerLogin(); break;
	      case (2): cadastrarConta(); break;
	      default: acaoInvalida(); loginIncorreto(); break;
	      }
	}
	
	//Mensagem de Encerramento do Programa
	public static void programaEncerrado() {
		teclado.close();
		System.out.println("\n|---------------------------------------------|");
	    System.out.println("|      Programa Encerrado pelo Usu�rio!       |");
	    System.out.println("|---------------------------------------------|");
	    System.out.println("|                                             |");
	    System.out.println("|    Obrigado por Usar o Platina64 Reviews!   |");
	    System.out.println("|                                             |");
	    System.out.println("|---------------------------------------------|\n");
	    DB.closeConnection();
	}

	//Mensagem de A��o Inv�lida para os Switches
	public static void acaoInvalida() {
			System.out.println("\n|-------------------------------------|");
		    System.out.println("|    A��o Inv�lida! Tente novamente.  |");
		    System.out.println("|-------------------------------------|\n");
	}

	//P�gina Inicial do Site
	public static void paginaInicial() {
		System.out.println("\n|-------------------------------------|");
		System.out.println("|    Bem vindo ao Platina64 Reviews   |");
	    System.out.println("|-------------------------------------|");
	    System.out.println("|     1 - Cat�logo de Jogos           |");
	    System.out.println("|     2 - �rea do Usu�rio             |");
	    System.out.println("|                                     |");
	    System.out.println("|     0 - Encerrar Programa           |");
	    System.out.println("|-------------------------------------|\n");
	    System.out.print("| Sua op��o: "); int caso = teclado.nextInt();
	    System.out.println();
	      
	      switch (caso) {
	      case (0): programaEncerrado(); break;
	      case (1): catalogoDeJogos(); break;
	      case (2): areaDoUsuario(); break;
	      default: acaoInvalida(); paginaInicial(); break;
	      }
	}
	
	//Cat�logo de Jogos
	public static void catalogoDeJogos() {
		System.out.println("\n|---------------------------------------------|");
	    System.out.println("|             Cat�logo de Jogos               |");
	    System.out.println("|---------------------------------------------|");
	    System.out.println("|                                             |");
	    System.out.println("|    1 - Dark Souls: Prepare to Die Edition   |");
	    System.out.println("|    2 - Hollow Knight                        |");
	    System.out.println("|    3 - Stardew Valley                       |");
	    System.out.println("|    4 - The Elder Scrolls V: Skyrim          |");
	    System.out.println("|                                             |");
	    System.out.println("|                                             |");
	    System.out.println("|                                             |");
	    System.out.println("|    9 - Voltar para P�gina Inicial           |");
	    System.out.println("|    0 - Encerrar Programa                    |");
	    System.out.println("|---------------------------------------------|\n");
	    System.out.print("| Sua op��o: "); int caso = teclado.nextInt();
	    System.out.println();
	      
	      switch (caso) {
	      case (0): programaEncerrado(); break;
	      case (1): DarkSouls(); break;
	      case (2): HollowKnight(); break;
	      case (3): StardewValley(); break;
	      case (4): TheElderScrolls(); break;
	      case (9): paginaInicial(); break;
	      default: acaoInvalida(); catalogoDeJogos(); break;
	      }
	}
	
	//Dark Souls
	public static void DarkSouls() {
		Jogo base = utilJogo.findById(1);
		System.out.println("\n|---------------------------------------------|");
	    System.out.println("|      Dark Souls: Prepare To Die Edition     |");
	    System.out.println("|---------------------------------------------|\n");
	    System.out.println("| G�nero: " + base.getGenero() + " | N� de Conquistas: " + base.getConquistas()
	    + " |\n| Desenvolvedora: " + base.getDesenvolvedor() + " | Data de Lan�amento: "
	    + base.getDataLancamento() + " |\n");
	    
	    System.out.println("\"" + base.getDescricao() + "\"\n");
	    menuBaseReviews(base.getId());
	}
	
	//Hollow Knight
	public static void HollowKnight() {
		Jogo base = utilJogo.findById(2);
		System.out.println("\n|---------------------------------------------|");
	    System.out.println("|                Hollow Knight                |");
	    System.out.println("|---------------------------------------------|\n");
	    System.out.println("| G�nero: " + base.getGenero() + " | N� de Conquistas: " + base.getConquistas()
	    + " |\n| Desenvolvedora: " + base.getDesenvolvedor() + " | Data de Lan�amento: "
	    + base.getDataLancamento() + " |\n");
	    
	    System.out.println("\"" + base.getDescricao() + "\"\n");
	    menuBaseReviews(base.getId());
	}
	
	//Stardew Valley
	public static void StardewValley() {
		Jogo base = utilJogo.findById(3);
		System.out.println("\n|---------------------------------------------|");
	    System.out.println("|              Stardew Valley                 |");
	    System.out.println("|---------------------------------------------|\n");
	    System.out.println("| G�nero: " + base.getGenero() + " | N� de Conquistas: " + base.getConquistas()
	    + " |\n| Desenvolvedora: " + base.getDesenvolvedor() + " | Data de Lan�amento: "
	    + base.getDataLancamento() + " |\n");
	    
	    System.out.println("\"" + base.getDescricao() + "\"\n");
	    menuBaseReviews(base.getId());	
	}
	
	//The Elder Scrolls V: Skyrim
	public static void TheElderScrolls() {
		Jogo base = utilJogo.findById(4);
		System.out.println("\n|---------------------------------------------|");
	    System.out.println("|         The Elder Scrolls V: Skyrim         |");
	    System.out.println("|---------------------------------------------|\n");
	    System.out.println("| G�nero: " + base.getGenero() + " | N� de Conquistas: " + base.getConquistas()
	    + " |\n| Desenvolvedora: " + base.getDesenvolvedor() + " | Data de Lan�amento: "
	    + base.getDataLancamento() + " |\n");
	    
	    System.out.println("\"" + base.getDescricao() + "\"\n");
	    menuBaseReviews(base.getId());
	}
	
	//Menu Base de Direcionamento das Reviews
	public static void menuBaseReviews(Integer idjogo) {
		System.out.println("\n|-----------------------------------------|");
	    System.out.println("|    1 - Acessar Reviews dos Usu�rios     |");
	    System.out.println("|    2 - Escrever uma Review              |");
	    System.out.println("|    3 - Editar sua Review                |");
	    System.out.println("|                                         |");
	    System.out.println("|    9 - Voltar para o Cat�logo de Jogos  |");
	    System.out.println("|    0 - Encerrar Programa                |");
	    System.out.println("|-----------------------------------------|\n");
	    System.out.print("| Sua op��o: "); int caso = teclado.nextInt();
	    System.out.println();
	      
	      switch (caso) {
	      case (0): programaEncerrado(); break;
	      case (1): acessarReviews(idjogo); break;
	      case (2): escreverReview(idjogo); break;
	      case (3): suaReview(idjogo); break;
	      case (9): catalogoDeJogos(); break;
	      default: acaoInvalida(); menuBaseReviews(idjogo); break;
	      }
	}
	
	//Acessar Review
	public static void acessarReviews(Integer idjogo) {
		Jogo jogoAcessado = utilJogo.findById(idjogo);
		System.out.println("\n|      " + jogoAcessado.getNome() + "      |");
		System.out.println("\n|---------------------------|");
		System.out.printf("|   M�dia de Notas   | %.2f |", utilReview.mediaDeNotas(idjogo));
		System.out.println("\n|---------------------------|\n");
		System.out.println("|   Sua nota: " + utilReview.findNotaByUser(contaAtiva, idjogo) + "\n");
		System.out.println("|   Sua an�lise: \"" + utilReview.findReviewByUser(contaAtiva, idjogo) + "\"\n");
		System.out.println("|---------------------------|");
		System.out.println("|   An�lises dos Usu�rios   |");
		System.out.println("|---------------------------|\n");
		List<Review> listaDeGames = utilReview.findAllByGame(idjogo);
    	for (int i = 0; i < listaDeGames.size(); i++) {
			System.out.println("| " + listaDeGames.get(i).getUsuario().getUsername() + 
					" | Nota: " + listaDeGames.get(i).getNota());
			System.out.println("| \"" + listaDeGames.get(i).getAnalise() + "\"\n");
 		}
    	System.out.println("|-----------------------------|");
		System.out.println("|     1. Voltar ao Cat�logo   |");
		System.out.println("|                             |");
		System.out.println("|     0. Encerrar Programa    |");
		System.out.println("|-----------------------------|\n");
		System.out.print("| Sua op��o: "); int caso = teclado.nextInt();
		switch (caso) {
		case (1): catalogoDeJogos(); break;
		case (0): programaEncerrado(); break;
		default: acaoInvalida(); acessarReviews(idjogo); break;
		}
	}
	
	// Review do Usu�rio
	public static void suaReview(Integer idjogo) {
		if (utilReview.findReviewByUser(contaAtiva, idjogo) == null) {
			System.out.println("\n| Voc� ainda n�o escreveu uma review para este jogo! |");
			escreverReview(idjogo);
		}
		System.out.println("|-----------------------------|");
		System.out.println("|          Sua Review         |");
		System.out.println("|-----------------------------|");
		System.out.println("\n|   Sua nota: " + utilReview.findNotaByUser(contaAtiva, idjogo) + "\n");
		System.out.println("|   Sua an�lise: \"" + utilReview.findReviewByUser(contaAtiva, idjogo) + "\"\n");
		System.out.println("\n|-----------------------------|");
		System.out.println("|     1. Editar Nota          |");
		System.out.println("|     2. Editar An�lise       |");
		System.out.println("|     3. Apagar Sua Review    |");
		System.out.println("|                             |");
		System.out.println("|     9. Voltar ao Cat�logo   |");
		System.out.println("|     0. Encerrar Programa    |");
		System.out.println("|-----------------------------|");
		System.out.print("| Sua op��o: "); int caso = teclado.nextInt();
		switch (caso) {
		case (1): editarNota(idjogo); break;
		case (2): editarAnalise(idjogo); break;
		case (3): apagarReview(idjogo); break;
		case (9): catalogoDeJogos(); break;
		case (0): programaEncerrado(); break;
		default: acaoInvalida(); suaReview(idjogo); break;
		}
	}
	
	//Escrever Uma Review
	public static void escreverReview(Integer idjogo) {
		String analise = null;
		if (utilReview.findReviewByUser(contaAtiva, idjogo) != null) {
			System.out.println("\n| Voc� j� tem uma review para este jogo! |");
			acessarReviews(idjogo);
		}
		System.out.println("\n|--------------------------------|");
	    System.out.println("|         Escrever Review        |");
	    System.out.println("|--------------------------------|\n");
	    System.out.print("| Qual a nota que voc� d� para " + utilJogo.findById(idjogo).getNome() + "? "
	    		+ "(Valor entre 1 e 10) "); int nota = teclado.nextInt();
	    System.out.print("| Voc� deseja escrever uma an�lise por escrito? (1 - Sim, 2 - N�o) "); 
	    int opcao = teclado.nextInt(); teclado.nextLine();
	    switch (opcao) {
	    case (1): System.out.print("| Por favor, escreva a sua an�lise: "); analise = teclado.nextLine(); break;
	    case (2): analise = null; break; 
	    default: acaoInvalida(); escreverReview(idjogo); break;
	    }
	    Review mousepad = new Review(utilJogo.findById(idjogo), nota, analise, contaAtiva);
	    utilReview.insert(mousepad);
	    acessarReviews(idjogo);
	}
	
	//Editar Notas
	public static void editarNota(Integer idjogo) {
		System.out.println("\n|--------------------------------|");
	    System.out.println("|   Editar Nota de Sua Review    |");
	    System.out.println("|--------------------------------|\n");
	    System.out.printf("| Qual a sua nova nota para " + utilJogo.findById(idjogo).getNome() 
	    		+ "? (Valores entre 1 e 10)"); int notaNova = teclado.nextInt();
	    utilReview.updateNota(contaAtiva, utilJogo.findById(idjogo), notaNova);
	    System.out.println("\n|--------------------------------|");
	    System.out.println("|    Sua Nota Foi Atualizada     |");
	    System.out.println("|--------------------------------|\n");
	    catalogoDeJogos();
	}
	
	//Editar a An�lise
	public static void editarAnalise(Integer idjogo) {
		System.out.println("\n|--------------------------------|");
	    System.out.println("|  Editar An�lise de Sua Review  |");
	    System.out.println("|--------------------------------|\n");
	    System.out.printf("| Qual a sua an�lise por escrito para " + utilJogo.findById(idjogo).getNome() 
	    		+ ":"); teclado.nextLine(); String novaAnalise = teclado.nextLine();
	    utilReview.updateAnalise(contaAtiva, utilJogo.findById(idjogo), novaAnalise);
	    System.out.println("\n|--------------------------------|");
	    System.out.println("|   Sua An�lise Foi Atualizada   |");
	    System.out.println("|--------------------------------|\n");
	    catalogoDeJogos();
	}
	
	//Apagar a Review
	public static void apagarReview(Integer idjogo) {
		System.out.println("\n|-------------------------------------|");
	    System.out.println("|        Deletando sua Review         |");
	    System.out.println("|-------------------------------------|\n");
	    System.out.printf("| Insira sua senha: "); String senha1 = teclado.next();
	    System.out.printf("| Confirme sua senha: "); String senha2 = teclado.next();
	    if (contaAtiva.getSenha().equals(senha1) && senha1.equals(senha2)) {
	    	utilReview.deleteByID(contaAtiva, idjogo);
	    	System.out.println("\n|-----------------------------------------|");
		    System.out.println("|   Sua Review foi Deletada com Sucesso   |");
		    System.out.println("|-----------------------------------------|\n");
		    catalogoDeJogos();
	    }
	    else {
	    	System.out.println("\n| Sua senha est� incorreta! Tente novamente. \n");
			apagarReview(idjogo);
	    }
	}
	
	//�rea do Usu�rio
	public static void areaDoUsuario() {
		System.out.println("\n|----------------------------------------|");
		System.out.println("|           �rea do Usu�rio              |");
	    System.out.println("|----------------------------------------|");
	    System.out.println("|     1 - Alterar Nome de Usu�rio        |");
	    System.out.println("|     2 - Alterar Endere�o de Email      |");
	    System.out.println("|     3 - Alterar Senha                  |");
	    System.out.println("|     4 - Deletar Sua Conta              |");
	    System.out.println("|     5 - Sair                           |");
	    System.out.println("|                                        |");
	    System.out.println("|     9 - Voltar para P�gina Inicial     |");
	    System.out.println("|     0 - Encerrar Programa              |");
	    System.out.println("|----------------------------------------|\n");
	    System.out.printf("| Sua op��o: "); int caso = teclado.nextInt();
	    System.out.println();
	      
	      switch (caso) {
	      case (0): programaEncerrado(); break;
	      case (1): alterarUsername(); break;
	      case (2): alterarEmail(); break;
	      case (3): alterarSenha(); break;
	      case (4): deletarConta(); break;
	      case (5): contaAtiva = null; abertura(); break;
	      case (9): paginaInicial(); break;
	      default: acaoInvalida(); areaDoUsuario(); break;
	      }
	}
	
	//Deletar Conta
	public static void deletarConta() {
		System.out.println("\n|-------------------------------------|");
    System.out.println("|         Deletando sua Conta         |");
    System.out.println("|-------------------------------------|\n");
    System.out.printf("| Insira sua senha: "); String senha1 = teclado.next();
    System.out.printf("| Confirme sua senha: "); String senha2 = teclado.next();
    if (contaAtiva.getSenha().equals(senha1) && senha1.equals(senha2)) {
    	utilConta.deleteByID(contaAtiva.getId());
    	System.out.println("\n|-----------------------------------------|");
	    System.out.println("|    Sua Conta foi Deletada com Sucesso   |");
	    System.out.println("|-----------------------------------------|\n");
	    abertura();
    }
    else {
    	System.out.println("\n| Sua senha est� incorreta! Tente novamente. \n");
		deletarConta();
    }
	}
	
	//Alterar Username
	public static void alterarUsername() {
		System.out.println("\n|-------------------------------------|");
	    System.out.println("|    Alterando seu Nome de Usu�rio    |");
	    System.out.println("|-------------------------------------|\n");
		System.out.print("| Insira seu novo nome de usu�rio: "); String username = teclado.next();
		System.out.print("| Confirme a sua senha: "); String senha = teclado.next();
		if (utilConta.findByUsername(username) != null) {
			System.out.println("\n| O nome de usu�rio \"" + username + "\" j� existe! Tente novamente. \n");
			alterarUsername();
		}
		else if (!senha.equals(contaAtiva.getSenha())) {
			System.out.println("\n| Sua senha est� incorreta! Tente novamente. \n");
			alterarUsername();
		} else {
			utilConta.updateUsername(contaAtiva, username);
			System.out.println("\n|-----------------------------------------|");
		    System.out.println("|   Nome de Usu�rio Alterado com Sucesso  |");
		    System.out.println("|-----------------------------------------|\n");
		    areaDoUsuario();
		}
	}
	
	//Alterar Email
	public static void alterarEmail() {
		System.out.println("\n|-------------------------------------|");
	    System.out.println("|   Alterando seu Endere�o de Email   |");
	    System.out.println("|-------------------------------------|\n");
		System.out.print("| Insira seu novo endere�o de email: "); String email = teclado.next();
		System.out.print("| Confirme a sua senha: "); String senha = teclado.next();
		if (utilConta.findByEmail(email) != null) {
			System.out.println("\n| O endere�o de email \"" + email + "\" j� pertence a outra"
					+ "conta cadastrada! Tente novamente.\n");
			alterarEmail();
		}
		else if (!senha.equals(contaAtiva.getSenha())) {
			System.out.println("\n| Sua senha est� incorreta! Tente novamente. \n");
			alterarEmail();
		} else {
			utilConta.updateEmail(contaAtiva, email);
			System.out.println("\n|------------------------------------------|");
		    System.out.println("|  Endere�o de Email Alterado com Sucesso  |");
		    System.out.println("|------------------------------------------|\n");
		    areaDoUsuario();
		}
	}
		
	//Alterar Senha
	public static void alterarSenha() {	
		System.out.println("\n|-------------------------------------|");
	    System.out.println("|         Alterando sua Senha         |");
	    System.out.println("|-------------------------------------|\n");
		System.out.print("| Insira sua senha atual: "); String senha1 = teclado.next();
		System.out.print("| Confirme sua senha atual: "); String senha2 = teclado.next();
		System.out.print("| Insira sua nova senha (Max. 25 caracteres): "); String senhaNova = teclado.next();
		if (senha1.equals(senha2)) {
			utilConta.updateSenha(contaAtiva, senhaNova);
			System.out.println("\n|------------------------------------------|");
		    System.out.println("|         Senha Alterada com Sucesso       |");
		    System.out.println("|------------------------------------------|\n");
		    areaDoUsuario();
		}
		else {
			System.out.println("\n|------------------------------------------|");
		    System.out.println("|       Suas Senhas N�o Coincidem!         |");
		    System.out.println("|------------------------------------------|\n");
		    alterarSenha();
		}
	}
}
package model.dao;

import java.util.List;
import entities.Conta;

public interface ContaDao {
	
	void insert(Conta obj);
	void updateEmail(Conta obj, String novoEmail);
	void updateUsername(Conta obj, String novoUsername);
	void updateSenha(Conta obj, String novaSenha);
	void deleteByID(Integer id);
	Conta findById(Integer id);
	List<Conta> findAll();
	boolean autenticarConta(String username, String senha);
	boolean disponibilidadeDeDados(String email, String username);
}
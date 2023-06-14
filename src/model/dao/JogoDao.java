package model.dao;

import java.util.List;
import entities.Jogo;

public interface JogoDao {

	Jogo findById(Integer id);
	List<Jogo> findAll();
}
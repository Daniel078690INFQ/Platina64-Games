package model.dao;

import java.util.List;
import entities.*;

public interface ReviewDao {
	
	void insert(Review obj);
	void updateNota(Conta obj, Jogo produto, int novaNota);
	void updateAnalise(Conta obj, Jogo produto, String novaAnalise);
	void deleteByID(Conta user, Integer idjogo);
	Review findById(Integer id);
	List<Review> findAllByGame(Integer idjogo);
	String findReviewByUser(Conta user, Integer produto);
}
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import entities.*;

public class ReviewDaoJDBC implements ReviewDao {
	Connection conn = DB.getConnection();

	public void insert(Review obj) {
		PreparedStatement st = null;
		try {
		st = conn.prepareStatement("INSERT INTO Review (nota, analise, idjogo, idconta)"
				+ " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		st.setInt(1, obj.getNota());
		st.setString(2, obj.getAnalise());
		st.setInt(3, obj.getJogo().getId());
		st.setInt(4, obj.getUsuario().getId());
		int rowsAffected = st.executeUpdate();
		if (rowsAffected > 0) {
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				obj.setId(rs.getInt(1));
			}
			DB.closeResultSet(rs);
		} else { throw new DbException("Erro inesperado! Nenhum registo foi criado ou alterado!"); }
		} catch (SQLException e) {
			throw new DbException(e.getMessage()); 
		} finally { DB.closeStatement(st); }
	}

	@Override
	public void updateNota(Conta obj, Jogo produto, int novaNota) {
		PreparedStatement st = null;
		try {
		conn = DB.getConnection();
		st = conn.prepareStatement("Update Review SET nota = ? where idconta = ? and idjogo = ?");
		st.setInt(1, novaNota);
		st.setInt(2, obj.getId());
		st.setInt(3, produto.getId());
		st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void updateAnalise(Conta obj, Jogo produto, String novaAnalise) {
		PreparedStatement st = null;
		try {
		conn = DB.getConnection();
		st = conn.prepareStatement("Update Review SET analise = ? where idconta = ? and idjogo = ?");
		st.setString(1, novaAnalise);
		st.setInt(2, obj.getId());
		st.setInt(3, produto.getId());
		st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteByID(Conta user, Integer idjogo) {
		PreparedStatement st = null;
		try {
		conn = DB.getConnection();
		st = conn.prepareStatement("DELETE FROM Review WHERE idconta = ? AND idjogo = ?");
		st.setInt(1, user.getId());
		st.setInt(2, idjogo);
		st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
		}
	}

	
	@Override
	public Review findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("Select * FROM Review r, Jogo j, Conta c WHERE r.idjogo = j.idjogo"
					+ " AND r.idconta = c.idconta AND r.idreview = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			while (rs.next()) {
				Review r = new Review(rs.getInt("idreview"), instantiateJogo(rs), rs.getInt("nota"), 
						rs.getString("analise"), instantiateConta(rs));
				return r;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return null;
	}
	
	public double mediaDeNotas(Integer idjogo) {
		double soma = 0;
		double quantReviews = 0;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT nota FROM review WHERE idjogo = ?");
			st.setInt(1, idjogo);
			rs = st.executeQuery();
			while (rs.next()) {
				soma += rs.getInt("nota");
				quantReviews++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st); }
		return (soma) / (quantReviews);
	}
	
	public String findReviewByUser(Conta user, Integer produto) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT analise FROM review WHERE idconta = ? AND idjogo = ?");
			st.setInt(1, user.getId()); st.setInt(2, produto);
			rs = st.executeQuery();
			while (rs.next()) {
				return rs.getString("analise");
			}
		}
			catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DB.closeStatement(st);
		} return null;
	}
	
	public int findNotaByUser(Conta user, Integer produto) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT nota FROM review WHERE idconta = ? AND idjogo = ?");
			st.setInt(1, user.getId()); st.setInt(2, produto);
			rs = st.executeQuery();
			while (rs.next()) {
				return rs.getInt("nota");
			}
		}
			catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DB.closeStatement(st);
		} return 0;
	}

	@Override
	public List<Review> findAllByGame(Integer idjogo) {
		List<Review> reviews = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT review.*, jogo.nome as nome, jogo.idjogo as idjogo, conta.username as username, "
					+ "conta.idconta as idconta FROM review INNER JOIN jogo INNER JOIN conta "
					+ "ON review.idconta = conta.idconta AND review.idjogo = jogo.idjogo AND jogo.idjogo = ? "
					+ "ORDER BY idreview");	
			st.setInt(1, idjogo);
			rs = st.executeQuery();
			Map<Integer, Conta> mapaUser = new HashMap<>();
			Map<Integer, Jogo> mapaGamer = new HashMap<>();
			while (rs.next()) {
				Jogo game = mapaGamer.get(rs.getInt("idjogo"));
				if (game == null) {
					game = new Jogo(rs.getInt("idjogo"), rs.getString("nome"));
					mapaGamer.put(rs.getInt("idjogo"), game);
				}
				Conta user = mapaUser.get(rs.getInt("idconta"));
				if (user == null) {
					user = new Conta(rs.getInt("idconta"), rs.getString("username"));
					mapaUser.put(rs.getInt("idconta"), user);
				}
				Review obj = new Review (game, rs.getInt("nota"), rs.getString("analise"), user);
				reviews.add(obj);
			}
			return reviews;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	private Jogo instantiateJogo(ResultSet rs) throws SQLException {
	    Jogo game = new Jogo(rs.getInt("idjogo"), rs.getString("nome"), 
				rs.getString("descricao"), rs.getString("dataLancamentoString"), rs.getString("desenvolvedor"),
				rs.getString("genero"), rs.getInt("conquistas"));
		return game;
	}
	
	private Conta instantiateConta(ResultSet rs) throws SQLException {
		Conta user = new Conta(rs.getInt("idconta"), rs.getString("username"), 
				rs.getString("email"), rs.getString("senha"));
		return user;
	}
}

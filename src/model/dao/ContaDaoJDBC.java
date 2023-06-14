package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import db.DB;
import db.DbException;
import entities.Conta;

public class ContaDaoJDBC implements ContaDao{
	private Connection conn = DB.getConnection();
	
	@Override
	public void insert(Conta obj) {
		PreparedStatement st = null;
		try {
		st = conn.prepareStatement("INSERT INTO Conta (username, email, senha) "
				+ "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		st.setString(1, obj.getUsername());
		st.setString(2, obj.getEmail());
		st.setString(3, obj.getSenha());
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

	public void updateUsername(Conta obj, String novoUsername) {
		PreparedStatement st = null;
		try {
		conn = DB.getConnection();
		st = conn.prepareStatement("Update Conta SET username = ? where idconta = ?");
		st.setString(1, novoUsername);
		st.setInt(2, obj.getId());
		st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
		}
	}
	
	@Override
	public void updateEmail(Conta obj, String novoEmail) {
		PreparedStatement st = null;
		try {
		conn = DB.getConnection();
		st = conn.prepareStatement("Update Conta SET email = ? where idconta = ?");
		st.setString(1, novoEmail);
		st.setInt(2, obj.getId());
		st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
		}
		
	}
	
	@Override
	public void updateSenha(Conta obj, String novaSenha) {
		PreparedStatement st = null;
		try {
		conn = DB.getConnection();
		st = conn.prepareStatement("Update Conta SET senha = ? where idconta = ?");
		st.setString(1, novaSenha);
		st.setInt(2, obj.getId());
		st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteByID(Integer id) {
		PreparedStatement st = null;
		try {
		conn = DB.getConnection();
		st = conn.prepareStatement("DELETE FROM Review WHERE idconta = ?");
		st.setInt(1, id);
		st.executeUpdate();
		DB.closeStatement(st);
		st = conn.prepareStatement("DELETE FROM Conta WHERE idconta = ?");
		st.setInt(1, id);
		st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Conta findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("Select * from Conta where idconta = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			while (rs.next()) {
				Conta c = new Conta(rs.getInt("idconta"), rs.getString("username"), 
						rs.getString("email"), rs.getString("senha"));
				return c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return null;
	}
	
	public Conta findByUsername(String username) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("Select * from Conta where username = ?");
			st.setString(1, username);
			rs = st.executeQuery();
			while (rs.next()) {
				Conta c = new Conta(rs.getInt("idconta"), rs.getString("username"), 
						rs.getString("email"), rs.getString("senha"));
				return c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return null;
	}

	public Conta findByEmail(String email) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("Select * from Conta where email = ?");
			st.setString(1, email);
			rs = st.executeQuery();
			while (rs.next()) {
				Conta c = new Conta(rs.getInt("idconta"), rs.getString("username"), 
						rs.getString("email"), rs.getString("senha"));
				return c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return null;
	}
	
	@Override
	public List<Conta> findAll() {
		List<Conta> retorno = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery("Select * from Conta ORDER BY idconta");
			while (rs.next()) {
				retorno.add(new Conta(rs.getInt("idconta"), rs.getString("username"), 
						rs.getString("email"), rs.getString("senha")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return retorno;
	}
	
	public boolean autenticarConta(String username, String senha) {
		for (Conta atual : findAll()) {
			if (atual.getUsername().equals(username) && atual.getSenha().equals(senha)) {
					return true;
			}
		}
		return false;
	}
	
	public boolean disponibilidadeDeDados(String email, String username) {
		List<Conta> checagem = findAll();
		for (Conta atual : checagem) {
			if (atual.getUsername().equals(username) || atual.getEmail().equals(email)) {
					return false;
			}
		}
		return true;
	}
}
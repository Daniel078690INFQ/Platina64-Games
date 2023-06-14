package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import db.DB;
import entities.Jogo;

public class JogoDaoJDBC implements JogoDao{
	Connection conn = DB.getConnection();

	@Override
	public Jogo findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT idjogo, nome, descricao, DATE_FORMAT(dataLancamento, '%d/%m/%y') "
					+ "as dataLancamentoString, "
					+ "desenvolvedor, genero, conquistas from Jogo where idjogo = ?");;
			st.setInt(1, id);
			rs = st.executeQuery();
			while (rs.next()) {
				Jogo c = new Jogo(rs.getInt("idjogo"), rs.getString("nome"), 
						rs.getString("descricao"), rs.getString("dataLancamentoString"), rs.getString("desenvolvedor"),
						rs.getString("genero"), rs.getInt("conquistas"));
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
	public List<Jogo> findAll() {
		List<Jogo> retorno = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery("SELECT idjogo, nome, descricao, DATE_FORMAT(dataLancamento, '%d/%m/%y') "
					+ "as dataLancamentoString, "
					+ "desenvolvedor, genero, conquistas from Jogo where idjogo = ?");
			while (rs.next()) {
				retorno.add(new Jogo(rs.getInt("idjogo"), rs.getString("nome"), 
						rs.getString("descricao"), rs.getString("dataLancamentoString"), rs.getString("desenvolvedor"),
						rs.getString("genero"), rs.getInt("conquistas")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return retorno;
	}
}
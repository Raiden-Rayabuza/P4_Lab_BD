package br.com.fateczl.p3_siga2_client.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fateczl.p3_siga2_client.model.Avaliacao;

public class AvaliacaoDao {
	private GenericDao gDao;

	public AvaliacaoDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	public List<Avaliacao> getAvaliacao() throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();

		List<Avaliacao> avaliacao = new ArrayList<Avaliacao>();
		String sql_query = ("SELECT * FROM avaliacao");
		PreparedStatement ps = c.prepareStatement(sql_query.toString());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Avaliacao a = new Avaliacao();

			a.setCod(rs.getInt("cod_avaliacao"));
			a.setTipo(rs.getString("tipo"));
			
			avaliacao.add(a);
		}
		rs.close();
		ps.close();
		c.close();
		return avaliacao;
	}
}

package br.com.fateczl.p3_siga2_client.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fateczl.p3_siga2_client.model.Disciplina;

public class DisciplinaDao {
	private GenericDao gDao;

	public DisciplinaDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	public List<Disciplina> getDisciplina() throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();

		List<Disciplina> disciplina = new ArrayList<Disciplina>();
		String sql_query = ("SELECT * FROM disciplina");
		PreparedStatement ps = c.prepareStatement(sql_query.toString());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Disciplina d = new Disciplina();

			d.setCod_disciplina(rs.getString("cod_disciplina"));
			d.setNome(rs.getString("nome"));
			d.setNum_aulas(rs.getInt("num_aulas"));
			d.setSigla(rs.getString("sigla"));
			d.setTurno(rs.getString("turno"));
			disciplina.add(d);
		}
		rs.close();
		ps.close();
		c.close();
		return disciplina;
	}
}

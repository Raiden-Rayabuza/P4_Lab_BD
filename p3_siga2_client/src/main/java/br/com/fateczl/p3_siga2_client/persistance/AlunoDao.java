package br.com.fateczl.p3_siga2_client.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fateczl.p3_siga2_client.model.Aluno;

public class AlunoDao {
	
	private GenericDao gDao;
	
	public AlunoDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	public List<Aluno> getAlunos() throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		
		List<Aluno> aluno = new ArrayList<Aluno>();
		String sql_query = ("SELECT ra, nome FROM aluno");
		PreparedStatement ps = c.prepareStatement(sql_query.toString());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Aluno a = new Aluno();
			
			a.setRa(rs.getString("ra"));
			a.setNome(rs.getString("nome"));
			aluno.add(a);
		}
		rs.close();
		ps.close();
		c.close();
		return aluno;
	}
}

package br.com.fateczl.p3_siga2_client.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fateczl.p3_siga2_client.persistance.GenericDao;
import br.com.fateczl.p3_siga2_client.model.Aluno;
import br.com.fateczl.p3_siga2_client.model.Disciplina;
import br.com.fateczl.p3_siga2_client.model.Falta;

public class FaltasDao {
	
	private GenericDao gDao;
	
	public List<Falta> getFaltas() throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		
		List<Falta> falta = new ArrayList<Falta>();
		String sql_query = ("SELECT * FROM falta");
		PreparedStatement ps = c.prepareStatement(sql_query.toString());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Falta f = new Falta();
			Aluno a = new Aluno();
			Disciplina d = new Disciplina();
			
			a.setRa(rs.getString("ra"));
			d.setCod_disciplina(rs.getString("cod_disciplina"));
			
			f.setAluno(a);
			f.setDisciplina(d);
			f.setData(rs.getString("data_falta"));
			f.setPresenca(rs.getString("presenca"));
			falta.add(f);
		}
		rs.close();
		ps.close();
		c.close();
		return falta;
	}
}

package br.com.fateczl.p3_siga2_client.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fateczl.p3_siga2_client.model.Aluno;
import br.com.fateczl.p3_siga2_client.model.Avaliacao;
import br.com.fateczl.p3_siga2_client.model.Disciplina;
import br.com.fateczl.p3_siga2_client.model.Notas;

public class NotasDao {
	
	private GenericDao gDao;
	
	public List<Notas> getNotas() throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		
		List<Notas> nota = new ArrayList<Notas>();
		String sql_query = ("SELECT * FROM notas");
		PreparedStatement ps = c.prepareStatement(sql_query.toString());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Notas n = new Notas();
			Aluno a = new Aluno();
			Disciplina d = new Disciplina();
			Avaliacao ava = new Avaliacao();
			
			a.setRa(rs.getString("ra"));
			d.setCod_disciplina(rs.getString("cod_disciplina"));
			ava.setCod(rs.getInt("cod_avaliacao"));
			
			n.setAluno(a);
			n.setDisciplina(d);
			n.setAvaliacao(ava);
			n.setNota(rs.getInt("nota"));
			nota.add(n);
		}
		rs.close();
		ps.close();
		c.close();
		return nota;
	}
}

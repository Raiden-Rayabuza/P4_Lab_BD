package br.com.fateczl.p3_siga2_client.persistance;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDao {
	private Connection c;
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		String hostNome = "127.0.0.1";
		String bdNome = "Fatec";
		String user = "Siga";
		String senha = "p4_prova";
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		c = DriverManager.getConnection(String.format("jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s",hostNome,bdNome,user,senha));
		return c;
	}
}

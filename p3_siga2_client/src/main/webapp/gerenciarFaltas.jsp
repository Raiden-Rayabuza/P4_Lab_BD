<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ page import="br.com.fateczl.p3_siga2_client.persistance.GenericDao"%>
<%@ page import="br.com.fateczl.p3_siga2_client.persistance.NotasDao"%>
<%@ page import="br.com.fateczl.p3_siga2_client.persistance.AlunoDao"%>
<%@ page
	import="br.com.fateczl.p3_siga2_client.persistance.AvaliacaoDao"%>
<%@ page
	import="br.com.fateczl.p3_siga2_client.persistance.DisciplinaDao"%>
<%@ page import="br.com.fateczl.p3_siga2_client.model.Aluno"%>
<%@ page import="br.com.fateczl.p3_siga2_client.model.Avaliacao"%>
<%@ page import="br.com.fateczl.p3_siga2_client.model.Disciplina"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="StyleSheets/index.css"
	media="screen">
<link rel="stylesheet" type="text/css" href="StyleSheets/table.css"
	media="screen">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="StyleSheets/normalize.css"
	media="screen">
<title>SIGA 2.0</title>
</head>
<body>
	<header id="navbar">
		<a href="index.jsp"><img src="Imgs/seta_voltar.png"
			alt="Logo Paulistão"></a>
		<p>Voltar</p>
	</header>
	<section id="opt-section">
		<div class="caixa-options">
			<div class="opt-container">
				<form action="Faltas" method="post">
					<h3>
						<strong>Chamada</strong>
					</h3>
					<table>
						<tr>
							<td><label for="alu">Alunos: </label> <select name="alu"
								id="alu">
									<option value="" selected></option>
									<% 
									List<Aluno> listaAluno = new ArrayList<Aluno>();
									GenericDao gDao = new GenericDao();
									AlunoDao aDao = new AlunoDao(gDao);
									listaAluno.addAll(aDao.getAlunos());
									for(Aluno a : listaAluno){
								%>
									<option value=<%=a.getRa() %>><%=a.getNome() %></option>
									<%
									}
								%>
							</select> 
							<label for="dis">Disciplina: </label> 
							<select name="dis" id="dis">
									<option value="" selected></option>
									<% 
									List<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
									DisciplinaDao dDao = new DisciplinaDao(gDao);
									listaDisciplina.addAll(dDao.getDisciplina());
									for(Disciplina d : listaDisciplina){
								%>
									<option value=<%=d.getCod_disciplina() %>><%=d.getNome() %></option>
									<%
									}
								%>
							</select> 
							<label for="data">Data de Falta: </label> 
							<input type="date" id="data" name="data" /> 
							<label for="presenca">Presença: </label> 
							<input type="text" id="presenca" name="presenca" minlength="4" maxlength="4" />
							</td>
						</tr>
						<tr id="opt_container_buttons">
							<td><input type="submit" id="btn" name="btn" value="Inserir"></td>
							<td><input type="submit" id="btn" name="btn" value="Alterar"></td>
							<td><input type="submit" id="btn" name="btn" value="Deletar"></td>
							<td><input type="submit" id="btn" name="btn" value="Buscar"></td>
							<td><input type="submit" id="btn" name="btn" value="Listar"></td>
							<td><input type="submit" id="btn" name="btn"
								value="Relatorio"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="table-wrapper">
			<h2>Grupos:</h2>
			<c:if test="${not empty listaFalta }">
				<table class="fl-table">
					<thead>
						<tr>
							<th>RA</th>
							<th>Aluno</th>
							<th>Disciplina</th>
							<th>Data</th>
							<th>Presença</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="f" items="${listaFalta }">
							<tr>
								<td><c:out value="${f.aluno.ra }" /></td>
								<td><c:out value="${f.aluno.nome }" /></td>
								<td><c:out value="${f.disciplina.nome }" /></td>
								<td><c:out value="${f.data }" /></td>
								<td><c:out value="${f.presenca }" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</section>
	<br />
	<br />
	<div align="center">
		<h2>
			<c:out value="${erro}" />
		</h2>
	</div>
	<div align="center">
		<h3>
			<c:out value="${saida}" />
		</h3>
	</div>
	<footer> </footer>
</body>
</html>
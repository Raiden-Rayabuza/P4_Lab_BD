package br.com.fateczl.p3_siga2_client.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fateczl.p3_siga2_client.consumer.NotasConsumer;
import br.com.fateczl.p3_siga2_client.consumer.RelatorioNotasConsumer;
import br.com.fateczl.p3_siga2_client.model.Aluno;
import br.com.fateczl.p3_siga2_client.model.Avaliacao;
import br.com.fateczl.p3_siga2_client.model.Disciplina;
import br.com.fateczl.p3_siga2_client.model.Notas;

/**
 * Servlet implementation class NotasServlet
 */
@WebServlet("/Notas")
public class NotasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NotasServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ra = request.getParameter("alu");
		String dis = request.getParameter("dis");
		String ava = request.getParameter("ava");
		String nota = request.getParameter("nota");
		String operacao = request.getParameter("btn");
		String erro = "";
		
		Notas n = new Notas();
		Aluno a = new Aluno();
		Disciplina d = new Disciplina();
		Avaliacao avalia = new Avaliacao();
		NotasConsumer nc = new NotasConsumer();
		
		if(operacao.equals("Inserir") && ra != null && dis != null && ava != null && nota != null) {
			a.setRa(ra);
			d.setCod_disciplina(dis);
			avalia.setCod(Integer.parseInt(ava));
			
			n.setAluno(a);
			n.setDisciplina(d);
			n.setAvaliacao(avalia);
			n.setNota(Integer.parseInt(nota));
			String saida = nc.iudNotas(n, operacao);
			request.setAttribute("saida", saida);
		}
		else {
			if(operacao.equals("Inserir")) {
				erro = "Não deixe os campos em branco";
			}
		}
		if(operacao.equals("Alterar") && ra != null && dis != null && ava != null && nota != null) {
			a.setRa(ra);
			d.setCod_disciplina(dis);
			avalia.setCod(Integer.parseInt(ava));
			n.setAluno(a);
			n.setAvaliacao(avalia);
			n.setDisciplina(d);
			n.setNota(Integer.parseInt(nota));
			String saida = nc.iudNotas(n, operacao);
			request.setAttribute("saida", saida);
		}
		else {
			if(operacao.equals("Alterar")) {
				erro = "Não deixe os campos em branco";
			}
		}
		if(operacao.equals("Deletar") && ra != null && dis != null && ava != null) {
			a.setRa(ra);
			d.setCod_disciplina(dis);
			avalia.setCod(Integer.parseInt(ava));
			
			n.setAluno(a);
			n.setDisciplina(d);
			n.setAvaliacao(avalia);
			String saida = nc.iudNotas(n, operacao);
			request.setAttribute("saida", saida);
		}
		else {
			if(operacao.equals("Deletar")) {
				erro = "RA é necessário";
			}
		}
		if(operacao.equals("Buscar") && !(ra.isEmpty())) {
			a.setRa(ra);
			n.setAluno(a);
			List<Notas> listaNotas = nc.consultaNota(n);
			request.setAttribute("listaNotas", listaNotas);
		}
		else {
			if(operacao.equals("Buscar")) {
				erro = "RA é necessário";
			}
		}
		if(operacao.equals("Listar")) {
			List<Notas> listaNotas = nc.listarNotas();
			request.setAttribute("listaNotas", listaNotas);
		}
		if(operacao.equals("Relatorio")) {
			RelatorioNotasConsumer rnc = new RelatorioNotasConsumer();
			InputStream is = rnc.getRelatorioNotas();
			
			response.setContentType("application/pdf");
			ServletOutputStream sos = response.getOutputStream();
			byte[] bytes = new byte[4096];
			int tamanho;
			
			while((tamanho = is.read(bytes)) > 0) {
				sos.write(bytes,0,tamanho);
			}
			sos.flush();
			sos.close();
		}
		if(!operacao.equals("Relatorio")) {
			RequestDispatcher rd = request.getRequestDispatcher("gerenciarNotas.jsp");
			if(!erro.equals("")) {
				request.setAttribute("erro", erro);
			}
			rd.forward(request, response);
		}
	}

}

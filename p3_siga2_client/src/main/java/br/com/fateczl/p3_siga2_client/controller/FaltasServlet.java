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

import br.com.fateczl.p3_siga2_client.consumer.FaltaConsumer;
import br.com.fateczl.p3_siga2_client.consumer.RelatorioFaltaConsumer;
import br.com.fateczl.p3_siga2_client.model.Aluno;
import br.com.fateczl.p3_siga2_client.model.Disciplina;
import br.com.fateczl.p3_siga2_client.model.Falta;

/**
 * Servlet implementation class FaltasServlet
 */
@WebServlet("/Faltas")
public class FaltasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaltasServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ra = request.getParameter("alu");
		String dis = request.getParameter("dis");
		String data = request.getParameter("data");
		String presenca = request.getParameter("presenca");
		String operacao = request.getParameter("btn");
		String erro = "";
		
		Falta f = new Falta();
		Aluno a = new Aluno();
		Disciplina d = new Disciplina();
		FaltaConsumer nc = new FaltaConsumer();
		
		if(operacao.equals("Inserir") && ra != null && dis != null && data != null && presenca != null) {
			a.setRa(ra);
			d.setCod_disciplina(dis);
			
			f.setAluno(a);
			f.setDisciplina(d);
			f.setData(data);
			f.setPresenca(presenca);
			String saida = nc.iudFalta(f, operacao);
			request.setAttribute("saida", saida);
		}
		else {
			if(operacao.equals("Inserir")) {
				erro = "Não deixe os campos em branco";
			}
		}
		if(operacao.equals("Alterar") && ra != null && dis != null && data != null && presenca != null) {
			a.setRa(ra);
			d.setCod_disciplina(dis);
			
			f.setAluno(a);
			f.setDisciplina(d);
			f.setData(data);
			f.setPresenca(presenca);
			String saida = nc.iudFalta(f, operacao);
			request.setAttribute("saida", saida);
		}
		else {
			if(operacao.equals("Alterar")) {
				erro = "Não deixe os campos em branco";
			}
		}
		if(operacao.equals("Deletar") && ra != null) {
			a.setRa(ra);
			d.setCod_disciplina(dis);
			
			f.setAluno(a);
			f.setDisciplina(d);
			f.setData(data);
			f.setPresenca(presenca);
			String saida = nc.iudFalta(f, operacao);
			request.setAttribute("saida", saida);
		}
		else {
			if(operacao.equals("Deletar")) {
				erro = "RA é necessário";
			}
		}
		if(operacao.equals("Buscar") && !(ra.isEmpty())) {
			a.setRa(ra);
			f.setAluno(a);
			List<Falta> listaFalta = nc.consultaFalta(f);
			request.setAttribute("listaFalta", listaFalta);
		}
		else {
			if(operacao.equals("Buscar")) {
				erro = "RA é necessário";
			}
		}
		if(operacao.equals("Listar")) {
			List<Falta> listaFalta = nc.listarFaltas();
			request.setAttribute("listaFalta", listaFalta);
		}
		if(operacao.equals("Relatorio")) {
			RelatorioFaltaConsumer rnc = new RelatorioFaltaConsumer();
			InputStream is = rnc.getRelatorioFalta();
			
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
			RequestDispatcher rd = request.getRequestDispatcher("gerenciarFaltas.jsp");
			if(!erro.equals("")) {
				request.setAttribute("erro", erro);
			}
			rd.forward(request, response);
		}
	}
}

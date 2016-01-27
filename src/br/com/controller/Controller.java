package br.com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dao.PessoaDAO;
import br.com.modelo.enumerador.EstadoCivil;
import br.com.modelo.negocio.Pessoa;

@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		String acao=request.getParameter("acao"), pagina=null;
		Pessoa pessoa = new Pessoa();
		PessoaDAO pessoaDao;
		List <Pessoa> pessoas = null;
		try {
			pessoaDao = new PessoaDAO();
			if ((acao == null) || (acao.equals("listar"))) {
				pessoas = pessoaDao.listar();
				request.setAttribute("pessoas", pessoas);
				pagina = "index.jsp";
			}
			if ((acao != null) && (acao.equals("incluir"))) {
				request.setAttribute("estadosCivis", EstadoCivil.SEPARADO);
				pagina = "entrada.jsp";		
			}
			if ((acao != null) && (acao.equals("alterar"))) {
				pessoa = pessoaDao.consultar(Long.valueOf(request.getParameter("id")));
				if (pessoa == null){
					request.setAttribute("erro", "Erro ao localizar para alteração");
					pagina = "index.jsp";
				}
				else {
					request.setAttribute("pessoa", pessoa);
					request.setAttribute("estadosCivis", EstadoCivil.valueOf(pessoa.getEstadoCivil().toString()));
					pagina = "entrada.jsp";		
				}
			}
			if (((acao != null) && (acao.equals("Cancelar")))|| acao == null) {
				pessoas = pessoaDao.listar();
				request.setAttribute("pessoas", pessoas);
				pagina = "index.jsp";
			}
			if ((acao != null) && (acao.equals("Salvar"))) {
				boolean erro=false;
				try {
					// Recebe valores do formulario
					if (request.getParameter("nome") != null)
						pessoa.setNome(request.getParameter("nome"));
					if (request.getParameter("email") != null)
						pessoa.setEmail(request.getParameter("email"));
					if (request.getParameter("telefone") != null)
						pessoa.setTelefone(request.getParameter("telefone"));
					if (request.getParameter("dtaNasc") != null) {
						DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						df.setLenient(false);
						Date data = df.parse(request.getParameter("dtaNasc"));
						pessoa.setDtaNasc(data);
					}
					if (request.getParameter("estCivil") != null)
						pessoa.setEstadoCivil(EstadoCivil.valueOf(request.getParameter("estCivil")));
					
					
					// Salva: inclui ou altera
					if (request.getParameter("id").equals("")) { 
						if (pessoaDao.incluir(pessoa) > 0)
							request.setAttribute("mensagem", "Incluído com sucesso");
						else
							request.setAttribute("erro", "Erro de inclusão");
					}
					else { 
						pessoa.setId(Long.valueOf(request.getParameter("id")));
						if (pessoaDao.alterar(pessoa) > 0)
							request.setAttribute("mensagem", "Alterado com sucesso");
						else
							request.setAttribute("erro", "Erro de alteração");
					}
					pessoas = pessoaDao.listar();
					request.setAttribute("pessoas", pessoas);
					pagina = "index.jsp";
				}
				catch (NumberFormatException e) {
					erro = true;
					request.setAttribute("erro", "Erro de conversao de numero");
				}
				catch (ParseException e) {
					erro = true;
					request.setAttribute("erro", "Erro de conversao de data");
				}
				if (erro) {
					request.setAttribute("estadosCivis", EstadoCivil.CASADO);
					pagina = "entrada.jsp";		
				}
			}
			if ((acao != null) && (acao.equals("excluir"))) {
				pessoa = pessoaDao.consultar(Long.valueOf(request.getParameter("id")));
				if (pessoaDao.excluir(pessoa) > 0)
					request.setAttribute("mensagem", "Excluído com sucesso");
				else
					request.setAttribute("erro", "Erro de exclusão");
				pessoas = pessoaDao.listar();
				request.setAttribute("pessoas", pessoas);
				pagina = "index.jsp";
			}
		} catch (SQLException e) {
			request.setAttribute("erro", "Erro de banco de dados ("+ e.getMessage() +")");
			pagina = "index.jsp";
		}
		dispatcher = request.getRequestDispatcher(pagina);
		dispatcher.forward(request, response);
	}

}

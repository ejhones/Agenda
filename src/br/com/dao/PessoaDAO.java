package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.modelo.enumerador.EstadoCivil;
import br.com.modelo.negocio.Pessoa;


public class PessoaDAO {
	private Connection con;
	
	public PessoaDAO() throws SQLException {
		this.con = ConnectionFactory.getConnection();
	}
	
	public int incluir(Pessoa pessoa) throws SQLException {
		if (pessoa == null)	return 0;
		PreparedStatement stmt = con.prepareStatement("INSERT INTO pessoa (nome, email, telefone, dtaNasc, estCivil) values (?, ?, ?, ?, ?)");
		stmt.setString(1, pessoa.getNome());
		stmt.setString(2, pessoa.getEmail());
		stmt.setString(3, pessoa.getTelefone());
		java.sql.Date dta = new java.sql.Date(pessoa.getDtaNasc().getTime());
		stmt.setDate(4, dta);
		stmt.setString(5, (pessoa.getEstadoCivil()).toString());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}
	

	public Pessoa consultar(Long id) throws SQLException {
		PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM pessoa WHERE id=?");
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		Pessoa pessoa = null;
		if (rs.next()) {
			pessoa = new Pessoa();
			pessoa.setId(rs.getLong("id"));
			pessoa.setNome(rs.getString("nome"));
			pessoa.setEmail(rs.getString("email"));
			pessoa.setTelefone(rs.getString("telefone"));
			pessoa.setDtaNasc(rs.getDate("dtaNasc"));
			pessoa.setEstadoCivil(EstadoCivil.valueOf(rs.getString("estCivil")));
		}
		rs.close();
		stmt.close();
		return pessoa;	
	}
	
	public List<Pessoa> listar() throws SQLException {
		PreparedStatement stmt = this.con.prepareStatement("SELECT * FROM pessoa");
		ResultSet rs = stmt.executeQuery();
	    List<Pessoa> contatos = new ArrayList<Pessoa>();
	    while (rs.next()) {
		    Pessoa pessoa = new Pessoa();
		    pessoa.setId(rs.getLong("id"));
			pessoa.setNome(rs.getString("nome"));
			pessoa.setEmail(rs.getString("email"));
			pessoa.setTelefone(rs.getString("telefone"));
			pessoa.setDtaNasc(rs.getDate("dtaNasc"));
			pessoa.setEstadoCivil(EstadoCivil.valueOf(rs.getString("estCivil")));
		    contatos.add(pessoa);
		}
		rs.close();
		stmt.close();
		return contatos;	
	}
	
	public int alterar(Pessoa pessoa) throws SQLException {
		if (pessoa == null) return 0;
		PreparedStatement stmt = this.con.prepareStatement("UPDATE pessoa SET nome=?, email=?, telefone=?, dtaNasc=?, estCivil=? WHERE id=?");
		stmt.setString(1, pessoa.getNome());
		stmt.setString(2, pessoa.getEmail());
		stmt.setString(3, pessoa.getTelefone());
		java.sql.Date dta = new java.sql.Date(pessoa.getDtaNasc().getTime());
		stmt.setDate(4, dta);
		stmt.setString(5, (pessoa.getEstadoCivil()).toString());
		stmt.setLong(6, pessoa.getId());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}
	
	public int excluir(Pessoa pessoa) throws SQLException {
		if (pessoa == null) return 0;
		PreparedStatement stmt = this.con.prepareStatement("DELETE FROM pessoa WHERE id=?");
		stmt.setLong(1, pessoa.getId());
		int retorno = stmt.executeUpdate();
		stmt.close();
		return retorno;
	}
}

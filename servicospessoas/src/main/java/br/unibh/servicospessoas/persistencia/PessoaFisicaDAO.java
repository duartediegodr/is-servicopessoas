package br.unibh.servicospessoas.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.unibh.servicospessoas.entidades.PessoaFisica;

public class PessoaFisicaDAO implements DAO<PessoaFisica, Long> {

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public PessoaFisica find(Long id) {
		PessoaFisica pessoafisica = null;
		try {
			String sql = "select * from pessoa_fisica where id = ?";
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(sql); 
			p.setLong(1, id);
			
			ResultSet res = p.executeQuery();
			if (res.next()){
				pessoafisica = new PessoaFisica(
							res.getLong("id"),
							res.getString("nome"), 
							res.getString("endereco"), 
							res.getString("telefone"), 
							res.getString("cpf"), 
							res.getString("email"), 
							res.getDate("data_nascimento"), 
							res.getString("sexo")
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}
		return pessoafisica;
	}

	@Override
	public void insert(PessoaFisica t) {
		try {
			String sql = "INSERT INTO pessoa_fisica (nome, endereco,telefone,cpf,email,data_nascimento,sexo)"+
				"VALUES (?,?,?,?,?,?,?)";
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(sql); 
			
			p.setString(1, t.getNome());
			p.setString(2, t.getEndereco());
			p.setString(3, t.getTelefone());
			p.setString(4, t.getCpf());
			p.setString(5, t.getEmail());
			p.setString(6, df.format(t.getDataNascimento()));
			p.setString(7, t.getSexo());
			
			p.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}
		
	}

	@Override
	public void update(PessoaFisica t) {
		try {
			String sql = "update pessoa_fisica set nome = ?, endereco = ?,"
					+ " telefone = ?, cpf = ?, email = ?, data_nascimento = ?, sexo=? where id = ?";
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(sql); 
			
			p.setString(1, t.getNome());
			p.setString(2, t.getEndereco());
			p.setString(3, t.getTelefone());
			p.setString(4, t.getCpf());
			p.setString(5, t.getEmail());
			p.setString(6, df.format(t.getDataNascimento()));
			p.setString(7, t.getSexo());
			p.setLong(8, t.getId());
			
			p.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}
		
	}

	@Override
	public void delete(PessoaFisica t) {
		try {
			String sql = "DELETE FROM pessoa_fisica WHERE ID = ?";
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(sql); 
			
			p.setLong(1, t.getId());
			p.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}
		
	}

	@Override
	public List<PessoaFisica> findAll() {
		List<PessoaFisica> listaPesoaFisica = new ArrayList<PessoaFisica>();
		try {
			
			String sql = "select * from pessoa_fisica";
			ResultSet res = JDBCUtil.getConnection().
					prepareStatement(sql).executeQuery();
			
			while (res.next()) {
				listaPesoaFisica.add(
						new PessoaFisica(
							res.getLong("id"),
							res.getString("nome"), 
							res.getString("endereco"), 
							res.getString("telefone"), 
							res.getString("cpf"), 
							res.getString("email"), 
							res.getDate("data_nascimento"), 
							res.getString("sexo")
						)
					);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}
		return listaPesoaFisica;
	}

	public PessoaFisica find(String nome) {
		PessoaFisica pessoafisica = null;
		try {
			String sql = "select * from pessoa_fisica where nome like ?";
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(sql); 
			p.setString(1, nome+"%");
			
			ResultSet res = p.executeQuery();
			if (res.next()){
				pessoafisica = new PessoaFisica(
							res.getLong("id"),
							res.getString("nome"), 
							res.getString("endereco"), 
							res.getString("telefone"), 
							res.getString("cpf"), 
							res.getString("email"), 
							res.getDate("data_nascimento"), 
							res.getString("sexo")
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}
		return pessoafisica;
	}
}

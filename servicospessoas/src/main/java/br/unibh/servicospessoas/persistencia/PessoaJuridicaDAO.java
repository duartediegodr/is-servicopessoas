package br.unibh.servicospessoas.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.unibh.servicospessoas.entidades.PessoaJuridica;

public class PessoaJuridicaDAO  implements DAO<PessoaJuridica, Long> {

private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public PessoaJuridica find(Long id) {
		PessoaJuridica PessoaJuridica = null;
		try {
			String sql = "select * from pessoa_juridica where id = ?";
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(sql); 
			p.setLong(1, id);
			
			ResultSet res = p.executeQuery();
			if (res.next()){
				PessoaJuridica = new PessoaJuridica(res.getLong("id"), 
						res.getString("nome"), 
						res.getString("endereco"), 
						res.getString("telefone"), 
						res.getString("cnpj"), 
						res.getDate("data_constituicao"), 
						res.getString("site")
					);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}
		return PessoaJuridica;
	}

	@Override
	public void insert(PessoaJuridica t) {
		try {
			String sql = "INSERT INTO pessoa_juridica (nome, endereco,telefone,cnpj,data_constituicao, site)"+
				"VALUES (?,?,?,?,?,?)";
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(sql); 
			
			p.setString(1, t.getNome());
			p.setString(2, t.getEndereco());
			p.setString(3, t.getTelefone());
			p.setString(4, t.getCnpj());
			p.setString(5, df.format(t.getDataConstituicao()));
			p.setString(6, t.getSite());
			
			p.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}
		
	}

	@Override
	public void update(PessoaJuridica t) {
		try {
			String sql = "update pessoa_juridica set nome = ?, endereco = ?,"
					+ "telefone = ?,cnpj = ?,data_constituicao = ?, site  = ? where id = ?";
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(sql); 
			
			p.setString(1, t.getNome());
			p.setString(2, t.getEndereco());
			p.setString(3, t.getTelefone());
			p.setString(4, t.getCnpj());
			p.setString(5, df.format(t.getDataConstituicao()));
			p.setString(6, t.getSite());
			p.setLong(7, t.getId());
			
			p.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}
		
	}

	@Override
	public void delete(PessoaJuridica t) {
		try {
			String sql = "DELETE FROM pessoa_juridica WHERE ID = ?";
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
	public List<PessoaJuridica> findAll() {
		List<PessoaJuridica> listaPesoaJuridica = new ArrayList<PessoaJuridica>();
		try {
			
			String sql = "select * from pessoa_juridica";
			ResultSet res = JDBCUtil.getConnection().
					prepareStatement(sql).executeQuery();
			
			while (res.next()) {
				listaPesoaJuridica.add(new PessoaJuridica(res.getLong("id"), 
								res.getString("nome"), 
								res.getString("endereco"), 
								res.getString("telefone"), 
								res.getString("cnpj"), 
								res.getDate("data_constituicao"), 
								res.getString("site"))
					);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}
		return listaPesoaJuridica;
	}

	public PessoaJuridica find(String nome) {
		PessoaJuridica PessoaJuridica = null;
		try {
			String sql = "select * from pessoa_juridica where nome like ?";
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(sql); 
			p.setString(1, nome+"%");
			
			ResultSet res = p.executeQuery();
			if (res.next()){
				PessoaJuridica = new PessoaJuridica(
						res.getLong("id"), 
						res.getString("nome"), 
						res.getString("endereco"), 
						res.getString("telefone"), 
						res.getString("cnpj"), 
						res.getDate("data_constituicao"), 
						res.getString("site")
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}
		return PessoaJuridica;
	}
}

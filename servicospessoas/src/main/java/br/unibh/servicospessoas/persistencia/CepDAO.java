package br.unibh.servicospessoas.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.unibh.servicospessoas.entidades.Cep;

public class CepDAO implements DAO<Cep, Long> {

	@Override
	public Cep find(Long cep) {
		Cep cepObject = null;
		try{
			String sql = "select * from tb_cep where cep = ?";
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(sql); 
			p.setLong(1, cep);
			
			ResultSet res = p.executeQuery();
			if (res.next()){
				cepObject = new Cep(
						res.getLong("cep"), 
						res.getString("endereco"), 
						res.getString("cidade") 
					);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}
		return cepObject;
	}

	@Override
	public void insert(Cep t) {
		try {
			String sql = "INSERT INTO tb_cep (cep, endereco,cidade)"+
				"VALUES (?,?,?)";
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(sql);
			
			p.setLong(1, t.getCep());
			p.setString(2, t.getEndereco());
			p.setString(3, t.getCidade());
			
			p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}
	}

	@Override
	public void update(Cep t) {
		try {
			String sql = "update tb_cep set endereco = ?,"
					+ " cidade = ? where cep = ?";
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(sql); 
			
			p.setString(1, t.getEndereco());
			p.setString(2, t.getCidade());
			p.setLong(3, t.getCep());
			
			p.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}
		
	}

	@Override
	public void delete(Cep t) {
		try {
			String sql = "DELETE FROM tb_cep WHERE cep = ?";
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(sql); 
			
			p.setLong(1, t.getCep());
			p.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}
		
	}

	@Override
	public List<Cep> findAll() {
		List<Cep> listaCep = new ArrayList<Cep>();
		try {
			
			String sql = "select * from tb_cep";
			ResultSet res = JDBCUtil.getConnection().
					prepareStatement(sql).executeQuery();
			
			while (res.next()) {
				listaCep.add(
						new Cep(
							res.getLong("cep"),
							res.getString("endereco"), 
							res.getString("cidade") 
						)
					);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}
		return listaCep;
	}
	
	public List<Cep>findCep(String endereco){
		List<Cep> listCep = new ArrayList<Cep>();
		try {
			String sql = "select * from tb_cep where endereco like ?";
			PreparedStatement p = JDBCUtil.getConnection().prepareStatement(sql); 
			p.setString(1, "%"+endereco+"%");
			
			ResultSet res = p.executeQuery();
			while (res.next()){
				Cep cep = new Cep(
							res.getLong("cep"), 
							res.getString("endereco"), 
							res.getString("cidade") 
						);
				listCep.add(cep);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}
		
		return listCep;
		
	}
}

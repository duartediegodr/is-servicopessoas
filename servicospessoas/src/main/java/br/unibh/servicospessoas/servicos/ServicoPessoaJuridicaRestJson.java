package br.unibh.servicospessoas.servicos;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.unibh.servicospessoas.entidades.PessoaJuridica;
import br.unibh.servicospessoas.persistencia.PessoaJuridicaDAO;

@Path("/pessoajuridica")
public class ServicoPessoaJuridicaRestJson {
	

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listar")
    public List<PessoaJuridica> listar() {
    	PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
        return dao.findAll();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscar/{id}")
    public PessoaJuridica buscarPessoaJuridica(@PathParam(value="id")String id){
    	PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
    	return dao.find(Long.parseLong(id));
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/salvar")
    public PessoaJuridica salva(PessoaJuridica pJ){
    	PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
    	dao.insert(pJ);
    	
    	return dao.find(pJ.getNome());
    }
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/atualizar/{id}")  
    public PessoaJuridica atualizaPessoaJuridica(@PathParam("id")long id, PessoaJuridica pJ){
    	PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
    	dao.update(pJ);
    	return dao.find(id);
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/excluir/{id}")
    public void excluirPessoaJuridica(@PathParam("id")long id){
    	PessoaJuridicaDAO dao = new PessoaJuridicaDAO();
    	PessoaJuridica pJ = new PessoaJuridica();
    	pJ=dao.find(id);

    	dao.delete(pJ);
    }
    
    
    
    
    
    
    
}

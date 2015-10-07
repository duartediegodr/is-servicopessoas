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

import br.unibh.servicospessoas.entidades.Cep;
import br.unibh.servicospessoas.persistencia.CepDAO;

@Path("/cep")
public class ServicoCepRestJson {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listar")
    public List<Cep> listar() {
    	CepDAO dao = new CepDAO();
        return dao.findAll();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listar/{endereco}")
    public List<Cep> buscarCepPorEndereco(@PathParam(value="endereco")String endereco){
    	CepDAO dao = new CepDAO();
    	return dao.findCep(endereco);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscar/{cep}")
    public Cep buscarCep(@PathParam(value="cep")long cep){
    	CepDAO dao = new CepDAO();
    	return dao.find(cep);
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/salvar")
    public Cep salva(Cep c){
    	CepDAO dao = new CepDAO();
    	dao.insert(c);
    	
    	return dao.find(c.getCep());
    }
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/atualizar/{cep}")  
    public Cep atualizaCep(@PathParam("cep")long cep, Cep c){
    	CepDAO dao = new CepDAO();
    	dao.update(c);
    	return dao.find(cep);
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/excluir/{cep}")
    public String excluirCep(@PathParam("cep")long cep){
    	CepDAO dao = new CepDAO();
    	Cep pJ = new Cep();
    	pJ=dao.find(cep);

    	dao.delete(pJ);
    	
    	return "CEP excluido";
    }

}

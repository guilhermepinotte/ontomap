package br.com.restfull.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.*;

import pkg.Criador;
import pkg.Mapeador;


@Path("/ontomap")
public class Services {

	@GET
//	@Produces("application/json")
//	@Produces(MediaType.APPLICATION_JSON)
	@Produces("text/plain")
	public String showHelloWorld() {
		return "Ola mundo!";
	}

	@POST
	@Path("/service")
	@Consumes("application/json")
	@Produces("text/plain")
	public Response mappingService(String map) {
		String output = "Olá Mundo!";
		
		Criador criador = new Criador(map);
		criador.leMapa();
		System.out.println(criador);
		
		Mapeador mapeador = new Mapeador();
//		mapeador.fazMapeamento(criador.getPropositions());
		
		
//		criador.imprimeArray(criador.getNodeDataArray());
//		criador.imprimeArray(criador.getLinkDataArray());
		
		
				
		return Response.ok(output).header("Access-Control-Allow-Origin", "*").build();
	}
	
}

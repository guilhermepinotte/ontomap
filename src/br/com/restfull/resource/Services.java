package br.com.restfull.resource;

import java.io.File;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
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
//	@Produces("application/xml")
	public Response mappingService(String map) {
		String output = "Deu Zika!";
		System.out.println(output);
		
		
		File file = new File("mymodel.owl");
		
		Criador criador = new Criador(map);
		criador.leMapa();
		System.out.println(criador);
				
		Mapeador mapeador = new Mapeador();
		mapeador.fazMapeamento(criador.getPropositions());
		
		String mt = new MimetypesFileTypeMap().getContentType(file);
//		System.out.println(mt);
		
//		if (!file.exists()) 
//		    throw new WebApplicationException(404);
//		else
//			return Response.ok(file,mt)
//					.header("Access-Control-Allow-Origin", "*")
//					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
//					.build();
		
		return Response.ok(output)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.build();
		
//		if(file.exists())
//			
//		else
//			return Response.ok(output).header("Access-Control-Allow-Origin", "*").build();
	}
	
}

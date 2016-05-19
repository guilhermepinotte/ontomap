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

import pkg.Criador;
import pkg.Mapeador;
import pkg.Raciocinador;

@Path("/ontomap")
public class Services {

	@GET
	// @Produces("application/json")
	// @Produces(MediaType.APPLICATION_JSON)
	@Produces("text/plain")
	public String showHelloWorld() {
		return "Ola mundo!";
	}

	@POST
	@Path("/service")
	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public Response mappingService(String map) {
		File file = new File("mymodel.owl");

		Criador criador = new Criador(map);
		criador.leMapa();
		System.out.println(criador);

		Mapeador mapeador = new Mapeador();
		mapeador.fazMapeamento(criador.getPropositions());

//		String mt = new MimetypesFileTypeMap().getContentType(file);
		
		Raciocinador rac = new Raciocinador("mymodel.owl");
		rac.validation("mymodel.owl");
//		rac.validation(mapeador.getModel());
		
//		rac.getInstancesFromClass("Professor");

		Raciocinador.teste("mymodel.owl");

		 if (!file.exists())
			 throw new WebApplicationException(404);
		 else
			 return Response.ok(file,MediaType.APPLICATION_XML).build();
//		 .header("Access-Control-Allow-Origin", "*")
//		 .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
		
//		return Response.ok(output,MediaType.TEXT_PLAIN).build();

	}

}

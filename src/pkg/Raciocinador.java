package pkg;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.jena.base.Sys;
import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFWriter;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.shared.JenaException;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.PrintUtil;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.XSD;


public class Raciocinador {
	private Reasoner reasoner;
	private OntModel ontModel;
	private Model model;
	private InfModel infmodel;
	private String NS = "http://example.com/test#";
	
	public Raciocinador (String path) {
//		this.getOwlFile(path);
//		FileManager.get().readModel(this.model, path);
		
		Model data = FileManager.get().loadModel(path);
		this.model = data;
//		this.setReasoner(ReasonerRegistry.getOWLReasoner().bindSchema(this.ontModel));
//		this.setInfmodel(ModelFactory.createInfModel(this.reasoner, this.ontModel));
		
		this.setReasoner(ReasonerRegistry.getOWLReasoner().bindSchema(this.model));
		this.setInfmodel(ModelFactory.createInfModel(this.reasoner, this.model));
		
	}
	
	public static void teste (String ontoFile){
	    OntModel ontoModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);
	    try 
	    {
	        InputStream in = FileManager.get().open(ontoFile);
	        try 
	        {
	            ontoModel.read(in, null);
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
//	        LOGGER.info("Ontology " + ontoFile + " loaded.");
	    } 
	    catch (JenaException je) 
	    {
	        System.err.println("ERROR" + je.getMessage());
	        je.printStackTrace();
	        System.exit(0);
	    }
//	    return ontoModel;
	}
	
	
	public Reasoner getReasoner() {
		return this.reasoner;
	}

	public void setReasoner(Reasoner reasoner) {
		this.reasoner = reasoner;
	}
	
	public Model getOntModel() {
		return this.ontModel;
	}

	public void setOntModel(OntModel ontModel) {
		this.ontModel = ontModel;
	}
	
	public InfModel getInfmodel() {
		return this.infmodel;
	}

	public void setInfmodel(InfModel infmodel) {
		this.infmodel = infmodel;
	}
	
	private void getOwlFile (String fname) {
//		InputStream in = FileManager.get().open(path);
//	    if (in == null) throw new IllegalArgumentException( "File: " + path + " not found");
//	    
//	    try {
//	    	this.ontModel.read(in, null); //load model??
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		Model data = FileManager.get().loadModel(fname);
		FileManager.get().readModel(this.ontModel, fname);
		
//		this.setOntModel(data);
	}
	
	
	
	public void validation (String fname){
		Model data = FileManager.get().loadModel(fname);
//		InfModel infmodel = ModelFactory.createRDFSModel(data);
		InfModel infmodel = ModelFactory.createRDFSModel(data);
		ValidityReport validity = infmodel.validate();
		if (validity.isValid()) {
		    System.out.println("OK");
		} else {
		    System.out.println("Conflicts");
		    for (Iterator i = validity.getReports(); i.hasNext(); ) {
		        System.out.println(" - " + i.next());
		    }
		}
	}
	
	public void validation (OntModel m){
//		Model data = FileManager.get().loadModel(fname);
//		InfModel infmodel = ModelFactory.createRDFSModel(data);
		InfModel infmodel = m;
		ValidityReport validity = infmodel.validate();
		if (validity.isValid()) {
		    System.out.println("OK2");
		} else {
		    System.out.println("Conflicts");
		    for (Iterator i = validity.getReports(); i.hasNext(); ) {
		        System.out.println(" - " + i.next());
		    }
		}
	}



	public void getInstancesFromClass(String nameClass){
//		Resource resource = this.infmodel.getResource(c);
//		System.out.println(c + " *:");
//		this.printStatements(infmodel, resource, null, null);
		
//		OntModel m = (OntModel) this.ontModel;
		
//		OntClass c = m.getOntClass( NS + nameClass );
//		Resource r = this.model.getResource(NS + nameClass);
//		OntClass<Resource> c = this.model.getClass()(NS + nameClass);
		
		
//		System.out.println("ENTROU");
//		ExtendedIterator instances = r.listInstances();
		
//		while (instances.hasNext()) {
//			Individual thisInstance = (Individual) instances.next();
//			System.out.println("Found instance: " + thisInstance.toString());
//		}
//		
//		for (ExtendedIterator<? extends OntResource> instances = c.listInstances(); instances.hasNext(); ) {
//			
//		}
		
	}

	public void printStatements(Model m, Resource s, Property p, Resource o) {
	    for (StmtIterator i = m.listStatements(s,p,o); i.hasNext(); ) {
	        Statement stmt = i.nextStatement();
	        System.out.println(" - " + PrintUtil.print(stmt));
	    }
	}
	
}

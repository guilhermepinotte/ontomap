package pkg;

import java.io.Writer;
import java.util.LinkedList;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFWriter;
import org.apache.jena.rdf.model.Resource;

import models.Proposition;

public class Mapeador {
	private OntModel model;
	private String NS;
	
	public Mapeador() {
		super();
//		this.setModel(ModelFactory.createOntologyModel(OntModelSpec.OWL_LITE_MEM_RULES_INF)); //problemas aqui, PESQUISAR
		
		this.setModel(ModelFactory.createOntologyModel());
		this.setNS("http://example.com/test#"); // PESQUISAR SOBRE URIs
		
//		OntClass classFrom = this.model.createClass(this.getNS() + "Carro");
//		classFrom.createIndividual(this.getNS() + "fusca");
//		
//		RDFWriter w = this.model.getWriter("RDF/XML-ABBREV");
//////		w.setProperty("allowBadURIs", "true");
//////		w.setProperty("relativeURIs","");
//		w.write(this.model, System.out, "http://example.org/");
		
//		this.model.write(System.out);
		
		
	}

	public OntModel getModel() {
		return this.model;
	}

	public void setModel(OntModel model) {
		this.model = model;
	}

	public String getNS() {
		return this.NS;
	}

	public void setNS(String nS) {
		this.NS = nS;
	}
	
	/**
	 * Método que faz a lógica de mapeamento criando as listas de Classes, Individuals e DataType Objects
	 * 
	 * @author Guilherme N. Pinotte
	 * 
	 */
	public void fazMapeamento (LinkedList<Proposition> propositions){
		for (Proposition p : propositions) {
			switch (p.getRel().getLabel()) {
				case "are": //relação: classe-subclasse
					this.criaClasseSubClasse(p);
					break;
					
				case "equivalent to": //relação: classes equivalentes
					this.criaClassesEquivalentes(p);
					break;
					
				case "cannot be": //relação: classes disjuntas
					this.criaClassesDisjuntas(p);
					break;
					
				case "exact opposite of": //relação: complemento de classes
					this.criaComplementoDeClasses(p);
					break;
					
				case "is composed of": //relação: relação de todo-parte
//					this.criaRelaçãoTodoParte(p);
					break;
					
				case "is a": //relação: indivíduo-classe
					this.criaInstaciaDeClasse(p);
					break;
					
				case "same as": //relação: indivíduos iguais
//					this.criaIndividuosIguais(p);
					break;
					
				case "different from": //relação: indivíduos diferentes
//					this.criaIndividuosDiferentes(p);
					break;
					
				case "is an attibute of": //relação: dataTypeObject-classe
//					this.criaAtributoDeClasse(p);
					break;
					
				case "that is": //relação: dataTypeObject-type (Integer, String, ...) 
//					this.criaTipoDeAtributo(p);
					break;	
		
				default: //caso sem esteriótipo
					this.criaRelacaoSemEsteriotipo(p);
					break;
			}
		}
		this.imprimeOWL();
	}
	
	public void imprimeOWL () {
		
//		out = new FileWriter( "mymodel.xml" );
//		m.write( out, "RDF/XML-ABBREV" );
		
		RDFWriter w = this.model.getWriter("RDF/XML");
//		w.setProperty("allowBadURIs","true");
		w.write(this.model, System.out, "RDF/XML");
	}
	
	/**
	 * Método que cria relação de herança entre as classes (From vira subclasse de To)
	 * 
	 * @author Guilherme N. Pinotte
	 * 
	 */
	private void criaClasseSubClasse (Proposition p) {
		String to = p.getTo().getLabel();
		String from = p.getFrom().getLabel();
		OntClass classTo = this.model.createClass(this.getNS() + to);
		OntClass classFrom = this.model.createClass(this.getNS() + from);
		
		//fazendo a classe From virar superclasse da To (HERANÇA)
		classFrom.addSuperClass(classTo);
	}
	
	/**
	 * Método que cria duas classes equivalentes (From equivale a To)
	 * 
	 * @author Guilherme N. Pinotte
	 * 
	 */
	private void criaClassesEquivalentes (Proposition p) {
		String to = p.getTo().getLabel();
		String from = p.getFrom().getLabel();
		OntClass classTo = this.model.createClass(this.getNS() + to);
		OntClass classFrom = this.model.createClass(this.getNS() + from);
		
		classFrom.addEquivalentClass(classTo);
	}
	
	/**
	 * Método que cria duas classes disjuntas (From não possui indivíduos em comum com To)
	 * 
	 * @author Guilherme N. Pinotte
	 * 
	 */
	private void criaClassesDisjuntas (Proposition p) {
		String to = p.getTo().getLabel();
		String from = p.getFrom().getLabel();
		OntClass classTo = this.model.createClass(this.getNS() + to);
		OntClass classFrom = this.model.createClass(this.getNS() + from);
		
		classFrom.addDisjointWith(classTo);
	}
	
	/**
	 * Método que cria duas classes complementares (From complementa To)
	 * Exemplo: Fumantes e Não Fumantes são complementares
	 * 
	 * @author Guilherme N. Pinotte
	 * 
	 */
	private void criaComplementoDeClasses (Proposition p) {
		String to = p.getTo().getLabel();
		String from = p.getFrom().getLabel();
		OntClass classTo = this.model.createClass(this.getNS() + to);
		OntClass classFrom = this.model.createClass(this.getNS() + from);
		
		classFrom.convertToComplementClass(classTo);
	}

	/**
	 * Método que cria instância de uma classe (From é indivíduo de To)
	 * Exemplo: Fumantes e Não Fumantes são complementares
	 * 
	 * @author Guilherme N. Pinotte
	 * 
	 */
	private void criaInstaciaDeClasse (Proposition p) {
		String to = p.getTo().getLabel();
		String from = p.getFrom().getLabel();
		
		OntClass classFrom = this.model.createClass(this.getNS() + to);
		classFrom.createIndividual(this.getNS() + from);
	}

	/**
	 * Método que diz que duas instâncias diferentes é a mesma (From é uma instância igual a To)
	 * Exemplo: Dede e Davidson Cury são o mesmo indivíduo
	 * 
	 * @author Guilherme N. Pinotte
	 * 
	 */
	private void criaIndividuosIguais (Proposition p) {
		String to = p.getTo().getLabel();
		String from = p.getFrom().getLabel();
		Individual iFrom = null;
		Individual iTo = null;
		
		//ANALISAR MELHOR ESSE MÉTODO, POIS PARA CRIAR UMA INSTANCIA PRIMEIRO É NECESSÁRIO CRIAR UMA CLASSE
		//TALVEZ DE PARA CRIAR UMA INSTANCIA DESSA FORMA: this.model.createIndividual(from, iFrom);
		
		// TESTAR ISSO ###########################
		
		
		for (OntClass o : this.model.listClasses().toList()) {
			for (OntResource  ind : o.listInstances().toList()) {
//				ind = (Individual) ind;
				if (ind.getLocalName().equalsIgnoreCase(from)) {
					iFrom = (Individual) ind;
				}
			}
			for (OntResource  ind : o.listInstances().toList()) {
//				ind = (Individual) ind;
				if (ind.getLocalName().equalsIgnoreCase(to)) {
					iTo = (Individual) ind;
				}
			}
		}
		if (iFrom != null && iTo != null)
			iFrom.addSameAs(iTo);
		
	}
	
	/**
	 * Método que cria duas classes e uma object property
	 * 
	 * @author Guilherme N. Pinotte
	 * 
	 */
	private void criaRelacaoSemEsteriotipo (Proposition p) {
		String to = p.getTo().getLabel();
		String from = p.getFrom().getLabel();
		OntClass classTo = this.model.createClass(this.getNS() + to);
		OntClass classFrom = this.model.createClass(this.getNS() + from);
		ObjectProperty relation = this.model.createObjectProperty(p.getRel().getLabel());
		
		relation.addDomain(classFrom);
		relation.addRange(classTo);
		relation.addLabel(p.getRel().getLabel(), "en" );
	}
	
}

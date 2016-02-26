package pkg;

import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONStringer;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.json.JSONArray;
import org.json.JSONException;
import models.*;
import java.util.Iterator;
import java.util.LinkedList;

public class Criador {
	private JSONObject mapJson;
	private JSONArray nodeDataArray;
	private JSONArray linkDataArray;
	
	//lista de proposições
	private LinkedList<Proposition> propositions = new LinkedList<Proposition>();

	//lista de Classes
	private LinkedList<ClassOWL> classes = new LinkedList<ClassOWL>();
	
	//lista de Individuals
//	private LinkedList<IndividualOWL> individuals = new LinkedList();
	
	//lista de DataType Attributes
//	private LinkedList<AttributeOWL> attributes = new LinkedList();
	
	//lista de Object Properties
	private LinkedList<RelationOWL> relations = new LinkedList<RelationOWL>();
	
	
	//Constructor
	public Criador(String mapJson) {
		this.setMapJson(new JSONObject(mapJson));
		this.setNodeDataArray(this.mapJson.getJSONArray("nodeDataArray"));
		this.setLinkDataArray(this.mapJson.getJSONArray("linkDataArray"));
	}

	public JSONObject getMapJson() {
		return mapJson;
	}

	public void setMapJson(JSONObject mapJson) {
		this.mapJson = mapJson;
	}

	public JSONArray getNodeDataArray() {
		return this.nodeDataArray;
	}

	public void setNodeDataArray(JSONArray nodeDataArray) {
		this.nodeDataArray = nodeDataArray;
	}

	public JSONArray getLinkDataArray() {
		return this.linkDataArray;
	}

	public void setLinkDataArray(JSONArray linkDataArray) {
		this.linkDataArray = linkDataArray;
	}
	
	public LinkedList<Proposition> getPropositions() {
		return this.propositions;
	}

	public void setPropositions(LinkedList<Proposition> propositions) {
		this.propositions = this.propositions;
	}
	
	public void imprimeArray (JSONArray array) {
		for(int i = 0; i < array.length(); i++) { 
			System.out.println("(" + i + ") " + array.get(i)); 
		}
	}
	
	
	/**
	 * Método que lê o mapa em formato JSON e monta as listas de relações e conceitos
	 * 
	 * @author Guilherme N. Pinotte
	 * 
	 */
	public void leMapa() {
		this.criaListaRelacoes();
		this.criaListaConceitos();
		
		//monta a lista de proposições
		this.criaListaProposicoes();
	}
	
	/**
	 * Método que povoa a linkedList de relacoes
	 * 
	 * @author Guilherme N. Pinotte
	 * 
	 */
	public void criaListaRelacoes() {
		for(int i = 0; i < this.getLinkDataArray().length(); i++) {
			JSONObject obj = (JSONObject) this.getLinkDataArray().get(i);
			
			RelationOWL relation = new RelationOWL();
			relation.setLabel(obj.getString("text"));
			relation.setFrom(obj.getInt("from"));
			relation.setTo(obj.getInt("to"));

			this.relations.add(relation);
		}
	}

	/**
	 * Método que povoa a linkedList de classes, sem fazer a separação entre classe, indivíduos e atributos
	 * 
	 * @author Guilherme N. Pinotte
	 * 
	 */
	public void criaListaConceitos() {
		for(int i = 0; i < this.getNodeDataArray().length(); i++) {
			JSONObject obj = (JSONObject) this.getNodeDataArray().get(i);
			
			ClassOWL classe = new ClassOWL();
			classe.setKey(obj.getInt("key"));
			classe.setLabel(obj.getString("text"));
			
			this.classes.add(classe);
		}
	}
	
	/**
	 * Método que cria uma lista de proposições
	 * 
	 * @author Guilherme N. Pinotte
	 * 
	 */
	public void criaListaProposicoes() {
		for (RelationOWL relation : this.relations) {
			Proposition proposition = new Proposition();
			proposition.setRel(relation);
			for (ClassOWL classe : this.classes) {
				if(relation.getFrom() == classe.getKey()){
					proposition.setFrom(classe);
				}
			}
			for (ClassOWL classe : classes) {
				if(relation.getTo() == classe.getKey()){
					proposition.setTo(classe);
				}
			}
			this.propositions.add(proposition);
		}
	}
	
	@Override
	public String toString() {
		String imprime = "";
		
		for (Proposition prop : this.propositions) {
			imprime = imprime + "[" + prop.getFrom().getLabel() + " --- " + prop.getRel().getLabel() + " ---> " + prop.getTo().getLabel() + "]\n";
		}
		return imprime;
	}
	
}

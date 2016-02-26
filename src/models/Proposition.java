package models;

public class Proposition {
	private RelationOWL rel;
	private ClassOWL to;
	private ClassOWL from;
	
	//Constructor
	public Proposition(RelationOWL rel, ClassOWL to, ClassOWL from) {
		this.rel = rel;
		this.to = to;
		this.from = from;
	}
	
	//Default
	public Proposition() {

	}
	
	
	public RelationOWL getRel() {
		return rel;
	}

	public void setRel(RelationOWL rel) {
		this.rel = rel;
	}

	public ClassOWL getTo() {
		return to;
	}

	public void setTo(ClassOWL to) {
		this.to = to;
	}

	public ClassOWL getFrom() {
		return from;
	}

	public void setFrom(ClassOWL from) {
		this.from = from;
	}
	
}

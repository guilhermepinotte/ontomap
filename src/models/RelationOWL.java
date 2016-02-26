package models;

public class RelationOWL {
	private int from; 
	private int to;
	private String label;

	// Constructor
	public RelationOWL(int from, int to, String label) {
		super();
		this.from = from;
		this.to = to;
		this.label = label;
	}
	// Default
	public RelationOWL() {
		super();
	}

	public int getFrom() {
		return this.from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return this.to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public String getLabel() {
		return this.label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}

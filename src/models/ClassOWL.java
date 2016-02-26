package models;

public class ClassOWL {
	private String label;
	private Integer key;
	
	// Constructor
	public ClassOWL(String label, Integer key) {
		this.setLabel(label);
		this.setKey(key);
	}
	//Default
	public ClassOWL() {
		
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}
}

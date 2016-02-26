package models;

public class IndividualOWL extends ClassOWL {
	private String name;

	// Constructor
	public IndividualOWL(String label, Integer key, String name) {
		super(label, key);
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

package models;

//DataType Property
public class AttributeOWL extends ClassOWL {
	private String type;
	
	//Constructor
	public AttributeOWL(String label, Integer key) {
		super(label, key);
		this.setType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

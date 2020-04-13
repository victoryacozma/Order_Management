package model;

public class Order {
	private int id;
	private String clientName;
	private String productName;
	private int productQuant;

	public Order(int id2, String clientName, String productName2, int productQuant2) {
		this.id = id2;
		this.clientName = clientName;
		this.productName = productName2;
		this.productQuant = productQuant2;
	}
	
	public Order(String clientName, String productName2, int productQuant2) {
		this.clientName = clientName;
		this.productName = productName2;
		this.productQuant = productQuant2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getproductQuant() {
		return productQuant;
	}

	public void setProducQuant(int q) {
		this.productQuant = q;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String name) {
		this.clientName = name;
	}
	
	public String getProducttName() {
		return productName;
	}

	public void setProductName(String name) {
		this.productName = name;
	}
	
}


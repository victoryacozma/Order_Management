package model;


public class Product {
	private int id;
	private String name;
	private int quantity;
	private float price;
	
	public Product(int id, String name, int quantity, float price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public Product(String name, int quant, float price) {
		super();
		this.name = name;
		this.quantity = quant;
		this.price = price;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int q) {
		this.quantity = q;
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + "]";
	}

}


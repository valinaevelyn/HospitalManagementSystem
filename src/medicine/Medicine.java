package medicine;

public class Medicine {
	private String id;
	private String name;
	private String function;
	private double price;
	private int stock;
	private String type;
	
	public Medicine() {
		
	}

	public Medicine(String id, String name, String function, double price, int stock, String type) {
		super();
		this.id = id;
		this.name = name;
		this.function = function;
		this.price = price;
		this.stock = stock;
		this.type = type;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}

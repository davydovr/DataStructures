package purchaseOrder;

public class ProcessedOrder {
	private String upc;
	private Integer quantity;
	private Double price;

	public ProcessedOrder(String upc, Integer quantity, Double price) {
		this.upc = upc;
		this.quantity = quantity;
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getUpc() {
		return upc;
	}
	
	public int compareTo(ProcessedOrder p) {
		return upc.compareTo(p.upc);
	}
	
	public boolean equals(Object o) {
		if (o ==null) {
			return false;
		}
		else {
			if (o instanceof ProcessedOrder) {
				ProcessedOrder temp = (ProcessedOrder)o;
				return upc.equals(temp.upc);
			}
			else {
				return false;
			}
		}
	}
	
	public String toString() {
		StringBuilder info = new StringBuilder ("\nProcessedOrder ");
		info.append(upc);
		info.append(" Quantity " + quantity);
		info.append(" Price " + price);
		return info.toString();
	}

}

package purchaseOrder;

public class PurchaseOrder implements Comparable<PurchaseOrder>{
	private String upc;
	private Integer quantity;
	

	public PurchaseOrder(String upc, Integer quantity) {
		this.upc = upc;
		this.quantity = quantity;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public String getUpc() {
		return upc;
	}

	public int compareTo(PurchaseOrder p) {
		return upc.compareTo(p.upc);
	}
	
	public boolean equals(Object o) {
		if (o==null) {
			return false;
		}
		else {
			if (o instanceof PurchaseOrder) {
			PurchaseOrder temp = (PurchaseOrder)o;
			return temp.upc.equals(upc);
			}
			else
			{
				return false;
			}
		}
	}
	
	public String toString() {
		StringBuilder info = new StringBuilder("\nItem ");
		info.append(upc);
		info.append(" Quantity " + quantity);
		return info.toString();
		
	}
}

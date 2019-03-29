package purchaseOrder;



import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PurchaseOrders {
	private ArrayList <PurchaseOrder> orders;
	private String orderPurchasedDate;

	public PurchaseOrders() {
		orders = new ArrayList<PurchaseOrder>();
		orderPurchasedDate = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
		//new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	}
	
	public void addOrder(String upc, Integer quantity) {
		PurchaseOrder order = new PurchaseOrder(upc, quantity);
		if (orders.contains(order)) {
			throw new DuplicateDataException();
		}
		else {
			orders.add(order);
		}
	}
	
	public String getOrderPurchasedDate() {
		return orderPurchasedDate;
	}
	
	public void cancelOrder(String upc) {
		Iterator<PurchaseOrder> iter = orders.iterator();
		while (iter.hasNext()) {
			PurchaseOrder p = iter.next();
			if (p.getUpc().equals(upc)) {
				iter.remove();
				return;
			}
		}
	}
	
	public void writeOutOrders (String filename) {
		try (Writer writer = new FileWriter(filename)) {
		    Gson gson = new GsonBuilder().create();
		    //stores the contents of the ArrayList in JSON format
		    gson.toJson(orders, writer);
		    writer.close();
		}
		catch(IOException ex1) {
			System.out.println("io exception");
		}
	}
	
	public String toString() {
		StringBuilder info = new StringBuilder("\n PurchaseOrders");
		for (PurchaseOrder p : orders) {
			info.append(p);
		}
		return info.toString();
	}
	
	public Iterator <PurchaseOrder > iterator(){
		return  orders.iterator();
	}
	
	public ArrayList <PurchaseOrder> getPurchasedOrders() 
	{
		ArrayList <PurchaseOrder> copy = new ArrayList <PurchaseOrder> ();
		
		Iterator <PurchaseOrder> iter = orders.iterator(); 
		while (iter.hasNext()) 
		{
			copy.add(iter.next());
		}
		
		return copy;
		
	}
	
	//not necessary because the ArrayList supports an iterator
	//class POIterator implements Iterator<PurchaseOrder> {
	//	private int currentElement ;
		
	//	POIterator(){
		//	currentElement =0;
	//	}
		
	//	public boolean hasNext() {
	//		return (currentElement < orders.size());
	//	}
		
	//	public PurchaseOrder next() {
		//	return orders.get(currentElement++);
		//}
	//}

	public static void main(String[] args) {
		PurchaseOrders orders = new PurchaseOrders();
		orders.addOrder("710069804005",100);
		orders.addOrder("03345400202", 50);
		orders.addOrder("076784003070", 150);
		try {
			Writer writer = new FileWriter("PurchaseOrders.json");
		    Gson gson = new GsonBuilder().create();
		    //stores the entire PurchaseOrders object which includes
		    //the ArrayList of PurchaseOrder objects
		    gson.toJson(orders, writer);
		    writer.close();
		}
		catch(IOException ex1) {
			System.out.println("io exception");
		}
		
		orders.writeOutOrders("orders.json");

	}

}

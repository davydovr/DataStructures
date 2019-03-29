package purchaseOrder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ProcessedOrders 
{


	private HashMap <String,ProcessedOrder> orders;
	private String orderProcessedDate;

	public ProcessedOrders() 
	{
		orders = new HashMap<String, ProcessedOrder>();
		orderProcessedDate = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
	}

	public String getOrderProcessedDate() 
	{
		return orderProcessedDate;
	}

	public void addProcessedOrder(ProcessedOrder p) 
	{
		orders.put(p.getUpc(), p);
	}

	public HashMap <String, ProcessedOrder> getProcessedOrders() 
	{
		HashMap <String, ProcessedOrder> copy = new HashMap <String, ProcessedOrder> ();
		for (Map.Entry<String, ProcessedOrder> entry : orders.entrySet()) 
		{
			copy.put(entry.getKey(), entry.getValue());
		}

		return copy;
	}

	public void writeOutProcessedOrders(String filename) 
	{
		/*
		   Enhance the ProcessedOrders class adding the following method
		   writeOutProcessedOrders(String filename)  
		   It should write out the contents of the HashMap as a list of ProcessedOrders, not as a HashMap
		 */
		
		Gson gsonOut = new Gson();
		try 
		{
			FileWriter writer = new FileWriter(filename);
			List <ProcessedOrder> theList = new ArrayList <ProcessedOrder> (orders.values());
			gsonOut.toJson(theList, writer);
			writer.close();
			
			
		} catch (IOException e) 
		{
			System.out.println("Action could not be completed");
		}
		
	}


	public static void main(String[] args) 
	{
		ProcessedOrders processedOrdersContainer = new ProcessedOrders();
		Scanner keyboard = new Scanner(System.in);
		Double price;
		try {
			Gson gson = new Gson();
			Reader reader = new FileReader("PurchaseOrders.json");

			// parse a PurchaseOrders object from the file, reconstructing the object in memory 
			PurchaseOrders orders = gson.fromJson(reader, PurchaseOrders.class);

			//process each order
			Iterator <PurchaseOrder> iter = orders.iterator();
			while (iter.hasNext()) {
				PurchaseOrder order = iter.next();
				System.out.println("enter price of " + order.getUpc() + ": ");
				price = keyboard.nextDouble();
				ProcessedOrder processedOrder = new ProcessedOrder(order.getUpc(),order.getQuantity(),price);
				processedOrdersContainer.addProcessedOrder(processedOrder);
			}
			//finished processing all the orders, now write it out to JSON file to communicate with the customer
			try (Writer writer = new FileWriter("processedOrders.json")) {
				Gson gsonBuilder = new GsonBuilder().create();
				gsonBuilder.toJson(processedOrdersContainer, writer);
				writer.close();
			}
			catch(IOException ex1) {
				System.out.println("io exception");
			}
		}
		catch (FileNotFoundException ex) {
			System.out.println("file not found");
		}

		keyboard.close();
	}


}



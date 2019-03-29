package purchaseOrder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CheckOrder 
{
	public static void main(String[] args) 
	{
		//compare and see that there are matches
		//display those that were not processed 

		PurchaseOrders purchased = null;
		ProcessedOrders processed = null;

		try 
		{
			Gson gson = new Gson();
			Reader reader = new FileReader("PurchaseOrders.json");
			Type type = new TypeToken <PurchaseOrders> () {}.getType();

			purchased = gson.fromJson(reader, type);

			reader = new FileReader("processedOrders.json");
			type = new TypeToken <ProcessedOrders> () {}.getType();

			processed = gson.fromJson(reader, type);

		}
		catch (FileNotFoundException e) 
		{
			System.out.println("No files found.");
		}

		//make sure the dates of the 2 files match up
		if (purchased.getOrderPurchasedDate().equals(processed.getOrderProcessedDate())) 
		{
			ArrayList <PurchaseOrder> purchasedOrders = purchased.getPurchasedOrders();
			HashMap <String, ProcessedOrder> processedOrders = processed.getProcessedOrders();

			StringBuilder unprocessedOrders = new StringBuilder();
			unprocessedOrders.append("Unprocessed Orders: ");

			//display purchased orders that were not processed
			for (int x = 0; x < purchasedOrders.size(); x++)
			{
				if (!processedOrders.containsKey(purchasedOrders.get(x).getUpc()))
				{
					unprocessedOrders.append("\n" + purchasedOrders.get(x).toString());
				}
			}
			
			System.out.println(unprocessedOrders.toString());
			
		}
		else 
		{
			System.out.println("Order dates of the Processed and Purchased Orders files do not match up.");
		}
		
		System.out.println("\n\nWrite Out Processed Orders: ");
		
		processed.writeOutProcessedOrders("testWriteOut.json");
	}
}

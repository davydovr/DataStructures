package progAssignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class Bank {

	public static void main(String[] args) {

		HashMap <String, Integer> hMap = new HashMap <String, Integer>();
		LinkedHashMap <String, Integer> lhMap = new LinkedHashMap <String, Integer> ();
		
		//Check that file exists
		File file = new File("banklist.txt");
		if(!file.exists()) {
			System.out.println("The file banklist.txt does not exist.");
			System.exit(0);
		}

		//Open file for reading
		Scanner readFile = null;
		try {
			readFile = new Scanner(file);
		} 
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}


		while(readFile.hasNextLine()) {
			
			/*
			 * I was going to use StringTokenizer, but when I saw the following written about the class by Oracle I used a String and split it.
			 * https://docs.oracle.com/javase/8/docs/api/java/util/StringTokenizer.html
			 * StringTokenizer is a legacy class that is retained for compatibility reasons although its use is discouraged in new code. 
			 * It is recommended that anyone seeking this functionality use the split method of String or the java.util.regex package instead.
			 * 
			 */
			
			String line = readFile.nextLine();
			//regex that ignores the commas inside ""
			String[] lineArray = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
			
			
			String city = lineArray[1];
			String state = lineArray[2];
			String cityState = city + state;
			
			//add to the hashmap
			//if this entry does not yet exist in the hashmap
			if (hMap.get(cityState) == null) {
				hMap.put(cityState, 1);
				lhMap.put(cityState, 1);
			}
			else {
				Integer numTimes = hMap.get(cityState);
				hMap.put(cityState, ++numTimes);
				lhMap.put(cityState, numTimes);		//not incrementing numTimes here bc it was just incremented
				
			}
		}

		readFile.close();
		
		displayInfoForEach(hMap);
		displayMax(lhMap);


	}
	
	public static void displayInfoForEach(HashMap <String, Integer> hMap) {
		//d.	Output results:   city,state   number of bank failures
		String comma = ", ";
		int index = 0;
		
		for (Map.Entry<String, Integer> entry : hMap.entrySet()) {
			String location = entry.getKey();
			if (location.length() >= 2)         // if word is at least two characters long
			{
				index = location.length() - 2;
				String newString = location.substring(0, index) + comma + location.substring(index);		
				System.out.println("Number of failures in " + newString + ": \t" + entry.getValue());
			}
		}

	}
	
	public static void displayMax(LinkedHashMap <String, Integer> lhMap) {
		int max = 0;
		
		String theKey = null;
		for (String key : lhMap.keySet()) {
			
			if (lhMap.get(key) > max) {
				theKey = key;
				max = lhMap.get(key);
			}
		}
		System.out.println("The banks in " + theKey  + " had " + lhMap.get(theKey) + " bank failures");
	}


}

package dfsBfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;


public class Graph <T> 
{
	private HashMap <T, HashSet <T>> verticies;
	
	public Graph() 
	{	
		verticies = new HashMap <T, HashSet <T>> ();	
	}
	
	public boolean addNode(T node) 
	{
		if (verticies.containsKey(node)) 
		{
			return false;
		}
		else 
		{
			verticies.put(node, new HashSet<T>());
			return true;
		}
	}
	
	public boolean addEdge(T start, T stop) throws NullPointerException
	{
		if (!verticies.containsKey(start) || !verticies.containsKey(stop)) {
			throw new NullPointerException("Given vertex does not exist");
		}
		else 
		{
			//HashSet add operation returns false if the element you're adding already exists in the collection
			
			//add connection to the start vertex
			HashSet <T> set = verticies.get(start);
			set.add(stop);
			verticies.put(start, set);
			
			return true;
		}
	}
	
	
	// useful link 
	// https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
	public String BFS(T start) 
	{
		StringBuilder s = new StringBuilder();
		
		HashMap <T, T> visited = new HashMap <T, T> ();
		
		LinkedList <T> queue = new LinkedList <T> ();
		
		visited.put(start, start);
		queue.add(start);
		
		while (!queue.isEmpty())
		{
			T curr = queue.poll();
			s.append(curr + " ");
			
			//iterate thru its children
			Iterator <T> iter = verticies.get(curr).iterator();
			while (iter.hasNext()) 
			{
				T next = iter.next();
				if (!visited.containsKey(next)) 
				{
					visited.put(next, next);
					queue.add(next);
				}
			}
		}
		
		return s.toString();
	}
	
	
	public void DFS(T start)
	{
		/*
		 * Pseudocode for DFS!
		 * add start to stack
		 * put in parent (start, start)
		 * while stack ! empty
		 * 	curr = stack.pop
		 * 	if curr ! visited
		 * 	mark curr visited
		 * 	get curr's children
		 * 	if child ! visited
		 * 		add child to stack
		 * 		add parent child (child, curr)
		 * yay!! 
		 */
		
		
		LinkedList <T> theStack = new LinkedList <T> ();
		HashMap <T, T> parent = new HashMap <T, T> ();
		HashMap <T, T> visited = new HashMap <T, T> ();
		
		theStack.add(start);
		//the start element put same, same in parent to signify the end of the iteration for later. 
		parent.put(start, start);

		while (!theStack.isEmpty())
		{
			//pop and visit
			T curr = theStack.getLast();
			theStack.removeLast();
			//to keep track of visits
			
			//if not visited, do all this and set curr to visited
			if (!visited.containsKey(curr))
			{
				visited.put(curr, curr);
				//iterate thru all of curr's connections, its hashset
				Iterator <T> iter = verticies.get(curr).iterator();
				//add each next to the stack
				while (iter.hasNext())
				{
					T next = iter.next();
					if (!visited.containsKey(next))
					{
						theStack.add(next);
						//put this: (child, parent)
						parent.put(next, curr);	
					}
					
				}
			}	
		}
			
		//stack is empty, we went through entire graph
		//for each vertex, find key in parent and loop up to start. 
		ArrayList <String> paths = new ArrayList <String> ();
		
		for (T vertex : verticies.keySet())
		{
			LinkedList <T> pathStack = new LinkedList <T> ();
			String path = "[ " + start;
			
			while (vertex != start)
			{
				pathStack.addFirst(vertex);
				vertex = parent.get(vertex);
			}
			
			while (!pathStack.isEmpty())
			{
				path += " " + pathStack.poll();
			}
			path += " ]";
			paths.add(path);
		}
		
		for (int x = 0; x < paths.size(); x++) 
		{
			System.out.println(paths.get(x));
		}
	
	}
	
		
	public String getImmediateConnections(T curr) 
	{
		StringBuilder s = new StringBuilder();
		s.append(curr + " -> ");
		if (verticies.containsKey(curr)) 
		{
			Iterator <T> iter = verticies.get(curr).iterator();
			while (iter.hasNext()) 
			{
				s.append(iter.next());
				s.append(" ");
			}
		}
		return s.toString();
	}
	

	public String toString() 
	{

		StringBuilder s = new StringBuilder();
		
		for (T entry : verticies.keySet())
		{
			s.append(getImmediateConnections(entry));
			s.append("\n");
		}
		
		return s.toString();
	}
	
	public static void main(String[] args) 
	{
		Graph <String> aGraph = new Graph <String> ();
		
		//add nodes
		aGraph.addNode("0");
		aGraph.addNode("1");
		aGraph.addNode("2");
		aGraph.addNode("3");
		aGraph.addNode("4");
		aGraph.addNode("5");
		aGraph.addNode("6");
		aGraph.addNode("7");
		
		//add edges
		aGraph.addEdge("0", "1");
		aGraph.addEdge("1", "0");
		aGraph.addEdge("0", "2");
		aGraph.addEdge("0", "4");
		aGraph.addEdge("0", "3");
		aGraph.addEdge("2", "1");
		aGraph.addEdge("1", "6");
		aGraph.addEdge("2", "6");
		aGraph.addEdge("5", "1");
		aGraph.addEdge("3", "5");
		aGraph.addEdge("7", "5");
		aGraph.addEdge("4", "7");
		aGraph.addEdge("2", "4");
		aGraph.addEdge("4", "2");
		aGraph.addEdge("5", "6");
		aGraph.addEdge("6", "4");
		aGraph.addEdge("6", "5");
		
		System.out.println("\nGet Immediate Connections");
		System.out.println(aGraph.getImmediateConnections("0"));
		System.out.println(aGraph.getImmediateConnections("1"));
		System.out.println(aGraph.getImmediateConnections("2"));
		System.out.println(aGraph.getImmediateConnections("3"));
		System.out.println(aGraph.getImmediateConnections("4"));
		System.out.println(aGraph.getImmediateConnections("5"));
		System.out.println(aGraph.getImmediateConnections("6"));
		System.out.println(aGraph.getImmediateConnections("7"));
			
		System.out.println("\nTo String:");
		System.out.println(aGraph.toString());
		
		System.out.println("Breadth First Search");
		System.out.println(aGraph.BFS("2"));
		
		System.out.println("\nDepth First Search:");
		aGraph.DFS("0");
	}
	
}
 
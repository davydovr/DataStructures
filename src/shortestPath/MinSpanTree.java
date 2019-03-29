package shortestPath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MinSpanTree 
{
	
	//Minimum Span Tree: The cheapest way to access all vertices in the graph
	
	private ArrayList <Edge> theTree;
	
	public MinSpanTree(Graph theGraph)
	{
		theTree = new ArrayList <Edge> ();
		LinkedList <Edge> theEdges = (LinkedList<Edge>) theGraph.getEdges(); 
		int numVerticies = theGraph.getVertexes().size();
		Collections.sort(theEdges);
		
		Edge curr = theEdges.getFirst();
		
		while (curr != null && theTree.size() < (numVerticies-1))
		{
			//remove curr from theEdges and add it to theTree
			theEdges.remove(curr);
			theTree.add(curr);
			
			System.out.println(curr + " ");
			
			LinkedList <Edge> adjacentEdges = new LinkedList <Edge> ();
			//get all edges adjacent to curr edge
			Iterator <Edge> edgeIter = theEdges.iterator();
			while (edgeIter.hasNext())
			{
				Edge currEdge = edgeIter.next();
				if (currEdge.getSource() == curr.getDestination() ||
						currEdge.getDestination() == curr.getDestination() ||
						currEdge.getSource() == curr.getSource() ||
						currEdge.getDestination() == curr.getSource())
				{
					adjacentEdges.add(currEdge);
				}
			}
			//have all adjacent edges
			Collections.sort(adjacentEdges);
			curr = adjacentEdges.poll();		//the adjacent edge with minimum weight
			
		}
		
		//now we go back up the loop. If curr is null, that means we reached the end of the tree. 
		//If its not null, it will be added to the tree
		
	}

	
	public String minSpanTreePath() {
		StringBuilder path = new StringBuilder();
		path.append("[");
		for (int x = 0; x < theTree.size(); x++)
		{
			path.append(" " + theTree.get(x).getId());
		}
		
		path.append(" ]");
		return path.toString();
	}
	
	
	public static void main(String[] args)
	{
		
		Vertex v0 = new Vertex("0", "0");
		Vertex v1 = new Vertex("1", "1");
		Vertex v2 = new Vertex("2", "2");
		Vertex v3 = new Vertex("3", "3");
		Vertex v4 = new Vertex("4", "4");
		Vertex v5 = new Vertex("5", "5");
		Vertex v6 = new Vertex("6", "6");
		Vertex v7 = new Vertex("7", "7");
		
		List <Vertex> theVerticies = new ArrayList <Vertex> ();
		theVerticies.add(v0);
		theVerticies.add(v1);
		theVerticies.add(v2);
		theVerticies.add(v3);
		theVerticies.add(v4);
		theVerticies.add(v5);
		theVerticies.add(v6);
		theVerticies.add(v7);
		
		Edge e01 = new Edge("e01", v0 , v1 , 1);
		Edge e02 = new Edge("e02", v0 , v2 , 2);
		Edge e03 = new Edge("e03", v0 , v3 , 5);
		Edge e04 = new Edge("e04", v0 , v4 , 6);
		Edge e21 = new Edge("e21", v2 , v1 , 4);
		Edge e26 = new Edge("e26", v2 , v6 , 5);
		Edge e24 = new Edge("e24", v2 , v4 , 3);
		Edge e16 = new Edge("e16", v1 , v6 , 11);
		Edge e51 = new Edge("e51", v5 , v1 , 7);
		Edge e53 = new Edge("e53", v5 , v3 , 4);
		Edge e56 = new Edge("e56", v5 , v6 , 3);
		Edge e64 = new Edge("e64", v6 , v4 , 3);
		Edge e47 = new Edge("e47", v4 , v7 , 8);
		Edge e75 = new Edge("e75", v7 , v5 , 4);
		
		List <Edge> theEdges = new ArrayList <Edge> ();
		theEdges.add(e01);
		theEdges.add(e02);
		theEdges.add(e03);
		theEdges.add(e04);
		theEdges.add(e21);
		theEdges.add(e24);
		theEdges.add(e26);
		theEdges.add(e16);
		theEdges.add(e51);
		theEdges.add(e53);
		theEdges.add(e56);
		theEdges.add(e64);
		theEdges.add(e47);
		theEdges.add(e75);
		
		
		Graph theGraph = new Graph(theVerticies, theEdges);
		MinSpanTree minSpanTree = new MinSpanTree(theGraph);
		
		System.out.println(minSpanTree.minSpanTreePath());

		
	}

	
}

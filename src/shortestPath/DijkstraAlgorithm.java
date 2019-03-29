package shortestPath;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class DijkstraAlgorithm {

	private final List<Vertex> nodes;
	private final List<Edge> edges;
	private Set<Vertex> settledNodes;				//nodes that have a place to go
	private Set<Vertex> unSettledNodes;				//nodes with no where to go YET. this is a queue. 
	private Map<Vertex, Vertex> predecessors;
	private Map<Vertex, Integer> distance;

	public DijkstraAlgorithm(Graph graph) {
		// create a copy of the array so that we can operate on this array
		this.nodes = new ArrayList<Vertex>(graph.getVertexes());
		this.edges = new ArrayList<Edge>(graph.getEdges());
	}

	public void execute(Vertex source) {
		//set of vertices that have been examined, no duplicates
		settledNodes = new HashSet<Vertex>();
		//set of Vertices that are being examined,  no duplicates
		unSettledNodes = new HashSet<Vertex>();
		//for each vertex, the current shortest distance that can be achieved
		//to that vertex from the source
		distance = new HashMap<Vertex, Integer>();
		//for each vertex 'a' , which vertex 'b' is passed by immediately before
		//reaching vertex 'a'
		predecessors = new HashMap<Vertex, Vertex>();
		//since we start at vertex, source, the distance to travel to that

		//vertex is zero
		distance.put(source, 0);
		//add the start vertex to set of unSettledNodes
		unSettledNodes.add(source);
		//while there are still nodes in the unSettledSet

		//size is 1 
		while (unSettledNodes.size() > 0) {
			//get the vertex that can be reached with the minimum amount of travel
			Vertex node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(Vertex node) {
		List<Vertex> adjacentNodes = getNeighbors(node);
		for (Vertex target : adjacentNodes) {
			if (getShortestDistance(target) > getShortestDistance(node)
					+ getDistance(node, target)) {
				distance.put(target, getShortestDistance(node)
						+ getDistance(node, target));
				predecessors.put(target, node);
				unSettledNodes.add(target);
			}
		}

	}

	private int getDistance(Vertex node, Vertex target) {

		for (Edge edge : edges) {
			if (edge.getSource().equals(node)
					&& edge.getDestination().equals(target)) {
				return edge.getWeight();
			}
		}
		throw new RuntimeException("Should not happen");
	}

	private List<Vertex> getNeighbors(Vertex node) {
		List<Vertex> neighbors = new ArrayList<Vertex>();
		for (Edge edge : edges) {
			if (edge.getSource().equals(node)
					&& !isSettled(edge.getDestination())) {
				neighbors.add(edge.getDestination());
			}
		}
		return neighbors;
	}


	private Vertex getMinimum(Set<Vertex> vertexes) {
		Vertex minimum = null;  //initialize the minimum distance to null
		//could be infinity 
		for (Vertex vertex : vertexes) {
			//for each vertex in the set of vertices
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
					minimum = vertex;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Vertex vertex) {
		return settledNodes.contains(vertex);
	}

	private int getShortestDistance(Vertex destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	/*
	 * This method returns the path from the source to the selected target and
	 * NULL if no path exists
	 */
	public LinkedList<Vertex> getPath(Vertex target) {
		LinkedList<Vertex> path = new LinkedList<Vertex>();
		Vertex step = target;
		// check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		return path;
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
		Edge e10 = new Edge("e10", v1 , v0 , 1);
		Edge e02 = new Edge("e02", v0 , v2 , 2);
		Edge e03 = new Edge("e03", v0 , v3 , 5);
		Edge e04 = new Edge("e04", v0 , v4 , 6);
		Edge e21 = new Edge("e21", v2 , v1 , 4);
		Edge e26 = new Edge("e26", v2 , v6 , 5);
		Edge e16 = new Edge("e16", v1 , v6 , 11);
		Edge e51 = new Edge("e51", v5 , v1 , 7);
		Edge e53 = new Edge("e53", v5 , v3 , 4);
		Edge e56 = new Edge("e56", v5 , v6 , 3);
		Edge e65 = new Edge("e65", v6 , v5 , 3);
		Edge e64 = new Edge("e64", v6 , v4 , 3);
		Edge e47 = new Edge("e47", v4 , v7 , 8);
		Edge e75 = new Edge("e75", v7 , v5 , 4);
		
		List <Edge> theEdges = new ArrayList <Edge> ();
		theEdges.add(e01);
		theEdges.add(e10);
		theEdges.add(e02);
		theEdges.add(e03);
		theEdges.add(e04);
		theEdges.add(e21);
		theEdges.add(e26);
		theEdges.add(e16);
		theEdges.add(e51);
		theEdges.add(e53);
		theEdges.add(e56);
		theEdges.add(e65);
		theEdges.add(e47);
		theEdges.add(e75);
		
		
		Graph theGraph = new Graph(theVerticies, theEdges);
		
		

		
	}
}




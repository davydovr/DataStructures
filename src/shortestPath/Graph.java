package shortestPath;



	import java.util.List;
import java.util.ListIterator;
import java.util.LinkedList;

	public class Graph {
	    private  List<Vertex> vertexes;
	    private  List<Edge> edges;

	    public Graph(List<Vertex> vertexes, List<Edge> edges) {
	        this.vertexes = vertexes;
	        this.edges = edges;
	    }
	    
	    public Graph() {
	    	this.vertexes = new LinkedList<Vertex>();
	    	this.edges = new LinkedList<Edge>();
	    }
	    
	    public void addVertex(Vertex v) {
	    	//check for duplicates , can't have a duplicate Vertex
	    	if (!this.vertexes.contains(v)) {
	    		this.vertexes.add(v);
	    	}
	    	//otherwise just ignore, the Graph already has this vertex
	    }

	    public void addEdge(String edgeID, Vertex start, Vertex end, int weight) {
	    	if (this.vertexes.contains(start)) {  //has the start Vertex in the Graph
	    		if (this.vertexes.contains(end)) {  //has the end vertex in the Graph
	    			Edge theEdge = new Edge(edgeID, start, end , weight);
	    			//verify that the edge doesn't already exist
	    			if (!this.edges.contains(theEdge)) {
	    			this.edges.add(theEdge);
	    			}
	    		}
	    	}
	    	//just ignore the request if the vertexes aren't part of the graph
	    }
	    
	    
	    public List<Vertex> getVertexes() {
	    	//deep copy
	    	List<Vertex> copy = new LinkedList<Vertex>();
	    	//use list iterator
	    	ListIterator <Vertex > liter = this.vertexes.listIterator();
	    	while (liter.hasNext()) {
	    		copy.add(liter.next());
	    	}
	        return copy;    //a different list with the same Vertexes
	                        //Vertexes can't be modified, no setters provided
	    }
	    
	    

	    public List<Edge> getEdges() {
	    	//deep copy
	    	List<Edge> copy = new LinkedList<Edge>();
	    	//use list iterator
	    	ListIterator <Edge > liter = this.edges.listIterator();
	    	while (liter.hasNext()) {
	    		copy.add(liter.next());
	    	}
	            //a different list with the same Edges
	                        //Edges can't be modified, no setters provided
	    	return copy;  
	    }
	    
	    


        public String toString() {
        	StringBuilder info = new StringBuilder("Graph \n ");
        	for (Vertex v : vertexes) {
        		info.append (v.getName()  );
        		for (Edge e : edges) {
        			if (e.getSource().getName().equalsIgnoreCase(v.getName())) {
        				info.append("->" + e.getDestination() + " weight " + e.getWeight());
        			}
        		}
        		info.append("\n");  //next line
        	}
        	return info.toString();
        }
        
       
        
        
        
        public static void  main(String[] args) {
        	 Graph aGraph = new Graph();
        	 LinkedList<Vertex> myVertexes = new LinkedList<Vertex>();
        	 for (int i = 0 ;i<11;i++) {
        	        Vertex location = new Vertex("Node_" + i, "Node_" + i);
        	        myVertexes.add(location);  //keep track of each Vertex
    	            aGraph.addVertex(location); 
    	     }
        	 
    	     
    	     aGraph.addEdge("Edge_0", myVertexes.get(0),myVertexes.get(1), 85);
    	     aGraph.addEdge("Edge_1",myVertexes.get(0), myVertexes.get(2), 217);
    	     aGraph.addEdge("Edge_2", myVertexes.get(0),myVertexes.get(4) , 173);
    	     aGraph.addEdge("Edge_3",myVertexes.get(2) ,myVertexes.get(6), 186);
    	     aGraph.addEdge("Edge_4", myVertexes.get(2), myVertexes.get(7), 103);
    	     aGraph.addEdge("Edge_5", myVertexes.get(3), myVertexes.get(7), 183);
    	     aGraph.addEdge("Edge_6",myVertexes.get(5),myVertexes.get(8), 250);
    	     aGraph.addEdge("Edge_7",myVertexes.get(8), myVertexes.get(9), 84);
    	     aGraph.addEdge("Edge_8",myVertexes.get(7) , myVertexes.get(9), 167);
    	     aGraph.addEdge("Edge_9",myVertexes.get(4),myVertexes.get(9), 502);
    	     aGraph.addEdge("Edge_10",myVertexes.get(9),myVertexes.get(10), 40);
    	     aGraph.addEdge("Edge_11",myVertexes.get(1),myVertexes.get(10), 600);
    	     
    	     System.out.println(aGraph.toString());
    	     
    	     //verify that Graph is immutable and can't be tampered with
    	     //via its getters
    	     List <Vertex> hackerList = aGraph.getVertexes();
    	     ListIterator<Vertex> liter = hackerList.listIterator();
    	     while (liter.hasNext()) {
    	    	 liter.next();
    	    	 liter.remove();  //try to remove the vertices from the graph
    	     }
    	     
    	     //now display the graph
    	     System.out.println(aGraph.toString());
    	     
    	     // Lets check from location Loc_1 to Loc_10
 	        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(aGraph);
 	        dijkstra.execute(myVertexes.get(0));
 	        LinkedList<Vertex> path = dijkstra.getPath(myVertexes.get(10));

 	      

 	        for (Vertex vertex : path) {
 	            System.out.println(vertex);
 	        }

 	    }
    	     
    }
	



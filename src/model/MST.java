package model;
import java.util.*;

public class MST {
	

	public static SpanningTree getInitialSpanningTree(HashMap<Integer, ArrayList<Edge>> nodeToEdge, HashMap<Integer,Edge> allEdges) {
    	int nodeCursor = 1;
    	int weight = 0;
    	HashSet<Integer> visitedNodes = new HashSet<Integer>();
    	HashSet<Integer> rootSpanningTree = new HashSet<Integer>();
    	Stack<Edge> availableEdges = new Stack<Edge>();
    	visitedNodes.add(1);
    	while(visitedNodes.size() < nodeToEdge.size()) {
    		for (Edge edge : nodeToEdge.get(nodeCursor)) {
    			int neighbourNode = edge.getOtherNode(nodeCursor);
    			if(!visitedNodes.contains(neighbourNode))
    				availableEdges.add(edge.setFromNode(nodeCursor));
    		}
    		
    		Edge nextEdge = availableEdges.pop();
    		while(visitedNodes.contains(nextEdge.getToNode())) {
    			nextEdge = availableEdges.pop();
    		}
    		weight += nextEdge.getWeight();
    		rootSpanningTree.add(nextEdge.getId());
    		nodeCursor = nextEdge.getToNode();
    		visitedNodes.add(nodeCursor);
    	}
    	
    	SpanningTree spanningTree = new SpanningTree(rootSpanningTree, allEdges, nodeToEdge);
    	spanningTree.B = weight;
    	
    	return spanningTree;
    }
    
}

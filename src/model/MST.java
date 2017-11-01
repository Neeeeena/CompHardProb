package model;
import java.util.*;

public class MST {
	

	public static HashSet<Integer> getInitialSpanningTree(HashMap<Integer, ArrayList<Edge>> nodeToEdge) {
    	int nodeCursor = 1;
    	HashSet<Integer> visitedNodes = new HashSet<Integer>();
    	HashSet<Integer> spanningTree = new HashSet<Integer>();
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
    		spanningTree.add(nextEdge.getId());
    		nodeCursor = nextEdge.getToNode();
    		visitedNodes.add(nodeCursor);
    	}
    	return spanningTree;
    }
    
}

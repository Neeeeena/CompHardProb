package model;

import java.util.HashSet;

public class GraphNode {
	public Edge edge;
	public HashSet<Integer> path;
	
	public GraphNode(Edge edge, HashSet<Integer> path){
		edge = edge;
		this.path = path;
	}
}

package model;

import java.util.HashSet;

public class GraphNode {
	public Integer node;
	public HashSet<Integer> path;
	
	public GraphNode(Integer node, HashSet<Integer> path){
		this.node = node;
		this.path = path;
	}
}

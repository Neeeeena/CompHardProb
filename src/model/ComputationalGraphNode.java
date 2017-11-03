package model;

import java.util.HashSet;
import java.util.Set;

public class ComputationalGraphNode {
	public HashSet<Integer> st;
	public HashSet<Integer> IN;
	public HashSet<Integer> OUT;
	
	public int weight;
	public int mirrorWeight;
	
	public ComputationalGraphNode parent;
	
	public ComputationalGraphNode(HashSet<Integer> st, HashSet<Integer> IN, HashSet<Integer> OUT, ComputationalGraphNode parent){
		this.st = st;
		this.IN = IN;
		this.OUT = OUT;
		this.parent = parent;
	}
}

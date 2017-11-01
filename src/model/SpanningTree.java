package model;
import java.util.*;


public class SpanningTree {

	public int B = (int)Double.POSITIVE_INFINITY;
	HashSet<Integer> rootST;
	public HashSet<Integer> bestST;
	//id of edge to edge
	HashMap<Integer,Edge> allEdges;
	//fromNode to edge
	HashMap<Integer, ArrayList<Edge>> nodeToEdge = new HashMap<Integer,ArrayList<Edge>>();
	HashSet<Integer> allIds = new HashSet<Integer>();
	
	public SpanningTree(HashSet<Integer> spanningTree, HashMap<Integer,Edge> allEdges, HashMap<Integer, ArrayList<Edge>> nodeToEdge){
		rootST = spanningTree;
		this.allEdges = allEdges;
		this.nodeToEdge = nodeToEdge;
		//initialize a set of the ids of all the edges
		for(int i = 0;i<allEdges.size();i++){
			allIds.add(i);
		
			
		}
		
		
		
	}
	
	
	
	public void FindMFMST(){
		ComputationalGraphNode current = new ComputationalGraphNode(rootST,new HashSet<Integer>(),new HashSet<Integer>(),null);
		findMFMST(current);
	}
	
	private void findMFMST(ComputationalGraphNode current){
		
			HashSet<Integer> notIncluded = (HashSet)allIds.clone();
			//select an edge from the set of edges not in the current spanning tree and in out
			notIncluded.removeAll(current.st);
			notIncluded.removeAll(current.OUT);
			for(Integer f : notIncluded){
				HashSet<Integer> swaps = GetSwaps(current,f);
				if(swaps!=null){
				for(Integer e : swaps){
					
					//swap edges
					HashSet<Integer> childST = current.st;
					childST.remove(e);
					childST.add(f);
					
					HashSet<Integer> OUT = current.OUT;
					OUT.add(e);
					
					HashSet<Integer> IN = current.IN;
					IN.add(f);
					
					ComputationalGraphNode child = new ComputationalGraphNode(childST, IN,OUT,current);
					//update the weight of the spanning tree
					child.weight = updateWeights(child,f,e); 
					checkB(child);
					//depth first search
					findMFMST(child);
				}
				}else{
					System.out.println("Det er ikke godt");
				}
				
			}
			
		
		
	}
	
	private int updateWeights(ComputationalGraphNode current, Integer in, Integer out){
		Edge outEdge = allEdges.get(out);
		Edge inEdge = allEdges.get(in);
		
		int weight = current.weight-(outEdge.getWeight()+outEdge.getMirrorWeight()) + inEdge.getWeight()+inEdge.getMirrorWeight();
		
		return weight;
		
	}
	
	
	private void checkB(ComputationalGraphNode node){
		//keep track of current best B and the matching spanning tree
		if(node.weight<B){
			bestST = node.st;
			B = node.weight;
		}
	}
	
	
	//finds the edges that can be removed from the spanning tree
	private HashSet<Integer> GetSwaps(ComputationalGraphNode gn, Integer f){
		
		Edge addEdge = allEdges.get(f);
		
		//find cycles

		ArrayList<GraphNode> frontier = new ArrayList<GraphNode>();
		HashSet<Integer> path = new HashSet<Integer>();
		path.add(addEdge.getId());
		Integer goal = addEdge.getFromNode();
		GraphNode node = new GraphNode(addEdge.getToNode(), path);
		frontier.add(node);
		HashSet<Integer> visitedNode = new HashSet<Integer>();
		visitedNode.add(addEdge.getFromNode());
		while(!frontier.isEmpty()){
				GraphNode current = frontier.get(0);
				ArrayList<Edge> foundEdges = nodeToEdge.get(current.node);
				
				for(Edge edge: foundEdges){
					//if it is actually in the spanning tree and havent already been visited
					if(gn.st.contains(edge.getId())){
						int otherNode = 0;
						if(edge.getToNode()==current.node){
							otherNode = edge.getFromNode();
						}else otherNode = edge.getToNode();
						if(!visitedNode.contains(otherNode)){
							HashSet<Integer> newPath = current.path;
							newPath.add(edge.getId());
							if(edge.getToNode()==addEdge.getFromNode() && !gn.IN.contains(edge.getId())){
								
								return newPath;
							}else{
								GraphNode newNode = new GraphNode(otherNode,newPath);
								frontier.add(newNode);
							}
						}
						
					}
				}
				frontier.remove(0);
				visitedNode.add(current.node);

		}
		
		return null;
		
	}
	
}

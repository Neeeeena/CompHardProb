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
		
		//remember the weight!
		
	}
	
	
	
	public void FindMFMST(){
		ComputationalGraphNode current = new ComputationalGraphNode(rootST,new HashSet<Integer>(),new HashSet<Integer>(),null);
		checkB(current);
		findMFMST(current);
	}
	
	private void findMFMST(ComputationalGraphNode current){
		
			HashSet<Integer> notIncluded = (HashSet<Integer>)allIds.clone();
			//select an edge from the set of edges not in the current spanning tree and in out
			notIncluded.removeAll(current.st);
			notIncluded.removeAll(current.OUT);
			
			HashSet<Integer> alreadySwapped = new HashSet<Integer>();
			
			for(Integer f : notIncluded){
				HashSet<Integer> swaps = GetSwaps(current,f);
				//the children must not swap with other fs!
				if(swaps!=null){
				for(Integer e : swaps){
					
					//swap edges
					HashSet<Integer> childST = (HashSet<Integer>)current.st.clone();
					childST.remove(e);
					childST.add(f);
					
					HashSet<Integer> OUT = (HashSet<Integer>)current.OUT.clone();
					OUT.add(e);
					OUT.addAll(alreadySwapped);
					
					HashSet<Integer> IN = (HashSet<Integer>)current.IN.clone();
					IN.add(f);
					
					ComputationalGraphNode child = new ComputationalGraphNode(childST, IN,OUT,current);
					//update the weight of the spanning tree
					child.weight = updateWeights(child,f,e); 
					checkB(child);
					//depth first search
					findMFMST(child);
					
					alreadySwapped.add(f);
				}
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
		Iterator<Integer> iterator = node.st.iterator(); 
		System.out.println("FOOUND SPANNING TREE");
		while (iterator.hasNext()){
			   System.out.print(iterator.next());
		}
		
		//keep track of current best B and the matching spanning tree
		if(node.weight<B){
			bestST = node.st;
			B = node.weight;
		}
		System.out.println("");
	}
	
	private HashMap<Integer,ArrayList<Edge>> convertSTToMap(HashSet<Integer> st){
		HashMap<Integer,ArrayList<Edge>> nodeToEdge = new HashMap<Integer,ArrayList<Edge>>();
		   Iterator<Integer> iterator = st.iterator(); 
		      
		   // check values
		   while (iterator.hasNext()){
			   int id = iterator.next();
			   Edge edge = allEdges.get(id);
	   			if(nodeToEdge.containsKey(edge.getFromNode())){
					nodeToEdge.get(edge.getFromNode()).add(edge);
				}else{
					ArrayList<Edge> list = new ArrayList<Edge>();
					list.add(edge);
					nodeToEdge.put(edge.getFromNode(),list);
				}
				
				if(nodeToEdge.containsKey(edge.getToNode())){
					nodeToEdge.get(edge.getToNode()).add(edge);
				}else{
					ArrayList<Edge> list = new ArrayList<Edge>();
					list.add(edge);
					nodeToEdge.put(edge.getToNode(),list);
				}
			   
		   }
		   
		
		
		
		return nodeToEdge;
	}
	
	
	//finds the edges that can be removed from the spanning tree
	private HashSet<Integer> GetSwaps(ComputationalGraphNode gn, Integer f){
		
		Edge addEdge = allEdges.get(f);
		HashMap<Integer,ArrayList<Edge>> spanningTreeEdges = convertSTToMap(gn.st);
		//find cycles

		ArrayList<GraphNode> frontier = new ArrayList<GraphNode>();
		HashSet<Integer> path = new HashSet<Integer>();

		Integer goal = addEdge.getFromNode();
		
		GraphNode node = new GraphNode(addEdge.getToNode(), path);
		frontier.add(node);
		
		HashSet<Integer> visitedNode = new HashSet<Integer>();
		
		while(!frontier.isEmpty()){
				GraphNode current = frontier.get(0);
				ArrayList<Edge> foundEdges = spanningTreeEdges.get(current.node);
				
				for(Edge edge: foundEdges){
					//if it is actually in the spanning tree and havent already been visited
						int otherNode = edge.getOtherNode(current.node);

						
						if(!visitedNode.contains(otherNode)){
							HashSet<Integer> newPath = (HashSet<Integer>)current.path.clone();
							newPath.add(edge.getId());
							
							if(otherNode==goal && !gn.IN.contains(edge.getId())){
								return newPath;
							}						
						
							GraphNode newNode = new GraphNode(otherNode,newPath);
							frontier.add(newNode);
						}
						

				}
				frontier.remove(0);
				visitedNode.add(current.node);

		}
		
		return null;
		
	}
	
}

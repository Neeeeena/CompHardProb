package main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import model.Edge;
import model.SpanningTree;

public class Main {

    public static void main(String[] args) throws IOException{
        String everything;
        HashMap<Integer,Edge> edges = new HashMap<Integer,Edge>();
        HashMap<Integer, ArrayList<Edge>> nodeToEdge = new HashMap<Integer,ArrayList<Edge>>();
        int idi = 0;
        BufferedReader br = new BufferedReader(new FileReader("testTimeGlass.uwg"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                if(line.length() == 1) {
                    line = br.readLine();
                    continue;
                }
                String[] split = line.split("\\s+");
                Edge edge = new Edge(Integer.parseInt(split[0]),
                        Integer.parseInt(split[1]),
                        Integer.parseInt(split[2]),
                        idi);
                edges.put(idi,edge);
                
                
                
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
                
                
                
                idi++;

                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        } finally {
            br.close();
        }
        //Set mirrorWeight
        for(int i = 0; i < edges.size(); i++) {
            edges.get(i).setMirrorWeight(edges.get(edges.size() - 1 - i).getWeight());
        }


        for(int i = 0; i < edges.size(); i++) {
            System.out.println(edges.get(i).getWeight());
            System.out.println(edges.get(i).getMirrorWeight());
        }

        System.out.println(everything);
        
        
        
        HashSet<Integer> spanningTree = new HashSet<Integer>();
        spanningTree.add(0);
        spanningTree.add(2);
        spanningTree.add(4);
        spanningTree.add(5);
       // SpanningTree st = new SpanningTree(spanningTree,edges, nodeToEdge);
        SpanningTree st = new SpanningTree(getInitialSpanningTree(nodeToEdge), edges, nodeToEdge);

        
        st.FindMFMST();
        System.out.println(st.B);
        
        
    }


    private void MFSFT() {
        Stack<Edge> stack = new Stack<Edge>();

    }
    
    private static HashSet<Integer> getInitialSpanningTree(HashMap<Integer, ArrayList<Edge>> nodeToEdge) {
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

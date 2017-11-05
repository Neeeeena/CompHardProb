package main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import model.Edge;
import model.MST;
import model.SpanningTree;

public class Main {

    public static void main(String[] args) throws IOException{
        String everything;
        HashMap<Integer,Edge> edges = new HashMap<Integer,Edge>();
        HashMap<Integer, ArrayList<Edge>> nodeToEdge = new HashMap<Integer,ArrayList<Edge>>();
        int idi = 0;
        BufferedReader br = new BufferedReader(new FileReader("test03.uwg"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                
                String[] split = line.split("\\s+");
                if(split.length == 1) {
                    line = br.readLine();
                    continue;
                }
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


   /*     for(int i = 0; i < edges.size(); i++) {
            System.out.println(edges.get(i).getWeight());
            System.out.println(edges.get(i).getMirrorWeight());
        }

        System.out.println(everything);
        
        */
       
       // SpanningTree st = new SpanningTree(spanningTree,edges, nodeToEdge);
        SpanningTree st = MST.getInitialSpanningTree(nodeToEdge, edges);

        
        st.FindMFMST();
        
        System.out.println("Lowest value of B: "+st.B);
        
		Iterator<Integer> iterator = st.bestST.iterator(); 
		System.out.println("Edges in the best MFMST:");
		while (iterator.hasNext()){
			Edge edge = edges.get(iterator.next());
			   System.out.println(edge.getFromNode() + " "+edge.getToNode());
		}
        
        
    }

    
    
    
    
}

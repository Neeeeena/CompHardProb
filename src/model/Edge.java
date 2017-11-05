package model;

public class Edge {
    private int fromNode;
    private int toNode;
    private int weight;
    private int id;
    private int mirrorWeight;

    public Edge(int f, int t, int w, int id, int mirrorWeight){
        this.fromNode = f;
        this.toNode = t;
        this.weight = w;
        this.id = id;
        this.mirrorWeight = mirrorWeight;
    }

    public int getWeight() {
        return weight;
    }

    public int getFromNode() {
        return fromNode;
    }

    public int getToNode() {
        return toNode;
    }

    public int getId() {
        return id;
    }

    public boolean compareTo(Edge other) {
    	return weight < other.weight;
    }
    
    
//    public boolean compare(Edge e1, Edge e2) {
//    	return e1.getId() == e2.getId();
//    }

    public int getMirrorWeight() {
        return mirrorWeight;
    }

    public void setMirrorWeight(int w) {
        this.mirrorWeight = w;
    }
    
    public int getOtherNode(int nodeid) {
    	if(nodeid == this.fromNode)
    		return this.toNode;
    	else if(nodeid == this.toNode)
    		return this.fromNode;
    	else
    		return -1;
    }
    
    public Edge setFromNode(int fn) {
    	if(this.fromNode == fn) {
    		return this;
    	}else if(this.toNode == fn) {
    		return new Edge(this.toNode, this.fromNode, this.weight, this.id, this.mirrorWeight);
    	}else {
    		return null;
    	}
    }
}

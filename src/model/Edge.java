package model;

public class Edge {
    private int fromNode;
    private int toNode;
    private int weight;
    private int id;
    private int mirrorWeight;

    public Edge(int f, int t, int w, int id){
        this.fromNode = f;
        this.toNode = t;
        this.weight = w;
        this.id = id;
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

    public boolean compare(Edge e1, Edge e2) {
        return e1.getId() == e2.getId();
    }

    public int getMirrorWeight() {
        return mirrorWeight;
    }

    public void setMirrorWeight(int w) {
        this.mirrorWeight = w;
    }
}

class Edge {
    public int destNode;
    public int src;
    public int weight;

    public Edge(int d, int s, int w)
    {
        this.destNode = d;
        this.src = s;
        this.weight = w;
    }

    @Override
    public boolean equals(Object o)
    {
        Edge e = (Edge)o;
        return ((this.src == e.src && this.destNode == e.destNode) || (this.src == e.destNode && this.destNode == e.src));
    }
    
    @Override
    public String toString() {
        String toReturn = "dest: " + Integer.toString(destNode) + "source: " + Integer.toString(src);
        return toReturn;
    }
}

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

//class Edge {
//    public int destNode;
//    public int src;
//    public int weight;
//
//    public Edge(int d, int s, int w)
//    {
//        this.destNode = d;
//        this.src = s;
//        this.weight = w;
//    }
//
//    @Override
//    public boolean equals(Object o)
//    {
//        Edge e = (Edge)o;
//        return ((this.src == e.src && this.destNode == e.destNode) || (this.src == e.destNode && this.destNode == e.src));
//    }
//}
//
class WeightedGraph {
    private List<List<Edge>> g;
    private static final int NO_PARENT = -1;
    private PriorityQueue<Edge> minHeap;
    private boolean[] visited;
 
    public int[] parent;
    
    public WeightedGraph(List<List<Edge>> g) {
        this.g = g;
        this.minHeap = new PriorityQueue<Edge>((a, b) -> Integer.compare(a.weight, b.weight));
    }
    
    public int[] primMST(int start, int n) {
        parent = new int[n];
        visited = new boolean[n];
        Arrays.fill(parent, NO_PARENT);
        primMSThelper(start, n);
        return this.parent;
    }
    
    private void primMSThelper(int start, int n) {

        List<Edge> vertexAdjacencyList = this.g.get(start-1); 
        visited[start - 1] = true;

        for(Edge e : vertexAdjacencyList)
        {
            this.minHeap.add(e);            
        } 

        int count = 0;
        while(count < n - 1 && !minHeap.isEmpty())
        {
            Edge min = minHeap.poll();
            int destIndex = min.destNode - 1;
        
            if (visited[destIndex]) {
                continue;
            }
            
            count++;
            System.out.println("Source: " + Integer.toString(min.src) + " Destination: " + Integer.toString(min.destNode));
            
            visited[destIndex] = true;
            parent[destIndex] = min.src;

            List<Edge> vertexAdjacencyList2 = this.g.get(min.destNode -1); 

            for(Edge e : vertexAdjacencyList2)
            { 
                if (!visited[e.destNode - 1]) {
                    this.minHeap.add(e);            
                }
            } 
        }
    }
    
    public void kruskalMST() {
        // step 1: have a list of edges sorted by weight
        // step 2: loop over this sorted list and pop the shortest edge
        // step 3: add the vertices connected via this edge into a UFDS 
        // step 4: repeat the process but check if the vertices of the shortest edge are already in the UFDS
        // step 5: repeat until v vertices have been added 
        
    }
    
    
}

class WeightMain
{
    public static void main(String[] args)
    {
        Edge e1_2 = new Edge(2,1,3);
        Edge e1_4 = new Edge(4,1,4);
        Edge e2_1 = new Edge(1,2,3);
        Edge e2_3 = new Edge(3,2,3);
        Edge e2_4 = new Edge(4,2,4);

        Edge e3_2 = new Edge(2,3,3);
        Edge e3_4 = new Edge(4,3,2);
        Edge e3_5 = new Edge(5,3,4);

        Edge e4_1 = new Edge(1,4,4);
        Edge e4_2 = new Edge(2,4,4);
        Edge e4_3 = new Edge(3,4,2);
        Edge e4_5 = new Edge(5,4,5);
        Edge e4_6 = new Edge(6,4,3);

        Edge e5_3 = new Edge(3,5,4);
        Edge e5_4 = new Edge(4,5,5);
        Edge e5_6 = new Edge(6,5,4);

        Edge e6_4 = new Edge(4,6,3);
        Edge e6_5 = new Edge(5,6,4);

        List<Edge> al1 = new ArrayList<>(List.of(e1_2, e1_4));
        List<Edge> al2 = new ArrayList<>(List.of(e2_1, e2_3, e2_4));
        List<Edge> al3 = new ArrayList<>(List.of(e3_2, e3_4, e3_5));
        List<Edge> al4 = new ArrayList<>(List.of(e4_1, e4_2, e4_3, e4_5, e4_6));
        List<Edge> al5 = new ArrayList<>(List.of(e5_3, e5_4, e5_6));
        List<Edge> al6 = new ArrayList<>(List.of(e6_4, e6_5));

        List<List<Edge>> gr = new ArrayList<>();
        gr.add(al1);
        gr.add(al2);
        gr.add(al3);
        gr.add(al4);
        gr.add(al5);
        gr.add(al6);

        WeightedGraph g = new WeightedGraph(gr);
        g.primMST(1, 6);

        for(int i = 0; i < g.parent.length; i++)
        {
            System.out.println(Integer.toString(i) + " " + Integer.toString(g.parent[i]));
        }
    }
}

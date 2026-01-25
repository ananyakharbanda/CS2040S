import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

class KruskalGraph {
    public List<Edge> graphList;
    public UFDS ufds;
    private int size;
    
    public KruskalGraph(List<Edge> graphList, int[] elements) {
        this.graphList = graphList;
        this.graphList.sort(Comparator.comparingInt(edge -> edge.weight));
        this.size = graphList.size();
        this.ufds = new UFDS(elements);
    }
    
    public void kruskalMST() {
        // step 1: have a list of edges sorted by weight
        // step 2: loop over this sorted list and pop the shortest edge
        // step 3: add the vertices connected via this edge into a UFDS
        // step 4: repeat the process but check if the vertices of the shortest edge ar    e al    ready in the UFDS
        // step 5: repeat until v vertices have been added
        
        List<Edge> edgeInMST = new ArrayList<>();
         
        for (Edge e : this.graphList) {
            if (!this.ufds.isSameSet(e.destNode, e.src)) {
                this.ufds.union(e.destNode, e.src);
                edgeInMST.add(e);
            }
        }
        
        for (Edge e : edgeInMST) {
            System.out.println(e);
        } 
    }
}

class KruskalTest {
    public static void main(String[] args) {
        int[] elements = {1, 2, 3, 4, 5, 6};
        Edge e1_2 = new Edge(2,1,3);
        Edge e1_4 = new Edge(4,1,4);
        Edge e2_3 = new Edge(3,2,3);
        Edge e2_4 = new Edge(4,2,4);

        Edge e3_4 = new Edge(4,3,2);
        Edge e3_5 = new Edge(5,3,4);

        Edge e4_5 = new Edge(5,4,5);
        Edge e4_6 = new Edge(6,4,3);

        Edge e5_6 = new Edge(6,5,4);
            
        List<Edge> edgeList = new ArrayList<>();
        edgeList.add(e1_2);
        edgeList.add(e1_4);
        edgeList.add(e2_3);
        edgeList.add(e2_4);
        edgeList.add(e3_4);
        edgeList.add(e3_5);
        edgeList.add(e4_5);
        edgeList.add(e4_6);
        edgeList.add(e5_6);
        
        KruskalGraph k = new KruskalGraph(edgeList, elements);
        
        k.kruskalMST();
    }
}

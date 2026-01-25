import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

class Graph {
    public List<Edge> graphList;
    
    public graph(List<Edge> graphList) {
        this.graphList = graphList.sort(Comparator.comparingInt(edge -> edge.weight));
        this.size = graphList.length;
    }
    
    public void kruskalMST() {
        // step 1: have a list of edges sorted by weight
        // step 2: loop over this sorted list and pop the shortest edge
        // step 3: add the vertices connected via this edge into a UFDS
        // step 4: repeat the process but check if the vertices of the shortest edge ar    e al    ready in the UFDS
        // step 5: repeat until v vertices have been added
        
        
        
           


class WeightedGraph {
    private List<List<Integer>> g;
    
    public int[] parent;
    
    public Graph(List<List<Integer>> g) {
        this.g = g;
    }
    
    public primMST(int start, int n) {
        parent = new int[n];
        Arrays.fill(parent, -1);
        return primMSThelper(start);
    }
    
    private primMSThelper(int start) {
        List<Integer> done; 
        List<Integer> notDone;
    
        done = new ArrayList<>();
        notDone = new ArrayList<>();
        
    }    

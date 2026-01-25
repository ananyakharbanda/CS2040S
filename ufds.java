import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

class UFDS {
    private int[] parent;
    private int[] rank;
    private Map<Integer, Integer> mappings;

    public UFDS(int[] elements) {
        int size = elements.length;
        this.parent = new int[size];
        this.rank = new int[size];
        this.mappings = new HashMap<Integer, Integer>(); 
        
        for (int i = 0; i < size; i++) {
            this.parent[i] = i;
            this.rank[i] = 0;
            this.mappings.put(elements[i], i);
        }
    }
    
    public int find(int x) {
        Integer val = this.mappings.get(x);
        if (val == null) {
            return -1;
        }
        
        int toFind = val.intValue();
        
        // finding the root

        while(this.parent[toFind] != toFind) {
            toFind = this.parent[toFind];
        }
        int root = toFind;

        toFind = val.intValue();
        
        // path compression

        while (parent[toFind] != toFind) {
            int next = parent[toFind];
            parent[toFind] = root;
            toFind = next;
        }
        
        return root;
    }
    
    public void union(int val1, int val2) {
        int root1 = find(val1);
        int root2 = find(val2);
    
        if (root1 == -1 || root2 == -1) {
            System.out.println("values not found");
            return;
        } 
        
        if (root1 == root2) {
            System.out.println("same parents already");
            return;
        }
        
        int rank1 = this.rank[root1];
        int rank2 = this.rank[root2];
            
        if (rank1 > rank2) {
            this.parent[root2] = root1;
        } else if (rank2 > rank1) {
            this.parent[root1] = root2;
        } else {
            this.parent[root2] = root1;
            this.rank[root1]++;
        }
    }   
    
    public void print() {
        System.out.println("------------------------------");   
        System.out.println(Arrays.toString(this.parent));
        System.out.println(Arrays.toString(this.rank));   
        System.out.println("------------------------------");   
    }
    
    public boolean isSameSet(int val1, int val2) {
        return find(val1) == find(val2);   
    } 
} 


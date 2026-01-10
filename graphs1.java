import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class Stack {
    public ArrayList<Integer> data;

    public Stack() {
        this.data = new ArrayList<Integer>();     
    }
    
    public void push(int i) {
        this.data.add(i);
    }
    
    public boolean isEmpty() {
        return this.data.size() == 0;
    }
    
    public int pop() {
        int toreturn = this.data.get(this.data.size() - 1);
        this.data.remove(this.data.size() - 1);
        return toreturn;
    }
}


class Queue {
    public ArrayList<Integer> data;
    
    public Queue() {
        this.data = new ArrayList<Integer>();
    }

    public void push(int i) {
        this.data.add(i);
    }

    public boolean isEmpty() {
        return this.data.size() == 0;   
    }
 
    public int pop() {
        int toReturn = this.data.get(0);
        this.data.remove(0);
        return toReturn;
    }
}


class Graph {

    private List<List<Integer>> g;
    private int[] colours;
    private boolean isBp;
    private Boolean[] visited;
    private boolean hasCycle;
    private int[] visiting;
    
    public static final int WHITE = 0;
    public static final int RED = 1;
    public static final int BLUE = 2;

    public Graph(List<List<Integer>> g) {
        this.g = g;
        isBp = true;
    }
    
    public void traverseDFS(int start) {
        Stack s = new Stack();
        List<Integer> visited = new ArrayList<>();
        
        s.push(start);  
        while (!s.isEmpty()) {
            int curr = s.pop();
            if (!visited.contains(curr)) {
                visited.add(curr);
                System.out.println(curr);
                List<Integer> neighbours = g.get(curr - 1);
                for (int i : neighbours) {  
                    s.push(i);
                }
            }
        }
    }
    
    public void traverseBFS(int start) {
        Queue q = new Queue();
        List<Integer> visited = new ArrayList<>();
        
        q.push(start);
        while(!q.isEmpty()) {
            int curr = q.pop();
            if (!visited.contains(curr)) {
                visited.add(curr);
                System.out.println(curr);
                List<Integer> neighbours = g.get(curr - 1);
                for (int i : neighbours) {
                    q.push(i);
                }
            }
        }
    }


    public boolean checkBipartite(int start, int startColour, int n) { 
        colours = new int[n];
        Arrays.fill(colours, WHITE);
        bipartiteDFS(start, startColour);
        return isBp;
    }

    private void bipartiteDFS(int curr, int colour) {

        if (colours[curr - 1] != WHITE) {
            if (colours[curr - 1] != colour) {
                this.isBp = false;
            }
            return;
        }

        colours[curr - 1] = colour;
    
        for (int v : g.get(curr - 1)) {
            if (colour  == BLUE) {
                bipartiteDFS(v, RED);
            } else if(colour == RED){
                bipartiteDFS(v, BLUE);
            }
        }
    }
    
    public boolean checkCycleU(int start, int n) {  
        visited = new Boolean[n];
        Arrays.fill(visited, false);
        this.hasCycle = false;
        cycleDFSU(start, -1);
        return this.hasCycle;
    }
    
    private void cycleDFSU(int curr, int prev) {
        visited[curr - 1] = true;
        
        for (int v : g.get(curr - 1)) {
            if (v != prev && visited[v - 1] == true) {
                this.hasCycle = true;
                return;
            } else if (visited[v - 1] == false) {
                cycleDFSU(v, curr);
            } else {
                continue;
            }
        }
    } 
    
    public boolean checkCycleD(int start, int n) {
        visiting = new int[n];
        Arrays.fill(visiting, 0);
        this.hasCycle = false;
        cycleDFSD(start);
        for (int i = 1; i <= n; i++) {
            if (visiting[i - 1] == 0) {
                cycleDFSD(i);
            }
        }
        return this.hasCycle;
    }
    
    private void cycleDFSD(int curr) {
        visiting[curr - 1] = 1;
        
        for (int v : g.get(curr - 1)) {
            if (visiting[v - 1] == 1) {
                this.hasCycle = true;
                return;
            } else if (visiting[v - 1] == 0) {
                cycleDFSD(v);  
            } else {
                continue;
            }
        }
        visiting[curr - 1] = 2;
    }
        
class GraphMain {
    public static void main(String[] args) {

        List<List<Integer>> adjacencyList = new ArrayList<>();
        List<List<Integer>> adjacencyList2 = new ArrayList<>();
        List<List<Integer>> adjacencyList3 = new ArrayList<>();
        List<List<Integer>> adjacencyList4 = new ArrayList<>();
        List<List<Integer>> adjacencyList5 = new ArrayList<>();
        
        adjacencyList.add(new ArrayList<Integer>(Arrays.asList(2,3)));
        adjacencyList.add(new ArrayList<Integer>(Arrays.asList(1,3,4,5)));
        adjacencyList.add(new ArrayList<Integer>(Arrays.asList(1,2)));
        adjacencyList.add(new ArrayList<Integer>(Arrays.asList(2,5)));
        adjacencyList.add(new ArrayList<Integer>(Arrays.asList(2,4)));

        adjacencyList2.add(new ArrayList<Integer>(Arrays.asList(2,4)));
        adjacencyList2.add(new ArrayList<Integer>(Arrays.asList(1,3)));
        adjacencyList2.add(new ArrayList<Integer>(Arrays.asList(2,4)));
        adjacencyList2.add(new ArrayList<Integer>(Arrays.asList(1,3)));

        adjacencyList3.add(new ArrayList<Integer>(Arrays.asList(2)));        
        adjacencyList3.add(new ArrayList<Integer>(Arrays.asList(1,3,4)));  
        adjacencyList3.add(new ArrayList<Integer>(Arrays.asList(2,5)));     
        adjacencyList3.add(new ArrayList<Integer>(Arrays.asList(2)));   
        adjacencyList3.add(new ArrayList<Integer>(Arrays.asList(3)));        

        adjacencyList4.add(new ArrayList<Integer>(Arrays.asList(2, 3)));
        adjacencyList4.add(new ArrayList<Integer>(Arrays.asList(4)));
        adjacencyList4.add(new ArrayList<Integer>(Arrays.asList(5)));
        adjacencyList4.add(new ArrayList<Integer>(Arrays.asList()));
        adjacencyList4.add(new ArrayList<Integer>(Arrays.asList()));
        
        adjacencyList5.add(new ArrayList<Integer>(Arrays.asList(2)));    
        adjacencyList5.add(new ArrayList<Integer>(Arrays.asList(3)));    
        adjacencyList5.add(new ArrayList<Integer>(Arrays.asList(4)));   
        adjacencyList5.add(new ArrayList<Integer>(Arrays.asList(2)));    
        adjacencyList5.add(new ArrayList<Integer>(Arrays.asList()));

         
        Graph g = new Graph(adjacencyList);
        Graph g2 = new Graph(adjacencyList2);
        Graph g3 = new Graph(adjacencyList3);
        Graph g4 = new Graph(adjacencyList4);
        Graph g5 = new Graph(adjacencyList5);

        g.traverseDFS(1);

        g.traverseBFS(1);

        System.out.println(g.checkBipartite(1, Graph.BLUE, 5)); 
        System.out.println(g2.checkBipartite(1, Graph.BLUE, 4)); 
        
        System.out.println("------");
        
        System.out.println(g.checkCycleU(1, 5));
        System.out.println(g2.checkCycleU(1, 4));
        System.out.println(g3.checkCycleU(1, 5));
        
        System.out.println("------");

        System.out.println(g4.checkCycleD(1,5));
        System.out.println(g5.checkCycleD(2, 5));
    }
}

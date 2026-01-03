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
        this.data.remove(this.data.get(this.data.size() - 1));
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
    public List<List<Integer>> g;
        
    public Graph(List<List<Integer>> g) {
        this.g = g;
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
}


class GraphMain {
    public static void main(String[] args) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        adjacencyList.add(new ArrayList<Integer>(Arrays.asList(2,3)));
        adjacencyList.add(new ArrayList<Integer>(Arrays.asList(1,3,4,5)));
        adjacencyList.add(new ArrayList<Integer>(Arrays.asList(1,2)));
        adjacencyList.add(new ArrayList<Integer>(Arrays.asList(2,5)));
        adjacencyList.add(new ArrayList<Integer>(Arrays.asList(2,4)));
        Graph g = new Graph(adjacencyList);
        g.traverseDFS(1);
        
    }
}

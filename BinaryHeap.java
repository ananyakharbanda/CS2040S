import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class Pair <S, T> {
    S first;
    T second;

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}


class BinaryMinHeap {
    public ArrayList<Pair<Integer, String>> minHeap;
    public Map<Integer, Integer> idToPosMap;
    public Map<Integer, Integer> posToIDMap;
    public static int RUNNING_ID = 0;

    public BinaryMinHeap() {
        this.minHeap = new ArrayList<>();
        this.idToPosMap = new HashMap<>();
        this.posToIDMap = new HashMap<>();
    }

    public void insert(int priority, String value){
        Pair<Integer, String> p = new Pair<>();
        p.first = priority;
        p.second = value;
        this.insert(p);
    }

    public void insert(Pair<Integer, String> node){
        RUNNING_ID++;
        this.minHeap.add(node);
        this.idToPosMap.put(RUNNING_ID, this.size() - 1);
        this.posToIDMap.put(this.size() - 1, RUNNING_ID);
        this.bubbleUp(this.size() - 1);
    }

    public void bubbleUp(int pos){
        System.out.println("main " + Integer.toString(pos));
        System.out.println("----------");
        while ((pos / 2) != 0 || this.minHeap.get(pos).first < this.minHeap.get(this.getParent(pos)).first){
            System.out.println(pos);
            System.out.println("swapping " + Integer.toString(pos) + " with " + Integer.toString(getParent(pos)));
            this.swap(pos, getParent(pos));
            pos = this.getParent(pos);
        } 
        System.out.println("----------");
    }

    public int getParent(int pos) {
       return pos / 2; 
    }

    public void swap(int pos1, int pos2){

        // first swap in minHeap arry
        Pair<Integer, String> temp = this.minHeap.get(pos1);
        this.minHeap.set(pos1, this.minHeap.get(pos2));
        this.minHeap.set(pos2, temp);

        // now correct idToPos and posToID maps
        int id1 = this.posToIDMap.get(pos1);
        int id2 = this.posToIDMap.get(pos2);
        
        this.posToIDMap.put(pos2, id1); 
        this.posToIDMap.put(pos1, id2); 

        this.idToPosMap.put(id1, pos2);
        this.idToPosMap.put(id2, pos1);
    }

    public int size(){
        return this.minHeap.size();
    }

    public Pair<Integer, String> extracMin() {
        this.swap(0, this.size() - 1);
        Pair<Integer, String> p = this.minHeap.remove(this.size() - 1);
        bubbleDown(0);
        int removeID = this.posToIDMap.get(this.size());
        this.posToIDMap.remove;   
        
        return p;
    }
    
    public void bubbleDown(int pos) {
        while(pos * 2 < this.size()) {
            if (this.minHeap.get(pos) > this.minHeap.get(pos * 2) {
                swap(pos, pos * 2);   
                pos = pos * 2;
            } else if (this.minHeap.get(pos) > this.minHeap.get(pos * 2 + 1)) {
                swap(pos, pos * 2 + 1);
                pos = pos * 2 + 1;
            }
        }
    }
}

class MinHeapTest{
    public static void main(String[] args){
        BinaryMinHeap bmh = new BinaryMinHeap();

        bmh.insert(8, "8apple");
        bmh.insert(2, "2apple");
        bmh.insert(7, "7apple");
        bmh.insert(9, "9apple");
        bmh.insert(5, "5apple");
        bmh.insert(10, "10apple");
        bmh.insert(3, "3apple");

        System.out.println(bmh.minHeap);
    }
}

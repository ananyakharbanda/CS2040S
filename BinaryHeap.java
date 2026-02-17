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
        while (pos > 0 && this.minHeap.get(pos).first < this.minHeap.get(this.getParentPos(pos)).first){
            System.out.println(pos);
            System.out.println("swapping " + Integer.toString(pos) + " with " + Integer.toString(getParentPos(pos)));
            this.swap(pos, getParentPos(pos));
            pos = this.getParentPos(pos);
        } 
        System.out.println("----------");
    }

    public int getParentPos(int pos) {
       return (pos - 1) / 2; 
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

    public Pair<Integer, String> extractMin() {
        
        if(this.size() == 0) {
            return null;
        }

        this.swap(0, this.size() - 1);
        int removeID = this.posToIDMap.get(this.size() - 1);
        this.posToIDMap.remove(this.size() - 1);    
        this.idToPosMap.remove(removeID);
        
        Pair<Integer, String> p = this.minHeap.remove(this.size() - 1);
        
        if (this.size() > 0) { 
            bubbleDown(0);
        }
         
        return p;
    }
    
    public Pair<Integer, String> peek() {
        Pair<Integer, String> p = this.minHeap.get(0);
        return p;
    }
   
    private Pair<Integer, String> getLeft(int pos) {
        if (pos * 2 + 1 < this.minHeap.size()) {
            return this.minHeap.get(pos * 2 + 1);
        }
        return null;
    } 
    
    private int getLeftPos(int pos) {
        if (pos * 2 + 1 < this.minHeap.size()) {
            return pos * 2 + 1;
        }
        return -1;
    }
    
    private int getRightPos(int pos) {
        if (pos * 2 + 2 < this.minHeap.size()) {
            return pos * 2 + 2;
        }
        return -1;
    }
    
    private Pair<Integer, String> getRight(int pos) {
        if (pos * 2 + 2 < this.minHeap.size()) {
            return this.minHeap.get(pos * 2 + 2);
        }
        return null;
    }

    private int getSwapPositionForBubbleDown(int l, int r) {
        if (l != -1 && r != -1) {
           if (this.minHeap.get(l).first < this.minHeap.get(r).first) {
                return l;
            } else {
                return r;
            }
        } else if (l != -1) {
            return l;
        } else {
            return -1;
        }
    }

    public void bubbleDown(int pos) {
        while(pos * 2 + 1 < this.size()) {
            int lPos = this.getLeftPos(pos);
            int rPos = this.getRightPos(pos);
            int swapPos = this.getSwapPositionForBubbleDown(lPos, rPos);

            if (swapPos != -1 && this.minHeap.get(pos).first > this.minHeap.get(swapPos).first){
                swap(pos, swapPos);   
                pos = swapPos;
            } else {
                break;
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
        
        Pair<Integer, String> p = bmh.extractMin();
        
        System.out.println(bmh.minHeap);
        System.out.println(p);

        System.out.println("----------------------");
        Pair<Integer, String> p1 = bmh.extractMin();
        
        System.out.println(bmh.minHeap);
        System.out.println(p1);

        System.out.println("----------------------");
        bmh.extractMin();
        System.out.println(bmh.minHeap);

        System.out.println("----------------------");
        bmh.extractMin();
        System.out.println(bmh.minHeap);

        System.out.println("----------------------");
        bmh.extractMin();
        System.out.println(bmh.minHeap);

        System.out.println("----------------------");
        bmh.extractMin();
        System.out.println(bmh.minHeap);

        System.out.println("----------------------");
        bmh.extractMin();
        System.out.println(bmh.minHeap);

        System.out.println("----------------------");
        bmh.extractMin();
        System.out.println(bmh.minHeap);
    }
}

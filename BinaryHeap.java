
class Pair <S, T> {
    S first;
    T second;
}


class BinaryMinHeap {
    public ArrayList<Pair<Integer, String>> minHeap;
    public ArrayList<Integer> posToId;
    public ArrayList<Integer> idToPos;
    public int size; 
    
    public BinaryMinHeap() {
        this.minHeap = new ArrayList<>();
        this.minHeap.add(null);
        this.posToId = new ArrayList<>();
        this.posToId.add(null);
        this.idToPost = new ArrayList<>();
        this.idToPost.add(null);
        this.size = 0;
    }
    
    public int insert(int priority, String value) {
        this.minHeap.add(Pair(priority, value));
        this.size += 1;
         
    }
   
    public void bubbleUp(Pair<Integer, String> node) {
        while (this.size / 2 != 0 || node.first < this.minHeap.get(this.size / 2)) {
            temp = this.minHeap.get(this.size / 2);
            this.minHeap[this.size / 2] = this.minHeap[this.size];
            this.minHeap[this.size] = temp;
            
             
    } 
     
    public Pair<Integer, String> getMin() {
    
    }
    
    public Pair<Integer, String> peekMin() {
    
    }
    
    public int size() {
    
    }
    
    public void heapify(ArrayList<Pair<Integer, String>> arr) {
    
    }
    
     


class Node<T extends Comparable<T>> {
    public Node<T> left;
    public Node<T> right;
    public T value;

    public Node(T val) {
        this.value = val;
        this.left = null;
        this.right = null;
    }
}

class AVLTree<T extends Comparable<T>> {

    public Node<T> root;

    public AVLTree() {
        this.root = null;
    }

    public void insert(T val) {
        if (this.root == null) {
            this.root = new Node<T>(val);
        } else {
            insertHelper(val, this.root);
        }
    }

    private Node<T> insertHelper(T val, Node<T> rt) {
        if (rt == null) {
            rt = new Node<T>(val);
            return rt;
        } else if(val.compareTo(rt.value) > 0) {
            rt.right = insertHelper(val, rt.right);
            return rt;
        } else if(val.compareTo(rt.value) < 0) {
            rt.left = insertHelper(val, rt.left);
            return rt;
        }
        return rt;
    }
    
    public void delete(T val) {
        this.root = this.deleteHelper(val, this.root); 
    }
        
    private Node<T> deleteHelper(T val, Node<T> rt) {
        if (rt == null) {   
            System.out.println("invalid key");
            return rt;
        } 
        
        if (val.compareTo(rt.value) < 0) {
            rt.left = deleteHelper(val, rt.left);
            return rt;
        } else if (val.compareTo(rt.value) > 0) {
            rt.right = deleteHelper(val, rt.right);
            return rt;  
        } else {
            if (rt.left == null && rt.right == null) {
                return null;
            } else if (rt.left == null) {
                return rt.right;
            } else if (rt.right == null) {
                return rt.left;
            } else {
                Node<T> successor = findMin(rt.right);
                rt.value = successor.value;
                rt.right = deleteHelper(successor.value, rt.right);
                return rt;
            }
        }
    } 
    
    private Node<T> findMin(Node<T> rt) {   
        while (rt.left != null) {
            rt = rt.left;
        } 
        return rt;   
    }
   
    
    public void inorder() {
        inorderHelper(this.root);
    }

    private void inorderHelper(Node<T> rt){
        if(rt == null){
            return;
        }
        inorderHelper(rt.left);
        System.out.println(rt.value);
        inorderHelper(rt.right);
    }
}


class AVLTreeMain{
    public static void main(String[] args){
        AVLTree<String> avl = new AVLTree<>();

        avl.insert("ananya");
        avl.insert("neha");
        avl.insert("paras");
        avl.insert("arun");
        avl.insert("rinu");

        avl.inorder();
        System.out.println("------------");
    
        avl.delete("neha");
        avl.inorder();
    }
}

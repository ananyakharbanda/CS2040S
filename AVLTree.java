class Node<T extends Comparable<T>> {
    public Node<T> left;
    public Node<T> right;
    public T value;
    public int height;

    public Node(T val) {
        this.value = val;
        this.left = null;
        this.right = null;
        this.height = 0;
    }
}

class AVLTree<T extends Comparable<T>> {

    public Node<T> root;

    public AVLTree() {
        this.root = null;
    }

    private int height(Node<T> node) {
        return (node == null) ? -1 : node.height;
    }
   
    public int getBalance(Node<T> node) {
        return (height(node.left) - height(node.right));
    }
     
    private Node<T> leftRotate(Node<T> node) {
        Node<T> newRoot = node.right;
        Node<T> temp  = newRoot.left;
       
        newRoot.left = node;
        node.right = temp;
         
        node.height = 1 + Math.max(height(node.right), height(node.left));
        newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));
        return newRoot;
    }
    
    private Node<T> rightRotate(Node<T> node) {
        Node<T> newRoot = node.left;
        Node<T> temp = newRoot.right;
    
        newRoot.right = node;
        node.left = temp;
    
        node.height = 1 + Math.max(height(node.right), height(node.left));
        newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));
        return newRoot; 
    }
    
    public Node<T> findNode(T val) {
        if (this.root == null) {
            return null;
        } else {
            return findNodeHelper(val, this.root);
        }
    }
    
    private Node<T> findNodeHelper(T val, Node<T> rt) {
        if (val.compareTo(rt.value) == 0) {  
            return rt;
        } else if (val.compareTo(rt.value) > 0) {
            return findNodeHelper(val, rt.right);
        } else if (val.compareTo(rt.value) < 0) {
            return findNodeHelper(val, rt.left);
        } else {
            return rt;
        } 
    }
    
    public void insert(T val) {
        if (this.root == null) {
            this.root = new Node<T>(val);
        } else {
            this.root = insertHelper(val, this.root);
        }
    }

    private Node<T> insertHelper(T val, Node<T> rt) {
        if (rt == null) {
            rt = new Node<T>(val);
            return rt;
        } else if(val.compareTo(rt.value) > 0) {
            rt.right = insertHelper(val, rt.right);
        } else if(val.compareTo(rt.value) < 0) {
            rt.left = insertHelper(val, rt.left);
        }
    
        rt.height = 1 + Math.max(height(rt.right), height(rt.left));
        int balance = getBalance(rt);
        
        if (balance > 1 && getBalance(rt.left) >= 0) {
            return rightRotate(rt);   
        } else if (balance < -1 && getBalance(rt.right) <= 0) {
            return leftRotate(rt);
        } else if (balance > 1 && getBalance(rt.left) < 0) {
            rt.left = leftRotate(rt.left);
            return rightRotate(rt);
        } else if (balance < -1 && getBalance(rt.right) > 0) {
            rt.right = rightRotate(rt.right);
            return leftRotate(rt);
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
        } else if (val.compareTo(rt.value) > 0) {
            rt.right = deleteHelper(val, rt.right);
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
            }
        }
        
        rt.height = 1 + Math.max(height(rt.right), height(rt.left));
        int balance = getBalance(rt);
            
        if (balance > 1 && getBalance(rt.left) >= 0) {
            return rightRotate(rt);   
        } else if (balance < -1 && getBalance(rt.right) <= 0) {
            return leftRotate(rt);
        } else if (balance > 1 && getBalance(rt.left) < 0) {
            rt.left = leftRotate(rt.left);
            return rightRotate(rt);
        } else if (balance < -1 && getBalance(rt.right) > 0) {
            rt.right = rightRotate(rt.right);
            return leftRotate(rt);
        }
        return rt;
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
        AVLTree<Integer> avl = new AVLTree<>();

        // avl.insert("ananya");
        // avl.insert("neha");
        // avl.insert("paras");
        // avl.insert("arun");
        // avl.insert("rinu");
        
        for (int i = 1; i <= 10000; i++) {
            avl.insert(i);
        }

        avl.inorder();
        System.out.println("------------");
            
        Node<Integer> toFind = avl.findNode(6144);
        System.out.println(avl.root.value);
        System.out.println(avl.root.left.value);
        System.out.println(avl.root.right.value);
        System.out.println(toFind.height);
        
        System.out.println("------------");
        for (int i = 1; i <= 5000; i++) { 
            avl.delete(i);
        }
        System.out.println(avl.root.value);
        System.out.println(avl.root.left.value);
        System.out.println(avl.root.right.value);
        System.out.println(toFind.height);
    }
}

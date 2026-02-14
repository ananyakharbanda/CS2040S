class Node {
    public int key;
    public String value;
    public Node left;
    public Node right;
    
    public Node(int key, String value) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
    }
}


class BinarySearchTree {
    public Node root;

    public BinarySearchTree() {
        this.root = null;
    }
    
    public void insert(int key, String value) {
        if (this.root == null) {
            this.root = new Node(key, value);
        } else {
            insertHelper(this.root, key, value);
        }
    }

    public Node insertHelper(Node r, int key, String value) {
        if (r == null) {
            return  new Node(key, value);
        } else if (key > r.key) {
            r.right = insertHelper(r.right, key, value);
            return r;
        } else {
            r.left = insertHelper(r.left, key, value);
            return r;
        } 
    }
    
    public void inorder(Node r) {
        if (r != null) {
            inorder(r.left);
            System.out.println(String.format("key: %d, value: %s", r.key, r.value));
            inorder(r.right);
        }
    }
    
    public Node lookup(int key) {
        return lookupHelper(this.root, key);
    }
    
    public Node lookupHelper(Node r, int k) {
        if (r == null) {
            System.out.println(String.format("value not found for key: %d", k));
            return null;
        } else if (k == r.key) {
            System.out.println(r.value);
            return r;
        } else if (k < r.key) {
            lookupHelper(r.left, k);
        } else if (k > r.key) {
            lookupHelper(r.right, k);
        }
        return r;
    }
    
    public void delete(int key) {
        deleteHelper(this.root, key, null, null);
    }
    public void deleteHelper(Node r, int k, Node prev, Boolean l) {
        if (r == null) {
            System.out.println(String.format("value not found for key: %d", k));
        } else if (k < r.key) {
            deleteHelper(r.left, k, r, true);
        } else if (k > r.key) {
            deleteHelper(r.right, k, r, false);
        } else if (k == r.key) {
            if (r.left == null && r.right == null) {
                if (l == true) {
                    prev.left = null;
                } else {
                    prev.right = null;
                }

            } else if (r.left == null && r.right != null) {
                if (l == true) { 
                    prev.left = r.right;
                } else { 
                    prev.right = r.right;
                }

            } else if (r.left != null && r.right == null) {
                if (l == true) {
                    prev.left = r.left;
                } else {
                    prev.right = r.left;
                }

            } else {
                Node newRoot = r.right;
                Node leftNode = r.left;
                Node rootPrev = null; 
                while (newRoot.left != null) {
                    rootPrev = newRoot;
                    newRoot = newRoot.left;
                }

                if (l == true) {
                    prev.left = newRoot;
                } else {
                    prev.right = newRoot;
                }
                
                newRoot.left = leftNode;
                if (newRoot.right != null) {
                    if (r.right != newRoot) {
                        r.right = newRoot.right;
                        newRoot.right = r.right;
                    } else {
                        while (r.right.left != null) {
                            r.right = r.right.left;
                        }
                        r.right.left = newRoot.right;
                    }
                }
            }
        }
    }
}


class BSTMain {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(5, "apple");
        
        // System.out.println(String.format("key: %d, value: %s", bst.root.key, bst.root.value));
        bst.insert(10, "banana");
        bst.insert(4, "carrot");
        bst.insert(7, "guava");
        bst.inorder(bst.root);
        
        bst.lookup(5);
        bst.lookup(7);
        bst.lookup(4);
        bst.lookup(8);
    
        bst.delete(10);
        bst.inorder(bst.root);  
        bst.lookup(10);
    }
}            

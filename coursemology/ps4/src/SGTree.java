/**
 * Scapegoat Tree class
 *
 * This class contains an implementation of a Scapegoat tree.
 */

public class SGTree {
    /**
     * TreeNode class.
     *
     * This class holds the data for a node in a binary tree.
     *
     * Note: we have made things public here to facilitate problem set grading/testing.
     * In general, making everything public like this is a bad idea!
     *
     */
    public static class TreeNode {
        int key;
        public TreeNode left = null;
        public TreeNode right = null;
        public TreeNode parent = null;
        int weight;

        TreeNode(int k) {
            this.key = k;
            this.weight = 1;
        }
    }

    // Root of the binary tree
    public TreeNode root = null;

    /**
     * Counts the number of nodes in the subtree rooted at node
     *
     * @param node the root of the subtree
     * @return number of nodes
     */
    public int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    /**
     * Builds an array of nodes in the subtree rooted at node
     *
     * @param node the root of the subtree
     * @return array of nodes
     */
    public TreeNode[] enumerateNodes(TreeNode node) {
        int numNodes = countNodes(node);
        TreeNode[] allNodes = new TreeNode[numNodes];
        enumNodesHelper(node, allNodes, 0);

        return allNodes;
    }

    private int enumNodesHelper(TreeNode node, TreeNode[] arr, int index) {
        if (node == null) {
            return index;
        }
        index = enumNodesHelper(node.left, arr, index);
        arr[index] = node;
        index++;

        index = enumNodesHelper(node.right, arr, index);
        return index;
    }

    /**
     * Builds a tree from the list of nodes
     * Returns the node that is the new root of the subtree
     *
     * @param nodeList ordered array of nodes
     * @return the new root node
     */
    public TreeNode buildTree(TreeNode[] nodeList) {
        int n = nodeList.length;

        return buildTreeHelper(nodeList, 0, n - 1);
    }

    public TreeNode buildTreeHelper(TreeNode[] nodeList, int low, int high) {
        if (low > high) {
            return null;
        }

        int mid = (low + high) / 2;
        TreeNode root = nodeList[mid];
        root.left = buildTreeHelper(nodeList, low, mid - 1);
        if (root.left != null) {
            root.left.parent = root;
        }

        root.right = buildTreeHelper(nodeList, mid + 1, high);
        if (root.right != null) {
            root.right.parent = root;
        }

        if (root.left != null && root.right != null) {
            root.weight = root.left.weight + root.right.weight + 1;
        } else if (root.right != null) {
            root.weight = root.right.weight + 1;
        } else if (root.left != null) {
            root.weight = root.left.weight + 1;
        } else {
            root.weight = 1;
        }

        return root;
    }


    /**
     * Determines if a node is balanced. If the node is balanced, this should return true. Otherwise, it should return
     * false. A node is unbalanced if either of its children has weight greater than 2/3 of its weight.
     *
     * @param node a node to check balance on
     * @return true if the node is balanced, false otherwise
     */
    public boolean checkBalance(TreeNode node) {
        if (node.left != null && node.right != null) {
            int compareLeft = node.left.weight;
            int compareRight = node.right.weight;

            if (3 * compareLeft > 2 * node.weight || 3 * compareRight > 2 * node.weight) {
                return false;
            }
        } else if (node.left != null) {
            int compareLeft = node.left.weight;
            if (3 * compareLeft > 2 * node.weight) {
                return false;
            }
        } else if (node.right != null) {
            int compareRight = node.right.weight;
            if (3 * compareRight > 2 * node.weight) {
                return false;
            }
        }
        return true;
    }

    /**
     * Rebuilds the subtree rooted at node
     *
     * @param node the root of the subtree to rebuild
     */
    public void rebuild(TreeNode node) {
        // Error checking: cannot rebuild null tree
        if (node == null) {
            return;
        }

        TreeNode p = node.parent;
        TreeNode[] nodeList = enumerateNodes(node);
        TreeNode newRoot = buildTree(nodeList);

        if (p == null) {
            root = newRoot;
        } else if (node == p.left) {
            p.left = newRoot;
        } else {
            p.right = newRoot;
        }

        newRoot.parent = p;

        updateWeightUpwards(p);
    }

    /**
     * Inserts a key into the tree
     *
     * @param key the key to insert
     */
    public void insert(int key) {
        if (root == null) {
            root = new TreeNode(key);
            return;
        }

        TreeNode inserted = insertAndReturn(key, root);
        updateWeightUpwards(inserted.parent);
        TreeNode current = inserted.parent;
        TreeNode scapegoat = null;

        while (current != null) {
            if (!checkBalance(current)) {
                scapegoat = current;
            }
            current = current.parent;
        }

        if (scapegoat != null) {
            rebuild(scapegoat);
        }
    }

    // Helper method to insert a key into the tree
    private TreeNode insertAndReturn(int key, TreeNode node) {
        if (key <= node.key) {
            if (node.left == null) {
                node.left = new TreeNode(key);
                node.left.parent = node;
                return node.left;
            } else {
                return insertAndReturn(key, node.left);   // FIX: return + correct function
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode(key);
                node.right.parent = node;
                return node.right;
            } else {
                return insertAndReturn(key, node.right);
            }
        }
    }

    private void updateWeightUpwards(TreeNode node) {
        while (node != null) {
            int lw = (node.left == null) ? 0 : node.left.weight;
            int rw = (node.right == null) ? 0 : node.right.weight;
            node.weight = 1 + lw + rw;
            node = node.parent;
        }
    }

//    // Simple main function for debugging purposes
//    public static void main(String[] args) {
//        SGTree tree = new SGTree();
//        for (int i = 0; i < 100; i++) {
//            tree.insert(i);
//        }
//        tree.rebuild(tree.root);
//    }
}
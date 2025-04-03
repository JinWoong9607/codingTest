import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int x, y ,value; 
    TreeNode left;
    TreeNode right;
    
    TreeNode(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}

class BinaryTree {
    TreeNode root;
        void insert(TreeNode parent, TreeNode child) {
        if (child.y >= parent.y) { 
            return;
        }
        if (child.x < parent.x) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                insert(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                insert(parent.right, child);
            }
        }
    }
    
    void addNode(TreeNode child) {
        if (root == null) {
            root = child;
        } else {
            insert(root, child);
        }
    }
    
    void preorder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        
        result.add(node.value);
        preorder(node.left, result);
        preorder(node.right, result);
    }
    
    void postorder(TreeNode node, List<Integer> result) {
        if (node==null) {
            return;
        }
        
        postorder(node.left, result);
        postorder(node.right, result);
        result.add(node.value);
    }
}
class Solution {
    public int[][] solution(int[][] nodeinfo) {
        BinaryTree tree = new BinaryTree();
        int[][] nodewithnumber = new int[nodeinfo.length][3];
        for (int i = 0 ; i<nodeinfo.length; i++) {
            nodewithnumber[i][0] = nodeinfo[i][0];
            nodewithnumber[i][1] = nodeinfo[i][1];
            nodewithnumber[i][2] = i+1;
        }
        Arrays.sort(nodewithnumber, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(b[1], a[1]);
            }
            return Integer.compare(a[0], b[0]);
        });
        
        for (int i = 0 ; i< nodewithnumber.length; i++) {
            tree.addNode(new TreeNode(nodewithnumber[i][0], nodewithnumber[i][1], nodewithnumber[i][2]));
        }
        
        List<Integer> preorderResult = new ArrayList<>();
        List<Integer> postorderResult = new ArrayList<>();
        tree.preorder(tree.root, preorderResult);
        tree.postorder(tree.root, postorderResult);
        
        
        int[][] answer = new int[2][nodewithnumber.length];
        for (int i = 0; i<nodewithnumber.length; i++) {
            answer[0][i] = preorderResult.get(i);
            answer[1][i] = postorderResult.get(i);
        }
        return answer;
    }
}
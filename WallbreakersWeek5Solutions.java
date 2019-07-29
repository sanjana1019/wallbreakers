
//Week 5 Solutions 


//---------- n-ary postorder traversal -------------------// 

class Solution {
    public List<Integer> postorder(Node root) {
        //will hold the solution 
        List<Integer> sol = new ArrayList<Integer>(); 
        
        //call helper function
        postHelper(root, sol); 
        
        //return solution 
        return sol; 
        
    }
    
    public void postHelper(Node root, List<Integer> sol){
        //check if root is null
        if(root == null){
            return;
        }
        
        //postorder means add children first
        //iterate through the children of root
        for (int i = 0; i < root.children.size(); i++){
            Node child = root.children.get(i); 
            postHelper(child, sol); 
        }
        
        //add val of root
        sol.add(root.val); 
    }
        
}




//---------- leaf-similar trees -------------------// 


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        
        //will hold solutions
        List<Integer> sol1 = new ArrayList<Integer>();
        List<Integer> sol2 = new ArrayList<Integer>();
        
        //call helper() on root1
        helper(root1, sol1); 
        
        //call helper() on root1
        helper(root2, sol2); 
        
        //check if they match
        if(sol1.equals(sol2)){
            return true; 
        } 
        
        return false; 
        
    }
    
    public void helper(TreeNode root, List<Integer> sol){
        //check if root is null
        if(root == null){
            return;
        }
        
        //if root is a leaf node, then add it to list
        if(root.left == null && root.right == null){
            sol.add(root.val); 
        }
        
        //recurse on left child
        helper(root.left, sol); 
        
        //recurse on right child
        helper(root.right, sol);           
      
    }
    
    
}




//---------- same tree -------------------// 


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
         //will hold solutions
        List<Integer> sol1 = new ArrayList<Integer>();
        List<Integer> sol2 = new ArrayList<Integer>();
        
        //call helper() on root1
        helper(p, sol1); 
        
        //call helper() on root1
        helper(q, sol2); 
        
        // System.out.println(sol1); 
        // System.out.println(sol2); 
        
        //check if they match
        if(sol1.equals(sol2)){
            return true; 
        } 
        
        return false; 
    }
    
    
    public void helper(TreeNode root, List<Integer> sol){
        //check if root is null
        if(root == null){
            sol.add(null); 
            return;
        }
        
        //add root's val to list
        sol.add(root.val); 
        
        //recurse on left child
        helper(root.left, sol); 
        
        //recurse on right child
        helper(root.right, sol);           
      
    }
}






//---------- sum of left leaves -------------------// 

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        //will hold solution
        List<Integer> sol = new ArrayList<Integer>();
         
        
        //call helper() on root
        helper(root, sol, false); 
        
        //System.out.println(sol); 
        
        //add all nums in sol
        int sum = 0; 
        for (Integer n : sol){
            sum += n; 
        }
        
        return sum; 
    }
    
    
    public void helper(TreeNode root, List<Integer> sol, boolean isLeftChild){
        //check if root is null
        if(root == null){
            return;
        }
        
        //add root's val to list if it's a leaf and isLeftChild is true 
        if (root.left == null && root.right == null && isLeftChild){
             //make it false now
             isLeftChild = false; 
             sol.add(root.val); 
        }
       
        //recurse on left child, boolean is true 
        helper(root.left, sol, true); 
        
        //recurse on right child
        helper(root.right, sol, false);           
      
    }
    
    
}

//---------- diameter of a binary tree -------------------// 


class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        
        if(root == null){
            return 0; 
        }
        
        int leftDiam = diameterOfBinaryTree(root.left); 
        int rightDiam = diameterOfBinaryTree(root.right); 
        
        return Math.max(getHeight(root.left) + getHeight(root.right), Math.max(leftDiam,rightDiam)); 
        
    }
    
    public int getHeight(TreeNode root){
		if(root == null){
            return 0;
        }
		int height =  1+ Math.max(getHeight(root.left), getHeight(root.right));
        return height; 
	}
}





//---------- longest univalue path -------------------// 


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
    int a; 
    public int longestUnivaluePath(TreeNode root) {
        a = 0;
        helper(root);
        return a;
    }
    
    
     public int helper(TreeNode root) {
        if (root == null){
            return 0;
        } 
         
        int maxl = helper(root.left);
        int maxr = helper(root.right);
         
        int mLeftNum = 0;
        int mRightNum = 0;
         
        if ((root.left != null) && (root.val == root.left.val)){
            mLeftNum = maxl + 1;
        } 
        if ((root.right != null) && (root.val == root.right.val)){
            mRightNum = maxr + 1;
        } 
        a = Math.max(a, mLeftNum + mRightNum);
        int max = Math.max(mLeftNum, mRightNum);
        return max; 
    }
    
    
}









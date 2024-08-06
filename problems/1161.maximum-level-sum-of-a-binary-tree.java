/*
 * @lc app=leetcode id=1161 lang=java
 *
 * [1161] Maximum Level Sum of a Binary Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> bfs = new LinkedList<>();
        int level = 1;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int maxlevel = 1;
        bfs.add(root);
        while(!bfs.isEmpty()) {
            int qlen = bfs.size();
            sum = 0;
            for(int i=0; i<qlen; i++) {
                root = bfs.remove();
                if(root.left != null) bfs.add(root.left);
                if(root.right != null) bfs.add(root.right);
                sum += root.val;
            }
            if(sum > max) {
                max = sum;
                maxlevel = level;
            }
            level++;
        }
        return maxlevel;
    }
}
// @lc code=end


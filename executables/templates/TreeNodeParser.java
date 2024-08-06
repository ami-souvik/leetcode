package executables.templates;

import java.util.*;

import executables.exceptions.*;
import executables.utils.*;

public class TreeNodeParser {
    TreeNode root;
    public TreeNodeParser(TreeNode root) {
        this.root = root;
    }
    public TreeNode treeRoot() {
        return root;
    }
    public String toString() {
        TreeNode current = root;
        Queue<TreeNode> bfs = new LinkedList<>();
        bfs.add(root);
        List<String> result = new ArrayList<>();
        while(!bfs.isEmpty()) {
            current = bfs.remove();
            if(current == null) {
                result.add("null");
                continue;
            }
            else result.add(String.valueOf(current.val));
            bfs.add(current.left);
            bfs.add(current.right);
        }
        return result.toString();
    }

    public static TreeNodeParser parse(String input) throws TestCaseException {
        // input = "[1,7,0,7,-8,null,null]";

        String[] tokens = null;
        tokens = input.substring(1, input.length()-1).split(",");
        int idx = 0;
        TreeNode root = null;

        if(StringUtil.isNumeric(tokens[idx])) {
            root = new TreeNode(Integer.parseInt(tokens[idx]));
        }
        else if(StringUtil.isNull(tokens[idx])) {
            return null;
        }
        else {
            throw new TestCaseException("Test case contains invalid value/s.");
        }
        TreeNode current = root;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        idx++;
        while(!q.isEmpty() && idx < tokens.length) {
            String token = tokens[idx++];
            if(!StringUtil.isNumeric(token) && !StringUtil.isNull(token))
                throw new TestCaseException("Test case contains invalid value/s.");

            current = q.remove();
            if(StringUtil.isNumeric(token)) {
                current.left = new TreeNode(Integer.parseInt(token));
                q.add(current.left);
            }
            if(idx >= tokens.length) break;
            token = tokens[idx++];
            if(StringUtil.isNumeric(token)) {
                current.right = new TreeNode(Integer.parseInt(token));
                q.add(current.right);
            }
        }
        return new TreeNodeParser(root);
    }
}

package io.github.css12345.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * leetcode 297.二叉树的序列化与反序列化<br>
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 * @author cs
 *
 */
public class Codec {

	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(1);
		TreeNode node4 = new TreeNode(3);
		TreeNode node5 = new TreeNode(2);
		TreeNode node6 = new TreeNode(3);
		
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node3.right = node5;
		node5.right = node6;
		
		dfs(node1);
		System.out.println();
		
		Codec codec = new Codec();
		String data = codec.serialize(node1);
		System.out.println(data);
		dfs(codec.deserialize(data));
	}
	
	private static void dfs(TreeNode root) {
		System.out.print(root.val + " ");
		if (root.left != null)
			dfs(root.left);
		if (root.right != null)
			dfs(root.right);
	}

	// Encodes a tree to a single string.
    public String serialize(TreeNode root) {
    	List<Integer> nodes = new ArrayList<>();
    	
    	//serializeByBFS(root, nodes);
    	serializeByDFS(root, nodes);
    	
    	StringBuilder stringBuilder = new StringBuilder();
    	for (int i = 0; i < nodes.size() - 1; i++) {
    		stringBuilder.append(nodes.get(i));
    		stringBuilder.append(',');
    	}
    	if (nodes.size() >= 1)
    		stringBuilder.append(nodes.get(nodes.size() - 1));
		return stringBuilder.toString();
    }

    private void serializeByDFS(TreeNode root, List<Integer> nodes) {
    	if (root == null)
    		nodes.add(null);
    	else {
    		nodes.add(root.val);
	    	serializeByDFS(root.left, nodes);
	    	serializeByDFS(root.right, nodes);
    	}
	}

	private void serializeByBFS(TreeNode root, List<Integer> nodes) {
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.add(root);
    	while (!queue.isEmpty()) {
    		TreeNode currentNode = queue.poll();
    		if (currentNode == null)
    			nodes.add(null);
    		else {
    			nodes.add(currentNode.val);
    			queue.add(currentNode.left);
    			queue.add(currentNode.right);
    		}
    	}
	}

	// Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    	if (data == null || data.equals(""))
    		return null;
    	String[] values = data.split(",");
//		return deserializeByBFS(values);
    	return deserializeByDFS(new LinkedList<>(Arrays.asList(values)));
    }

	private TreeNode deserializeByDFS(Queue<String> values) {
		TreeNode currentNode = generateTreeNode(values.poll());
		if (currentNode != null) {
			currentNode.left = deserializeByDFS(values);
			currentNode.right = deserializeByDFS(values);
		}
		return currentNode;
	}

	private TreeNode deserializeByBFS(String[] values) {
		TreeNode root = generateTreeNode(values[0]);
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.add(root);
    	
    	int currentIndex = 1;
    	while (currentIndex < values.length) {
    		TreeNode currentNode = queue.poll();
    		currentNode.left = generateTreeNode(values[currentIndex++]);
    		currentNode.right = generateTreeNode(values[currentIndex++]);
    		if (currentNode.left != null)
    			queue.add(currentNode.left);
    		if (currentNode.right != null)
    			queue.add(currentNode.right);
    	}
		return root;
	}
    
    private TreeNode generateTreeNode(String value) {
    	if (value == null || value.equals("null"))
    		return null;
    	return new TreeNode(Integer.parseInt(value));
    }
}

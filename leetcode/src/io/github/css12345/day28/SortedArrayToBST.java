package io.github.css12345.day28;

/**
 * leetcode 108.将有序数组转换为二叉搜索树<br>
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 * @author cs
 *
 */
public class SortedArrayToBST {

	public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left > right || left < 0 || right >= nums.length)
            return null;
        if (left == right)
            return new TreeNode(nums[left]);
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBST(nums, left, mid - 1);
        node.right = sortedArrayToBST(nums, mid + 1, right);
        return node;
    }
}

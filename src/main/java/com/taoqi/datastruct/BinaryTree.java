package com.taoqi.datastruct;

/**
 * @description:
 * @author: ChangFeng
 * @create: 2018-04-26 15:37
 **/
public class BinaryTree<T> {

    public static void main(String[] args) {
        Integer[] arr = {2, 3, 1, 4, 5};
        BinaryTree binaryTree = new BinaryTree(arr);
        rlrTraverse(binaryTree.rootNode);
        System.out.println();
        lrrTraverse(binaryTree.rootNode);
        System.out.println();
        lrrootTraverse(binaryTree.rootNode);
        System.out.println();
        System.out.println(binaryTree.getHeight());
        System.out.println();
        System.out.println(binaryTree.maxValue());
    }

    private TreeNode<T> rootNode;

    public BinaryTree(T[] a) {
        for (int i = 0; i < a.length; i++) {
            this.add(a[i]);
        }
    }

    public int maxValue() {
        return this.maxValue(rootNode);
    }

    public int maxValue(TreeNode<T> rootNode) {
        if (rootNode == null) {
            return 0;
        } else {
            int max = (Integer) rootNode.value;
            int l = this.maxValue(rootNode.leftNode);
            int r = this.maxValue(rootNode.rightNode);
            return Math.max(max, Math.max(r, l));
        }
    }

    public int getHeight() {
        return this.getHeight(rootNode);
    }

    private int getHeight(TreeNode<T> rootNode) {
        if (rootNode == null) {
            return 0;
        }
        int left = this.getHeight(rootNode.leftNode);
        int right = this.getHeight(rootNode.rightNode);
        return left > right ? left + 1 : right + 1;
    }


    public void add(T value) {
        TreeNode<T> newNode = new TreeNode<>(value);
        if (rootNode == null) {
            rootNode = newNode;
            return;
        }
        TreeNode<T> tempNode = rootNode;
        while (tempNode != null) {
            Comparable rootVal = (Comparable) tempNode.value;
            Comparable newVal = (Comparable) newNode.value;
            Boolean left = null;
            if (rootVal.compareTo(newVal) > 0) {
                left = true;
            } else {
                left = false;
            }
            if (left) {
                if (null == tempNode.leftNode) {
                    tempNode.setLeftNode(newNode);
                    tempNode = tempNode.leftNode;
                    return;
                } else {
                    tempNode = tempNode.leftNode;
                }
            } else {
                if (null == tempNode.rightNode) {
                    tempNode.setRightNode(newNode);
                    tempNode = tempNode.rightNode;
                    return;
                } else {
                    tempNode = tempNode.rightNode;
                }
            }
        }
    }


    /**
     * 中序遍历 root left right
     *
     * @param rootNode
     */
    public static void rlrTraverse(TreeNode rootNode) {
        if (rootNode == null) {
            return;
        }
        System.out.print(rootNode.value);
        rlrTraverse(rootNode.leftNode);
        rlrTraverse(rootNode.rightNode);
    }

    /**
     * 先序遍历 left root right
     *
     * @param rootNode
     */
    public static void lrrTraverse(TreeNode rootNode) {
        if (rootNode == null) {
            return;
        }
        lrrTraverse(rootNode.leftNode);
        System.out.print(rootNode.value);
        lrrTraverse(rootNode.rightNode);
    }

    /**
     * 后序遍历 left right root
     *
     * @param rootNode
     */
    public static void lrrootTraverse(TreeNode rootNode) {
        if (rootNode == null) {
            return;
        }
        lrrootTraverse(rootNode.leftNode);
        lrrootTraverse(rootNode.rightNode);
        System.out.print(rootNode.value);
    }

    static class TreeNode<T> {
        private T value;

        private TreeNode<T> leftNode;

        private TreeNode<T> rightNode;

        public TreeNode(T value) {
            this.value = value;
        }

        public void setLeftNode(TreeNode<T> leftNode) {
            this.leftNode = leftNode;
        }

        public void setRightNode(TreeNode<T> rightNode) {
            this.rightNode = rightNode;
        }
    }

}
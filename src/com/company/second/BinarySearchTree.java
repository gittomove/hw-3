package com.company.second;

public class BinarySearchTree {
    private MyNode root;

    public boolean insert(int data) {
        root = insert(root, data);
        return true;
    }

    private MyNode insert(MyNode node, int data) {

        if (node == null) {
            return new MyNode(data);
        }

        if (node.data > data) {
            node.data = insert(node.left, data);
        }

        if (node.data < data) {
            node.right = insert(node.right, data);
        }

        else{
            return node;
        }
        return node;
    }
}

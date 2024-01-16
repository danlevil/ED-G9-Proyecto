package Estructuras;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//public class Tree<E> {
//
//    private TreeNode<E> root;
//
//    public Tree() {
//        this.root = null;
//    }
//
//    public Tree(TreeNode<E> node) {
//        this.root = node;
//    }
//
//    public boolean isEmpty() {
//        return this.root == null;
//    }
//
//    public E getRoot() {
//        return root.getContent();
//    }
//
//    private TreeNode getRootNode() {
//        return this.root;
//    }
//
//    private void setRootNode(TreeNode<E> root) {
//        this.root = root;
//    }
//
//    public void setRoot(E content) {
//        this.root.setContent(content);
//    }
//
//    public boolean isLeaf() {
//        return this.root.getChildren().isEmpty();
//    }
//
//    public void clear() {
//        this.root = null;
//    }
//
//    public List<E> Recorrer() {
//        List<E> result = new ArrayList<>();
//        Queue<TreeNode<E>> queue = new LinkedList<>();
//        queue.add(root);
//        while (!queue.isEmpty()) {
//            TreeNode<E> node = queue.poll();
//            result.add(node.getContent());
//            for (Tree<E> child : node.getChildren()) {
//                queue.add((TreeNode<E>) child.getRoot());
//            }
//        }
//        return result;
//    }
//
//}

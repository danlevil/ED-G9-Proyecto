package Estructuras;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    private int[] move;
    private int score;
    private List<TreeNode> children;

    public TreeNode(int[] move, int score) {
        this.move = move;
        this.score = score;
        this.children = new ArrayList<>();
    }

    public int[] getMove() {
        return move;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public void setMove(int[] move) {
        this.move = move;
    }

}

//    private E content;
//    private List<Tree<E>> children;
//
//    public TreeNode(E content) {
//        this.content = content;
//        this.children = new LinkedList<>();
//    }
//
//    public E getContent() {
//        return content;
//    }
//
//    public void setContent(E content) {
//        this.content = content;
//    }
//
//    public List<Tree<E>> getChildren() {
//        return children;
//    }
//
//    public void setChildren(List<Tree<E>> children) {
//        this.children = children;
//    }
//
//    @Override
//    public String toString() {
//        return "TreeNode{" + "content=" + content + "children={" + children + "} }";
//    }
//
//    public void addChild(E childContent) {
//        TreeNode<E> childNode = new TreeNode<>(childContent);
//        this.children.add(new Tree<>(childNode));
//    }
//
//    public boolean removeChild(E childContent) {
//        return this.children.removeIf(childTree -> childTree.getRoot().equals(childContent));
//    }

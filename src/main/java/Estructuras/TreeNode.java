package Estructuras;

import java.util.LinkedList;
import java.util.List;

public class TreeNode<E> {

    private E content;
    private List<Tree<E>> children;
    private int utility;

    public TreeNode(E content) {
        this.content = content;
        this.children = new LinkedList<>();
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public List<Tree<E>> getChildren() {
        return children;
    }

    public void setChildren(List<Tree<E>> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TreeNode{" + "content=" + content + "children={" + children + "} }";
    }

    public void addChild(E childContent) {
        TreeNode<E> childNode = new TreeNode<>(childContent);
        this.children.add(new Tree<>(childNode));
    }

    public boolean removeChild(E childContent) {
        return this.children.removeIf(childTree -> childTree.getRoot().equals(childContent));
    }

    public int getUtility() {
        return utility;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }

}

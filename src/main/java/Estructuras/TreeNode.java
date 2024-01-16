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
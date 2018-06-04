package com.demo;

import java.util.*;

public class Node {
    /**
     * Contains a map of open and corresponding closing braces.
     */
    protected static final Map<Character, Character> OPEN_CLOSE_BRACES = new HashMap<>();

    /**
     * Each brace should have a weight.
     * Starting from most inner brace until most exterior brace they should be weighted in order with a step of 1.
     */
    private static final Map<Character, Integer> WEIGHT = new HashMap<>();

    /**
     * The minimum weight of the braces.
     */
    private static final int MIN_WEIGHT;

    /**
     * The maximum weight of the braces.
     */
    private static final int MAX_WEIGHT;

    /*
     * Braces configuration.
     */
    static {
        OPEN_CLOSE_BRACES.put('(', ')');
        OPEN_CLOSE_BRACES.put('[', ']');
        OPEN_CLOSE_BRACES.put('{', '}');
//        OPEN_CLOSE_BRACES.put('<', '>');

        WEIGHT.put('(', 1);
        WEIGHT.put('[', 2);
        WEIGHT.put('{', 3);
//        WEIGHT.put('<', 4);

        MIN_WEIGHT = WEIGHT.values().stream().min(Integer::compareTo).orElseThrow(IllegalArgumentException::new);
        MAX_WEIGHT = WEIGHT.values().stream().max(Integer::compareTo).orElseThrow(IllegalArgumentException::new);
    }

    /**
     * It is the starting node that does not contain any value.
     */
    private boolean isRoot;

    /**
     * The opening or closing brace of current node.
     */
    private Character value;
    private Node parent;
    private List<Node> children;
//    private boolean isClosed; // YAGNI

    public Node() {
        isRoot = false;
        children = new ArrayList<>();
    }

    public Node(Character value) {
        this();
        this.value = value;
    }

    public Node(boolean isRoot) {
        this();
        this.isRoot = isRoot;
    }

    public Node(Character value, Node parent) {
        this();
        this.value = value;
        this.parent = parent;
    }

    /**
     * Checks if current node contains a value of an opening brace.
     *
     * @return [boolean]
     */
    public boolean isOpenBrace() {
        return OPEN_CLOSE_BRACES.containsKey(value);
    }

    /**
     * Checks if current node contains a value of an closing brace.
     *
     * @return [boolean]
     */
    public boolean isClosedBrace() {
        return OPEN_CLOSE_BRACES.containsValue(value);
    }

    /**
     * Checks if node can hierarchically be a subnode of current node.
     *
     * @param node the new node
     * @return [boolean]
     */
    public boolean canContain(Node node) {
        return isRoot() || (getWeight() > node.getWeight()) || (getWeight()==MAX_WEIGHT && node.getWeight()==MAX_WEIGHT);
    }

    /**
     * If current node is root node.
     *
     * @return [boolean]
     */
    public boolean isRoot() {
        return isRoot;
    }

    /**
     * Checks if current node can be closed with specified node.
     *
     * @param closingNode the node to close current node with
     * @return [boolean]
     */
    public boolean isCloseableWith(Node closingNode) {
        return OPEN_CLOSE_BRACES.get(value).equals(closingNode.getValue());
    }

    /**
     * Checks if current node is hierarchically complete. If it's the lowest weighted or has any direct hierarchical subbraces.
     *
     * @return [boolean]
     */
    public boolean isHierarchicallyComplete() {
        return (getWeight()==MIN_WEIGHT) || hasAnyHierarchicalSubBrace();
    }

    /**
     * Checks if current node has any direct hierarchical subbraces.
     *
     * @return [boolean]
     */
    private boolean hasAnyHierarchicalSubBrace() {
        return  getChildren().stream().anyMatch(childNode -> (childNode.getWeight()+1==getWeight()) || (childNode.getWeight()==MAX_WEIGHT && getWeight()==MAX_WEIGHT));
    }

    /**
     * Returns the weight of the current node from the weight Set.
     *
     * @return [int]
     */
    public int getWeight() {
        return WEIGHT.get(value);
    }

    public Character getValue() {
        return value;
    }

    public void setValue(Character value) {
        this.value = value;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return isRoot == node.isRoot &&
                Objects.equals(value, node.value) &&
                Objects.equals(parent, node.parent) &&
                Objects.equals(children, node.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isRoot, value, parent, children);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Node{");
        sb.append("isRoot=").append(isRoot);
        sb.append(", value=").append(value);
        sb.append(", parent=").append(parent);
        sb.append(", children=").append(children);
        sb.append('}');
        return sb.toString();
    }
}

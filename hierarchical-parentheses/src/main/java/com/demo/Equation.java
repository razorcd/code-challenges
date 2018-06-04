package com.demo;

public class Equation {
    private String input;

    /**
     * Constructor to set an equation as a string. E.g. "{[(2 + 5) * (2 + 1)] * 2 + 1} * 3"
     * @param input the equation.
     */
    public Equation(String input) {
        this.input = input;
    }

    /**
     * Checks if the hierarchy of the parenthesis is valid.
     * Validity rules:
     *   - contains only `(), [], {}` braces
     *   - all open braces are also closed
     *   - hierarchy of braces is in the following order: `{[()]}` or `{{{[()]}}}`
     *
     * @return [boolean] if equation parenthesis are valid.
     */
    public boolean isValid() {
        Node root = new Node(true);

        try {
            return input.chars()
                    .filter(c -> Node.OPEN_CLOSE_BRACES.containsKey((char)c) || Node.OPEN_CLOSE_BRACES.containsValue((char)c))
                    .mapToObj(v -> new Node((char) v))
                    .reduce(root, (currentNode, node) -> {
                        if (node.isOpenBrace()) {
                            if (currentNode.canContain(node)) {
                                node.setParent(currentNode);
                                currentNode.getChildren().add(node);
                                return node;
                            } else throw new BrokenEquationException(currentNode.getValue()+" can not contain subnode "+node.getValue());
                        } else { // else is close brace
                            if (currentNode.isCloseableWith(node) && currentNode.isHierarchicallyComplete())
                                return currentNode.getParent();
                            else throw new BrokenEquationException(currentNode.getValue()+" is not closeable with "+node.getValue()+" or node does not have any hierarchical sub brace.");
                        }
                    }).isRoot();
        } catch (BrokenEquationException ex) {
            return false;
        }

    }

    public class BrokenEquationException extends RuntimeException {
        public BrokenEquationException() {
            super();
        }
        public BrokenEquationException(String message) {
            super(message);
        }
    }
}


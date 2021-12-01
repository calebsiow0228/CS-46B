package Project;

import java.util.ArrayList;

public class PokeTree {

    // class to create a tree of pokemon nodes
    public class PokeNode {
        ArrayList<PokeNode> children;
        String name;

        public PokeNode(String name) {
            this.name = name;
            children = new ArrayList<PokeNode>();
        }

        public void addChild(PokeNode child) {
            children.add(child);
        }

        public ArrayList<PokeNode> getChildren() {
            return children;
        }

        public String getName() {
            return name;
        }
    }

    // root of the tree
    private PokeNode root;

    // constructor
    public PokeTree() {
        root = null;
    }

    // add children to the tree using the addChild method recursively
    public void addChild(PokeNode parent, String name) {
        if (root == null) {
            root = new PokeNode(name);
        } else {
            parent.addChild(new PokeNode(name));
        }

    }

    // use recursive find method to find the pokemon
    public PokeNode find(String name) {
        return recursiveFind(root, name);
    }

    // recursive method to find the pokemon
    private PokeNode recursiveFind(PokeNode node, String name) {
        if (node.getName().equals(name)) {
            return node;
        } else {
            for (PokeNode child : node.getChildren()) {
                PokeNode found = recursiveFind(child, name);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    // to string method
    public String toString() {
        return toStringRecursive(root);
    }

    // recursive to string method
    public String toStringRecursive(PokeNode node) {
        String result = node.getName();
        for (PokeNode child : node.getChildren()) {
            result += " " + toStringRecursive(child);
        }
        return result;
    }

    // method to use the recursive get leaves method to get the leaves of the tree
    // and set it to an arraylist
    // return an arraylist of pokenode objects
    public ArrayList<PokeNode> getLeaves() {
        ArrayList<PokeNode> leaves = new ArrayList<PokeNode>();
        getLeavesRecurse(root, leaves);
        return leaves;
    }

    // recursive method to get leaves the pokemon tree using isLeaf method
    private void getLeavesRecurse(PokeNode node, ArrayList<PokeNode> leaves) {
        if (isLeaf(node) == true) {
            leaves.add(node);
        } else {
            for (PokeNode child : node.getChildren()) {
                getLeavesRecurse(child, leaves);
            }
        }
    }

    // check if a pokeNode is a leaf
    public boolean isLeaf(PokeNode node) {
        return node.getChildren().isEmpty();
    }

    // method to get the parent of a pokemon
    public PokeNode getParent(PokeNode node) {
        return getParentRecurse(root, node);
    }

    // recursive method to get the parent of a pokemon
    private PokeNode getParentRecurse(PokeNode node, PokeNode child) {
        if (node.getChildren().contains(child)) {
            return node;
        } else {
            for (PokeNode childNode : node.getChildren()) {
                PokeNode found = getParentRecurse(childNode, child);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    // method to get the siblings of a pokemon
    public ArrayList<PokeNode> getSiblings(PokeNode node) {
        ArrayList<PokeNode> siblings = new ArrayList<PokeNode>();
        PokeNode parent = getParent(node);
        for (PokeNode child : parent.getChildren()) {
            if (child != node) {
                siblings.add(child);
            }
        }
        return siblings;
    }

    // method to get the children of a pokemon
    public ArrayList<PokeNode> getChildren(PokeNode node) {
        return node.getChildren();
    }

    // method to get ancestors of a pokemon
    public ArrayList<PokeNode> getAncestors(PokeNode node) {
        ArrayList<PokeNode> ancestors = new ArrayList<PokeNode>();
        getAncestorsRecurse(root, node, ancestors);
        return ancestors;
    }

    // recursive method to get ancestors of a pokemon
    private void getAncestorsRecurse(PokeNode node, PokeNode child, ArrayList<PokeNode> ancestors) {
        if (node.getChildren().contains(child)) {
            ancestors.add(node);
        } else {
            for (PokeNode childNode : node.getChildren()) {
                getAncestorsRecurse(childNode, child, ancestors);
            }
        }
    }

    // method to get descendants of a pokemon
    public ArrayList<PokeNode> getDescendants(PokeNode node) {
        ArrayList<PokeNode> descendants = new ArrayList<PokeNode>();
        getDescendantsRecurse(node, descendants);
        return descendants;
    }

    // recursive method to get descendants of a pokemon
    private void getDescendantsRecurse(PokeNode node, ArrayList<PokeNode> descendants) {
        descendants.add(node);
        for (PokeNode child : node.getChildren()) {
            getDescendantsRecurse(child, descendants);
        }
    }

    // use recursive method to get the height of the tree
    public int getHeight() {
        return recursiveGetHeight(root);
    }

    // recursive method to get the height
    private int recursiveGetHeight(PokeNode pokemon) {
        if (pokemon == null) {
            return 0;
        } else {
            int max = 0;
            for (PokeNode child : pokemon.getChildren()) {
                int height = recursiveGetHeight(child);
                if (height > max) {
                    max = height;
                }
            }
            return max + 1;
        }
    }

    // method to get all subtrees of a pokemon
    public ArrayList<PokeTree> getSubtrees() {
        ArrayList<PokeTree> subtrees = new ArrayList<PokeTree>();
        getSubtreesRecurse(root, subtrees);
        return subtrees;
    }

    // recursive method to get all subtrees of a pokemon
    private void getSubtreesRecurse(PokeNode node, ArrayList<PokeTree> subtrees) {
        PokeTree tree = new PokeTree();
        tree.addChild(tree.root, node.getName());
        for (PokeNode child : node.getChildren()) {
            tree.addChild(tree.root, child.getName());
            getSubtreesRecurse(child, subtrees);
        }
        subtrees.add(tree);
    }

    // to string method for the tree
    public String toStringTree() {
        return toStringTreeRecursive(root);
    }

    // recursive method to get the tree
    private String toStringTreeRecursive(PokeNode node) {
        String result = "";
        if (node == null) {
            return result;
        } else {
            result += node.getName() + " ";
            for (PokeNode child : node.getChildren()) {
                result += toStringTreeRecursive(child);
            }
            return result;
        }
    }

    // main method to test the tree
    public static void main(String[] args) {

        PokeTree tree = new PokeTree();
        // arceus is the root of all pokemon
        tree.addChild(tree.root, "Arceus");

        // arceus gave birth to dialga, palkia, girantina, and mew
        tree.addChild(tree.find("Arceus"), "Dialga");
        tree.addChild(tree.find("Arceus"), "Palkia");
        tree.addChild(tree.find("Arceus"), "Girantina");
        tree.addChild(tree.find("Arceus"), "Mew");

        // dialga gave birth to yveltal, zygarde, and xerneas, and celebi
        tree.addChild(tree.find("Dialga"), "Yveltal");
        tree.addChild(tree.find("Dialga"), "Zygarde");
        tree.addChild(tree.find("Dialga"), "Xerneas");
        tree.addChild(tree.find("Dialga"), "Celebi");

        // palkia gave birth to rayquaza, kyogre, and groudon
        tree.addChild(tree.find("Palkia"), "Rayquaza");
        tree.addChild(tree.find("Palkia"), "Kyogre");
        tree.addChild(tree.find("Palkia"), "Groundon");

        // girantina gave birth to zekrom, reshiram, and kyurem
        tree.addChild(tree.find("Girantina"), "Zekrom");
        tree.addChild(tree.find("Girantina"), "Reshiram");
        tree.addChild(tree.find("Girantina"), "Kyurem");

        // mew gave birth to pikachu
        tree.addChild(tree.find("Mew"), "Pikachu");

        // Rayquaza gave birth to Ho-Oh
        tree.addChild(tree.find("Rayquaza"), "Ho-Oh");
        // Groundon gave birth to regigigas
        tree.addChild(tree.find("Groundon"), "Regigigas");
        // Kyogre gave birth to Lugia
        tree.addChild(tree.find("Kyogre"), "Lugia");

        // reshiram gave birth to Keldeo
        tree.addChild(tree.find("Reshiram"), "Keldeo");

        // kyogre gave birth to Manaphy and Phione
        tree.addChild(tree.find("Kyogre"), "Manaphy");
        tree.addChild(tree.find("Kyogre"), "Phione");

        // get ancestors of manaphy
        ArrayList<PokeNode> ancestors = tree.getAncestors(tree.find("Manaphy"));
        System.out.println("Ancestors of Manaphy: ");
        for (PokeNode ancestor : ancestors) {
            System.out.print(ancestor.getName() + " ");
        }
        System.out.println("\n");

        // get the descendants of palkia
        ArrayList<PokeNode> descendants = tree.getDescendants(tree.find("Palkia"));
        System.out.println("Descendants of Palkia: ");
        for (PokeNode descendant : descendants) {
            System.out.print(descendant.getName() + " ");
        }
        System.out.println("\n");

        // print the siblings of zekrom
        ArrayList<PokeNode> siblings = tree.getSiblings(tree.find("Zekrom"));
        System.out.println("Siblings of Zekrom: ");
        for (PokeNode sibling : siblings) {
            System.out.print(sibling.getName() + " ");
        }
        System.out.println("\n");

        // get the parent of rayquaza
        PokeNode parent = tree.getParent(tree.find("Rayquaza"));
        System.out.println("Parent of Rayquaza: " + parent.getName());
        System.out.println("\n");

        // print height
        System.out.println("The Height of the PokeTree is: " + tree.getHeight());
        System.out.println("\n");

        // print leaves
        System.out.println("All PokeNodes that are Leaves: ");
        ArrayList<PokeNode> leaves = tree.getLeaves();
        for (PokeNode leaf : leaves) {
            System.out.print(leaf.getName() + ", ");
        }
        System.out.println("\n");

        // print tree
        System.out.println("PokeTree Legend: ");
        System.out.println(tree.toStringTree());
        System.out.println("\n");

        // print subtrees
        System.out.println("All Subtrees: ");
        ArrayList<PokeTree> subtrees = tree.getSubtrees();
        for (PokeTree subtree : subtrees) {
            System.out.print(subtree.toStringTree() + "\n");
            System.out.println("");
        }
    }

}

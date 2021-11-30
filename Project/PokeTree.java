package Project;

import java.util.ArrayList;
import java.util.*;
//import queue data structure
import java.util.Queue;
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

    //return result of sorted level order traversal, separated by spaces and new lines for each level
    public String levelOrder() {
        return levelOrderRecursive(root);
    }

    // recursive method to get the level order traversal
    //return result of sorted level order traversal, separated by spaces and new lines for each level

    private String levelOrderRecursive(PokeNode node) {
        String result = "";
        if (node == null) {
            return result;
        } else {
            Queue<PokeNode> queue = new LinkedList<PokeNode>();
            queue.add(node);
            while (!queue.isEmpty()) {
                PokeNode current = queue.remove();
                result += current.getName() + " ";
                for (PokeNode child : current.getChildren()) {
                    queue.add(child);
                }
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

        // print height
        System.out.println("The Height of the PokeTree is: " + tree.getHeight());
        System.out.println("\n");

        // is pikachu a leaf?
        System.out.println("Is Pikachu a Leaf?: " + tree.isLeaf(tree.find("Pikachu")));
        System.out.println("\n");

        // print leaves
        System.out.println("All PokeNodes that are Leaves: ");
        ArrayList<PokeNode> leaves = tree.getLeaves();
        for (PokeNode leaf : leaves) {
            System.out.print(leaf.getName() + ", ");
        }

        // separate line
        System.out.println("\n");

        // print tree in level order
        System.out.println("PokeTree Legend: ");
        System.out.println(tree.levelOrder());
    }

}

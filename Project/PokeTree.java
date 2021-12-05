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

    // method to get the parent of a pokemon non recursively
    public PokeNode getParent(PokeNode node) {
        PokeNode parent = null;
        for (PokeNode child : root.getChildren()) {
            if (child.getChildren().contains(node)) {
                parent = child;
            }
        }
        return parent;
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

    // method to get all the children of a pokemon
    public ArrayList<PokeNode> getChildren(PokeNode node) {
        return node.getChildren();
    }

    //get descendants of a pokemon not including the pokemon itself in the list
    public ArrayList<PokeNode> getDescendants(PokeNode node) {
        ArrayList<PokeNode> descendants = new ArrayList<PokeNode>();
        getDescendantsRecurse(node, descendants);
        return descendants;
    }

    // recursive method to get descendants of a pokemon
    private void getDescendantsRecurse(PokeNode node, ArrayList<PokeNode> descendants) {
        for (PokeNode child : node.getChildren()) {
            descendants.add(child);
            getDescendantsRecurse(child, descendants);
        }
    }



    //method to get max height of the tree -- number of nodes along the longest path from the root node
    public int getMaxHeight() {
        return getMaxHeightRecurse(root);
    }

    //find the height by ocunting the number of nodes in the longest path from the root node
    private int getMaxHeightRecurse(PokeNode node) {
        int count = 0;
        for (PokeNode child : node.getChildren()) {
            count = Math.max(count, getMaxHeightRecurse(child));
        }
        return count + 1;
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


        // get the descendants of palkia
        ArrayList<PokeNode> descendants = tree.getDescendants(tree.find("Palkia"));
        System.out.println("Descendants of Palkia: ");
        for (PokeNode descendant : descendants) {
            System.out.print(descendant.getName() + " ");
        }
        System.out.println("\n");

        //find the parent of pikachu
        PokeNode parent = tree.getParent(tree.find("Pikachu"));
        System.out.println("Parent of Pikachu: " + parent.getName());

        // print the siblings of zekrom
        ArrayList<PokeNode> siblings = tree.getSiblings(tree.find("Zekrom"));
        System.out.println("Siblings of Zekrom: ");
        for (PokeNode sibling : siblings) {
            System.out.print(sibling.getName() + " ");
        }
        System.out.println("\n");


        //gets all the children of kyogre
        ArrayList<PokeNode> children = tree.getChildren(tree.find("Kyogre"));
        System.out.println("Children of Kyogre: ");
        for (PokeNode child : children) {
            System.out.println(child.getName() + " ");
        }
        System.out.println("\n");

        // print height
        System.out.println("The Height of the PokeTree is: " + tree.getMaxHeight());
        System.out.println("\n");

        //print leaves
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


    }

}

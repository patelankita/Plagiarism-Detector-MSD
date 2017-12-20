package Utility; /**
 * Created by Chaitanya on 11/18/17.
 */

/**
 * This class holds node information like hash-code of the node, number of child nodes, node type
 * and starting and ending line number of the node. Visited is true or false depending on if a node
 * has been visited or not
 *
 * @return Nothing.
 */

public class NodeDataStruct {
    public int hash_code;
    public int numberOfChildNodes;
    public int nodeType;
    public int[] lineNumbers;
    public boolean notVisited;
}

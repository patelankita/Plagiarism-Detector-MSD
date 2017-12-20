/**
 * Created by Chaitanya and Ankita on 11/20/17.
 */
package CorePlagarismDetectionEngine;

import Utility.NodeDataStruct;
import org.eclipse.jdt.core.dom.*;

import java.util.HashMap;
import java.util.Map;

/**
 * CorePlagarismDetectionEngine.ASTNodeVisitor class gets all the node information like
 * the hash code, node type and number of child nodes.
 * It uses the AST parser's visitor method to visit each node
 * and gather the information.
 *
 * @return Nothing.
 */
public class ASTNodeVisitor {
    private HashMap<ASTNode, NodeDataStruct> nodeHashMap;
    private CompilationUnit compilationUnit;

    public HashMap<ASTNode, NodeDataStruct> getFinalMap() {
        return finalMap;
    }

    private HashMap<ASTNode, NodeDataStruct> finalMap;

    /**
     * CorePlagarismDetectionEngine.ASTNodeVisitor constructor creates a new nodeHashMap and finalMap
     * and assigns the compilation unit to the existing compilation unit.
     * @param compilationUnit
     * @return Nothing.
     */
    public ASTNodeVisitor(CompilationUnit compilationUnit) {
        nodeHashMap = new HashMap<ASTNode, NodeDataStruct>();
        this.compilationUnit = compilationUnit;
        finalMap = new HashMap<ASTNode, NodeDataStruct>();
    }

    /**
     * calculateNodeVals accepts a node and gets all the node information and store it
     * in a nodeHashMap which is of type node and nodeInformation.
     * After the nodeHashMap is created, it removes all the nodes with no children
     * to remove false positive values.
     * @param node This is the node returned by the AST node visitor.
     * @return Nothing.
     */
    public void calculateNodeVals(ASTNode node) {
        NodeDataStruct astDS = new NodeDataStruct();
        setNodeValues(node, astDS);
        checkAndUpdateParent(node);
        recursiveUpdate(node);
        createNodeMap();
    }

    /**
     * createNodeMap helper method iterates over each node in the node hash map and if
     * child nodes exists, it sets them in the finalHashMap.
     * @return Nothing.
     */
    private void createNodeMap() {
        for (Map.Entry<ASTNode, NodeDataStruct> entry : nodeHashMap.entrySet()) {
            if (entry.getValue().numberOfChildNodes != 0)
                finalMap.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * setNodeValues helper method sets the values of user defined AST data structure like hash code,
     * node type, number of child nodes, not visited and line number. It then sets the node and its corresponding
     * value in the nodeHashMap.
     * @param node This is the node returned by the AST node visitor.
     * @param astDS
     * @return Nothing.
     */
    private void setNodeValues(ASTNode node, NodeDataStruct astDS) {
        astDS.hash_code = node.getNodeType();
        astDS.nodeType = node.getNodeType();
        astDS.numberOfChildNodes = 0;
        astDS.notVisited = true;
        astDS.lineNumbers = new int[]{compilationUnit.getLineNumber(node.getStartPosition()),
                compilationUnit.getLineNumber(node.getStartPosition() + node.getLength())};
        nodeHashMap.put(node, astDS);
    }

    /**
     * checkAndUpdateParent accepts a node and checks if the parent exists in the hashMap.
     * If yes, the it updates the children count by 1 else does nothing.
     * @param node This is the node returned by the AST node visitor.
     * @return Nothing.
     */
    public void checkAndUpdateParent(ASTNode node) {
        if (nodeHashMap.containsKey(node.getParent())) {
            ASTNode parentNode = node.getParent();
            NodeDataStruct parentProperties = nodeHashMap.get(parentNode);
            parentProperties.numberOfChildNodes = parentProperties.numberOfChildNodes + 1;
            nodeHashMap.put(parentNode, parentProperties);
        }
    }

    /**
     * recursiveUpdate accepts a node and checks if the parent exists in the hashMap.
     * If yes, the it updates the parent's hash value as 'parent_current_hash_value + child_hash_value'
     * and puts the updated value in the nodeHashMap
     * @param node This is the node returned by the AST node visitor.
     * @return Nothing.
     */
    public void recursiveUpdate(ASTNode node) {
        if (node.getParent() != null) {
            ASTNode parent = node.getParent();
            if (nodeHashMap.containsKey(parent)) {
                NodeDataStruct parentInfo = nodeHashMap.get(parent);
                if (nodeHashMap.containsKey(node)) {
                    parentInfo.hash_code = parentInfo.hash_code + nodeHashMap.get(node).hash_code;
                }
                nodeHashMap.put(parent, parentInfo);
            }
            recursiveUpdate(parent);
        }
    }

    /**
     * traverseAllNodes uses the visitor pattern to point to the root node of the AST and then
     * visits all the different types of AST nodes and passes the node to calculateNodeVals method.
     *
     * @return Nothing.
     */
    public void traverseAllNodes() {
        compilationUnit.accept(new ASTVisitor() {
            public boolean visit(MethodInvocation node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(Block node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(MethodDeclaration node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(VariableDeclarationFragment node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(TypeDeclarationStatement node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(WhileStatement node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(ExpressionStatement node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(EmptyStatement node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(TryStatement node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(AssertStatement node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(LabeledStatement node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(SynchronizedStatement node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(SwitchCase node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(SwitchStatement node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(ReturnStatement node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(ConstructorInvocation node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(ContinueStatement node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(IfStatement node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(BreakStatement node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(DoStatement node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(EnhancedForStatement node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(ForStatement node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(SuperConstructorInvocation node) {
                calculateNodeVals(node);
                return true;
            }

            public boolean visit(ThrowStatement node) {
                calculateNodeVals(node);
                return true;
            }


        });
    }
}
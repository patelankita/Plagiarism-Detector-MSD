package CorePlagarismDetectionEngine;

import Utility.NodeDataStruct;
import Utility.Comparator;
import Utility.Util;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import javax.xml.soap.Node;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Chaitanya and Ankita on 11/20/17.
 */

/**
 * CorePlagarismDetectionEngine.PlagarismDetector class extends the Utility.Comparator class and implements Utility.IComparator's
 * compareFiles method. It checks for code similarity between the two java files using the
 * AST nodes and its properties.
 * @return Nothing.
 */
public class PlagiarismDetector extends Comparator {

    private String file1;
    private String file2;
    private int NodeCount = 0;
    private int file2TotalNodes = 0;
    private static DecimalFormat df2 = new DecimalFormat(".##");

    /**
     * CorePlagarismDetectionEngine.PlagarismDetector constructor takes the two java files.
     * @param file1 First file
     * @param file2 Second file
     * @return Nothing.
     */
    public PlagiarismDetector(String file1, String file2) {
        this.file1 = file1;
        this.file2 = file2;
    }

    /**
     * compareFiles is an overriden method of Utility.IComparator interface. It calls the getComparisionBeetweenTrees
     * method to get the similarity score between two AST trees and return the similarity ratio.
     * @return double - similarity ratio
     * @exception IOException On input error.
     */
    @Override
    public double compareFiles() throws IOException {
        getComparisionBeetweenTrees();
        return getSimilarityPercentage();
    }

    /**
     * getSimilarityPercentage computes the similarity ratio of suspected file2 against file 1.
     * @return double - similarity ratio
     */
    public double getSimilarityPercentage() throws IOException {
        try {
            double result = Double.valueOf(df2.format((double) NodeCount / (double) file2TotalNodes * 100));
            Util.writeResultToFile("results.txt",String.valueOf(result)+"%");
            return result;
        }
        catch (NumberFormatException e) {
            return 0.0;
        }
    }

    /**
     * getComparisionBeetweenTrees method creates a HashMap of the nodes and AST properties
     * of each file and converts it into the a treeMap and calls the calculateDifference method to get
     * the difference between the 2 AST's.
     * @return Nothing.
     * @exception IOException On input error.
     */
    public void getComparisionBeetweenTrees() throws IOException {
        Util util = new Util();
        Map<ASTNode, NodeDataStruct> map1 = createComparision(file1);
        Map<ASTNode, NodeDataStruct> map2 = createComparision(file2);

        Map<Integer, List<NodeDataStruct>> treeMap1 = new TreeMap<Integer, List<NodeDataStruct>>(util.getMap(map1));
        Map<Integer, List<NodeDataStruct>> treeMap2 = new TreeMap<Integer, List<NodeDataStruct>>(util.getMap(map2));

        List<Integer> list1 = util.getIntegerList(treeMap1.keySet());
        List<Integer> list2 = util.getIntegerList(treeMap2.keySet());

        file2TotalNodes = map2.size();
        calculateDifference(treeMap1, treeMap2,list1, list2);
    }

    /**
     * createComparision method creates a parser for a specific java file and after creating the CompilationUnit
     * of that specific source code, calls the traverseAllNodes method which will return a hashmap of the AST Nodes.
     * @param file
     * @return HashMap<ASTNode, Utility.NodeDataStruct>
     */
    private HashMap<ASTNode, NodeDataStruct> createComparision(String file) {
        ASTParser parser = ASTParser.newParser(AST.JLS9);
        parser.setSource(file.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        final CompilationUnit compilationUnit = (CompilationUnit)parser.createAST(null);
        ASTNodeVisitor astNodeVisitor = new ASTNodeVisitor(compilationUnit);
        astNodeVisitor.traverseAllNodes();
        return astNodeVisitor.getFinalMap();
    }

    /**
     * calculateDifference method calculates the difference between two hashmaps containing the childNode counts as key,
     * and the List of nodes having the the same childNode count. The inputs are supposed to be sorted HashMap type data structures,
     * hence we use a TreeMap to automatically sort the the keyset in an increasing order.
     * Then the method, traverses the two hashmaps and checks if the hashcodes of two nodes under comparision is same or not.
     * If it's same, then the parent is checked to check if the whole block is same, and accordingly the result is fed the output
     * file which is read by the Angular controller through the jar output.
     * @param map1
     * @param map2
     * @param listFile1
     * @param listFile2
     * @return Nothing.
     * @exception IOException On input error.
     *
     * Reference : http://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=7424821
     */

    private void calculateDifference(Map<Integer, List<NodeDataStruct>> map1, Map<Integer, List<NodeDataStruct>> map2,
                                     List<Integer> listFile1, List<Integer> listFile2) throws IOException {
        int i = 0, j=0;
        while(i<listFile1.size() && j<listFile2.size()) {
            if(listFile1.get(i)==listFile2.get(j)) {
                List<NodeDataStruct> listNodeFile1 = map1.get(listFile1.get(i));
                List<NodeDataStruct> listNodeFile2 = map2.get(listFile2.get(j));
                iterateNodeData(listNodeFile1, listNodeFile2);
                i++;
                j++;
            }
            else if(listFile1.get(i)<listFile2.get(j))
                i++;

            else if(listFile1.get(i)>listFile2.get(j))
                j++;
        }
    }

    /**
     * iterateNodeData helper method iterates over the list of nodes in file1 and file 2 and checks
     * if the hash codes of the nodes match then it invokes the sameHasCode method to output their lines
     * and set the 'notVisited' property of node to false.
     * @param listNodeFile1
     * @param listNodeFile2
     * @return Nothing.
     * @exception IOException On input error.
     *
     */
    private void iterateNodeData(List<NodeDataStruct> listNodeFile1, List<NodeDataStruct> listNodeFile2) throws IOException {
        for (NodeDataStruct nodeFile1 : listNodeFile1) {
            for (NodeDataStruct nodeFile2 : listNodeFile2) {
                if ((nodeFile1.hash_code == nodeFile2.hash_code) &&
                        (nodeFile1.nodeType == nodeFile2.nodeType && nodeFile2.notVisited))
                    sameHashCode(nodeFile1, nodeFile2);
            }
        }
    }

    /**
     * sameHashCode helper method is called if the hashcodes of nodes in both the files are same.
     * It then invoked the appendResultsToFile method and sends it the start and end line number of the node.
     * It also sets the 'notVisited' property of the node data structure to false.
     * @param nodeFile1
     * @param nodeFile2
     * @return Nothing.
     *
     */
    private void sameHashCode(NodeDataStruct nodeFile1, NodeDataStruct nodeFile2) throws IOException {
        appendResultsToFile(nodeFile1.lineNumbers[0], nodeFile1.lineNumbers[1]);
        nodeFile2.notVisited = false;
        NodeCount++;
    }

    /**
     * appendResultsToFile helper method writes the matching nodes starting and ending line number to a text file.
     * @param startLineNumber
     * @param endLineNumber
     * @return Nothing.
     *
     */
    private void appendResultsToFile(int startLineNumber, int endLineNumber ) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("File 2: Line " + startLineNumber + " to " + endLineNumber +"\n");
        Util.writeResultToFile("visualLineDifference.txt",sb.toString());
    }

}
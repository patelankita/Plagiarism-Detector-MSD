package Utility;
import org.eclipse.jdt.core.dom.ASTNode;
import java.io.*;
import java.util.*;


/**
 * Created by Chaitanya and Ankita on 11/16/17.
 */

/**
 * Utility.Util class is helper class for reading the files, creating hashMap for the AST node information,
 * converting the subnodes to integer, convert file to string and count the number of lines in a file.
 * @return Nothing.
 */
public class Util {

    /**
     * bufferedReader takes the file content as string and
     * returns buffered reader for further processing.
     * @param firstFile
     * @return BufferedReader.
     * @exception FileNotFoundException
     */
    public BufferedReader bufferedReader(String firstFile) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(firstFile));
        return br;
    }

    /**
     * writeResultToFile writes all the similarity score to a result file.
     * @param result - similarity scores
     * @return Nothing.
     * @exception IOException
     */
    public static void writeResultToFile(String resultFile, String result) throws IOException {
        FileWriter fileWriter = null;
        BufferedWriter bufferWriter = null;
        PrintWriter out = null;
        try {
            fileWriter = new FileWriter(resultFile, true);
            bufferWriter = new BufferedWriter(fileWriter);
            out = new PrintWriter(bufferWriter);
            out.println(result);
            out.close();
        } finally {
            closeWriters(out,fileWriter, bufferWriter);
        }
    }

    /**
     * closeWriters closes the file writer, print writer and buffered writer if they are
     * not null.
     * @param out
     * @param fileWriter
     * @param bufferWriter
     * @exception IOException
     * @return Nothing.
     */
    private static void closeWriters(PrintWriter out, FileWriter fileWriter, BufferedWriter bufferWriter) throws IOException{
        if (out != null)
            out.close();
        if (fileWriter != null)
            fileWriter.close();
        if (bufferWriter != null)
            bufferWriter.close();
    }

    /**
     * getMap creates a hashMap for storing the AST node and its number of child nodes
     * @param hashMap - hashMap of AST node and its properties
     * @return HashMap<Integer, List<Utility.NodeDataStruct>>
     */
    public HashMap<Integer, List<NodeDataStruct>> getMap(Map<ASTNode, NodeDataStruct> hashMap) {
        HashMap<Integer, List<NodeDataStruct>> result = new HashMap<Integer, List<NodeDataStruct>>();

        for (Map.Entry<ASTNode, NodeDataStruct> entry : hashMap.entrySet()) {
            NodeDataStruct node = entry.getValue();
            if (result.containsKey(node.numberOfChildNodes)) {
                List<NodeDataStruct> list = result.get(node.numberOfChildNodes);
                list.add(node);
            }
            else if (node.numberOfChildNodes > 0) {
                List<NodeDataStruct> list = new ArrayList<NodeDataStruct>();
                list.add(node);
                result.put(node.numberOfChildNodes, list);
            }
        }
        return result;
    }

    /**
     * getIntegerList accepts a set of Subnodes and
     * converts them to an arrayList of Integer.
     * @param keySet
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> getIntegerList(Set<Integer> keySet) {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        Iterator<Integer> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            resultList.add(iterator.next());
        }
        return resultList;
    }

    /**
     * convertFileToString accepts a filePath of .java file and and first converts into a BufferReader
     * object and then finally it coverts that to a String file
     * @param filePath
     * @return String
     * @exception IOException On input error.
     */
    public static String convertFileToString(String filePath) throws IOException {
        StringBuilder finalFile = new StringBuilder(5000);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[10];
        int initialCounter = 0;
        while ((initialCounter = bufferedReader.read(buf)) != -1)
        {
            String readData = String.valueOf(buf, 0, initialCounter);
            finalFile.append(readData);
            buf = new char[1024];
        }
        bufferedReader.close();
        return finalFile.toString();
    }

}

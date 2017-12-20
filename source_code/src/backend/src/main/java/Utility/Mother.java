package Utility;

import java.io.IOException;

/**
 * Created by Chaitanya and Ankita on 11/16/17.
 */

/**
 * Utility.Mother class is the main driver class of the program which creates a Utility.CompareFactory
 * and calls getCommentSimilarity, getLineCountAnalysis and performPlagarismDetection methods of the comparator.
 * @return Nothing
 */

public class Mother {
    public static void main(String args[]) throws IOException {

        String filePath1 = args[0];
        String filePath2 =args[1];
        Util util = new Util();

        CompareFactory cf = new CompareFactory();

        Comparator comparator =  cf.getCommentSimilarity(util.bufferedReader(filePath1),util.bufferedReader(filePath2));
        comparator.compareFiles();

        comparator = cf.getLineCountAnalysis(util.bufferedReader(filePath1), util.bufferedReader(filePath2));
        comparator.compareFiles();

        comparator = cf.performPlagiarismDetection(util.convertFileToString(filePath1),util.convertFileToString(filePath2));
        comparator.compareFiles();
    }



}

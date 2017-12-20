package StringBasedComparisionEngine;

import java.io.*;
import java.util.Arrays;

import Utility.Comparator;
import Utility.Util;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import info.debatty.java.stringsimilarity.Levenshtein;


/**
 * Created by Chaitanya and Ankita  on 11/10/17.
 */

/**
 * StringBasedComparisionEngine.CompareComments class extends the Utility.Comparator class and implements Utility.IComparator's
 * compareFiles method. The class reads two files, gets all the comments from each file
 * and using Levenshtein distance formula, calculates the similarity between the them and writes
 * the result to the output file.
 *
 * @return Nothing.
 */
public class CompareComments extends Comparator {
    private BufferedReader file1;
    private BufferedReader file2;

    /**
     * StringBasedComparisionEngine.CompareComments constructor takes the two java files.
     *
     * @param firstFile  First file
     * @param secondFile Second file
     * @return Nothing.
     * @throws FileNotFoundException .
     */
    public CompareComments(BufferedReader firstFile, BufferedReader secondFile) throws FileNotFoundException {
        this.file1 = firstFile;
        this.file2 = secondFile;
    }

    /**
     * compareFiles is an overriden method of Utility.IComparator interface. It calls the getPercentageResult
     * method to get the similarity score between two files and writes it to an output file
     * and also returns the score.
     * @return double - similarity score
     * @exception IOException On input error.
     */
    @Override
    public double compareFiles() throws IOException {
        double result = getPercentageResult();
        dumpResultToFile(result);
        return result;
    }

    /**
     * dumpResultToFile method calls the util's writeResultToFile method to write the results
     * to output file.
     * @exception IOException On input error.
     * @param result Similarity score
     * @return Nothing
     */
    public void dumpResultToFile(double result) throws IOException {
        Util.writeResultToFile("results.txt", String.valueOf(result)+"%");
    }

    /**
     * getPercentageResult method does the following:
     * 1. creates a compilation unit of the java files
     * 2. using parser's getAllContainedComments, it gets all the comments from the file.
     * 3. regex transformation is applied to removes the special character's
     * by calling the applyRegexTransformations method.
     * 4. Once the text is cleaned, the arrays are sorted
     * 5. getResults method is called to compute the similarity.
     *
     * @return double - similarity percentage
     */
    public double getPercentageResult() {
        CompilationUnit cu = JavaParser.parse(file1);
        String file1 = cu.getAllContainedComments().toString().toLowerCase();
        file1 = applyRegexTransformations(file1);

        CompilationUnit cu2 = JavaParser.parse(file2);
        String file2 = cu2.getAllContainedComments().toString().toLowerCase();
        file2 = applyRegexTransformations(file2);

        String[] arrayFile1 = file1.split(" ");
        String[] arrayFile2 = file2.split(" ");
        if (arrayFile1.length == 1 && arrayFile2.length == 1) {
            if (arrayFile1[0] == "[]" && arrayFile2[0] == "[]") {
                return 0.0;
            }
        }
        Arrays.sort(arrayFile1);
        Arrays.sort(arrayFile2);

        return getResults(arrayFile1, arrayFile2);
    }

    /**
     * getResults method calculates the similarity between two string arrays
     * using the Levenshtein distance formula and returns a percentage similarity score.
     *
     * @param array1 String array of comments of file 1
     * @param array2 String array of comments of file 2
     * @return double - similarity percentage
     */
    private double getResults(String[] array1, String[] array2) {
        Levenshtein l = new Levenshtein();
        double distance = l.distance(Arrays.toString(array1), Arrays.toString(array2));
        double ratio = distance / (Math.max(Arrays.toString(array1).length(), Arrays.toString(array2).length()));
        double result = Math.round(100 - ratio * 100);
        return result;
    }

    /**
     * applyRegexTransformations method applies regex transformation on the input string.
     *
     * @param input String
     * @return String - comment string after removing the special characters.
     */
    private String applyRegexTransformations(String input) {
        input = input.replaceAll("\\*", "");
        input = input.replaceAll(",", "");
        input = input.replaceAll("/", "");
        input = input.replaceAll("\n", "");
        return input;
    }
}
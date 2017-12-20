package Utility;

import CorePlagarismDetectionEngine.PlagiarismDetector;
import StringBasedComparisionEngine.CompareComments;
import StringBasedComparisionEngine.LineCounter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

/**
 * Created by Chaitanya and Ankita on 11/10/17.
 *
 */

/**
 * Factory for comparing AST nodes
 *
 */

public class CompareFactory {

    /**
     * factory method for counting the number of lines in both the java files
     * @input : path1, path2
     * @returns: StringBasedComparisionEngine.LineCounter of type Utility.Comparator
     * @exception FileNotFoundException .
     * */
    public LineCounter getLineCountAnalysis(BufferedReader path1, BufferedReader path2) throws FileNotFoundException {
        return new LineCounter(path1, path2);
    }

    /**
     * factory method for comparing the comment similarity in both the java files
     * @input : path1, path2
     * @returns: StringBasedComparisionEngine.CompareComments of type Utility.Comparator
     * @exception FileNotFoundException .
     * */
    public CompareComments getCommentSimilarity(BufferedReader path1, BufferedReader path2) throws FileNotFoundException {
        return new CompareComments(path1, path2);
    }

    /**
     * factory method for checking the behavior preserving transformation in both the files like
     * moving functions or methods to another location in the same file,
     * renaming variables, classes, and methods and extracting sequences of statements into methods
     * @input : path1, path2
     * @returns: CorePlagarismDetectionEngine.PlagarismDetector of type Utility.Comparator
     * @exception FileNotFoundException .
     * */
    public PlagiarismDetector performPlagiarismDetection(String file1, String file2) {
        return new PlagiarismDetector(file1, file2);
    }

}

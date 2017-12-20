package StringBasedComparisionEngine;

import Utility.Comparator;
import Utility.Constants;
import Utility.Util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Chaitanya and Ankita on 11/10/17.
 *
 */

/**
 * StringBasedComparisionEngine.LineCounter class extends the Utility.Comparator class and implements Utility.IComparator's
 * compareFiles method. The class reads two files and gets counts the number of lines in each file.
 * If the lines are same it returns 0 else return -1.
 * @return Nothing.
 */
public class LineCounter extends Comparator {

    private int counterForFile1;
    private int counterForFile2;
    private BufferedReader file1;
    private BufferedReader file2;

    /**
     * StringBasedComparisionEngine.LineCounter constructor takes the two java files and initializes
     * counterForFile1 and counterForFile2 variables.
     * @param firstFile First file
     * @param secondFile Second file
     * @return Nothing.
     * @exception FileNotFoundException .
     */
    public LineCounter(BufferedReader firstFile, BufferedReader secondFile) throws FileNotFoundException {
        counterForFile1 = 0;
        counterForFile2 = 0;
        this.file1 = firstFile;
        this.file2 = secondFile;
    }

    /**
     * compareFiles is an overriden method of Utility.IComparator interface. It calls the getPercentageResult
     * method to get the similarity score between two files and writes it to an output file
     * and also returns the score
     * @return double - 0.0 if files have similar number of lines ; -1.0 if files have different number of lines
     * @exception IOException On input error.
     */
    @Override
    public double compareFiles() throws IOException {
        double result = getResult();
        dumpResultToFile(result);
        return  result;
    }

    /**
     * dumpResultToFile method calls the util's writeResultToFile method to write the results
     * to output file.
     * @param result number of line similar or not
     * @return Nothing
     * @exception IOException On input error.
     */
    private void dumpResultToFile(double result) throws IOException {
        Util.writeResultToFile("results.txt",String.valueOf(result));
    }

    /**
     * getResult method counts the number of lines in each files and adds a threshold i.e buffer to the count.
     * If the line count of both the files are within the buffer the function will return 0.0 or else -1.0.
     * @return double - 0.0 if files have similar number of lines ; -1.0 if files have different number of lines
     * @exception IOException On input error.
     */
    private double getResult() throws IOException {
        double result = Constants.RESULTS;
        int buffer = Constants.THRESHOLD;

        if(file1.readLine()==null && file2.readLine()==null)
            return -1.0;

        while (file1.readLine()!=null)
            counterForFile1++;

        while (file2.readLine()!=null)
            counterForFile2++;

        if(counterForFile1==0 || counterForFile2==0)
            return -1.0;

        if(counterForFile1-buffer<=counterForFile2 && counterForFile1+buffer>=counterForFile2)
            result = 0.0;

        return result;
    }

}
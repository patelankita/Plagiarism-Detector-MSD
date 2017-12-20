import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;


public class LineCounter extends Comparator {

    int counterForFile1;
    int counterForFile2;
    BufferedReader file1;
    BufferedReader file2;

    // Read two files and counts the number of lines in each of them.
    public LineCounter(BufferedReader firstFilePath, BufferedReader secondFilePath) throws FileNotFoundException {
        counterForFile1 = 0;
        counterForFile2 = 0;
        this.file1 = firstFilePath;
        this.file2 = secondFilePath;
    }

    @Override
    public double compareFiles() throws IOException {

        double result = getResult();
        dumpResultToFile(result);
        return  result;
    }

    public void dumpResultToFile(double result)
    {
        Util util = new Util();
        util.writeResultToFile(String.valueOf(result));
    }


    }

}
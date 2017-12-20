import Utility.Comparator;
import Utility.CompareFactory;
import Utility.Util;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Chaitanya and Ankita on 11/10/17.
 */

/**
 * Test for Plagiarism Detector.
 */
public class TestLineCountSimilarity {
    CompareFactory cf;
    Util util;

    @Before
    public void setUp() throws Exception {
        cf = new CompareFactory();util = new Util();
    }

    /**
     * Test to count lines if both files have same number of lines
     */
    @Test
    public void testEqualLines() throws IOException {
        Comparator lineComparator = cf.getLineCountAnalysis
                (util.bufferedReader("src/test/srcCodeFiles/Node.java"),
                        util.bufferedReader("src/test/srcCodeFiles/Node2.java"));
        assertEquals(lineComparator.compareFiles(),0.0,0.0);
    }

    /**
     * Test to count lines if both files have different number of lines
     */
    @Test
    public void testUnequalLines() throws IOException {
        Comparator lineComparator = cf.getLineCountAnalysis
                (util.bufferedReader("src/test/srcCodeFiles/Node2.java"),
                        util.bufferedReader("src/test/srcCodeFiles/Node3.java"));
        assertEquals(lineComparator.compareFiles(), -1.0, 0.0);

    }

    /**
     * Test to count lines if one of the files is empty
     */
    @Test
    public void testOneEmptyFile() throws IOException {
        Comparator lineComparator = cf.getLineCountAnalysis
                (util.bufferedReader("src/test/srcCodeFiles/Node2.java"),
                        util.bufferedReader("src/test/srcCodeFiles/Node4.java"));
        assertEquals(lineComparator.compareFiles(), -1.0, 0.0);
    }

    /**
     * Test to count lines  if both files are empty
     */
    @Test
    public void testBothEmptyFile() throws IOException {
        Comparator lineComparator = cf.getLineCountAnalysis
                (util.bufferedReader("src/test/srcCodeFiles/Node4.java"),
                        util.bufferedReader("src/test/srcCodeFiles/Node5.java"));
        assertEquals(lineComparator.compareFiles(), -1.0, 0.0);
    }

    /**
     * Test to count lines  if both files are completely different
     */
    @Test
    public void testDifferentContentFile() throws IOException {
        Comparator lineComparator = cf.getLineCountAnalysis
                (util.bufferedReader("src/test/srcCodeFiles/Node2.java"),
                        util.bufferedReader("src/test/srcCodeFiles/Node6.java"));
        assertEquals(lineComparator.compareFiles(), -1.0, 0.0);
    }
}

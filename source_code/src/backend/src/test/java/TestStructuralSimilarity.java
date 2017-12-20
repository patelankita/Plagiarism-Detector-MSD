import Utility.Comparator;
import Utility.CompareFactory;
import Utility.Util;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Chaitanya and Ankita on 11/10/17.
 */

/**
 * Test for Plagiarism Detector.
 */
public class TestStructuralSimilarity {
    CompareFactory cf;
    Util util;

    @Before
    public void setUp() throws Exception {
        cf = new CompareFactory();util = new Util();
    }

    /**
     * Test when file 2 is copy of file 1
     */
    @Test
    public void testWhenFile2Copied() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/CodeSimilarity.java"),
                        util.convertFileToString("src/test/srcCodeFiles/CodeSimilarity-2.java"));
        assertEquals(codeSimilarity.compareFiles() , 100.0, 0.00);
    }

    /**
     * Test when both the files are exactly similar
     */
    @Test
    public void testWhenBothFilesSame() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/CodeSimilarity.java"),
                        util.convertFileToString("src/test/srcCodeFiles/CodeSimilarity-same.java"));
        assertEquals(codeSimilarity.compareFiles() , 100.0, 0.00);
    }

    /**
     * Test when file 2 methods are relocated
     */
    @Test
    public void testWhenFile2ContentsRelocated() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/CodeSimilarity.java"),
                        util.convertFileToString("src/test/srcCodeFiles/CodeSimilarity-relocation.java"));
        assertEquals(codeSimilarity.compareFiles() , 100.0, 0.00);
    }

    /**
     * Test when file 2 is empty
     */
    @Test
    public void testWhenFile2Empty() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/CodeSimilarity.java"),
                        util.convertFileToString("src/test/srcCodeFiles/CodeSimilarity-empty.java"));
        assertEquals(codeSimilarity.compareFiles() , 0.0, 0.00);
    }

    /**
     * Test for and while loop similarity
     */
    @Test
    public void testForWhileLoopSimilarity() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/testFiles/ForLoopTests.java"),
                        util.convertFileToString("src/test/srcCodeFiles/testFiles/WhileLoopTest.java"));
        assertEquals(codeSimilarity.compareFiles() , 73.33, 0.00);
    }

    /**
     * Test doWhile and for loop similarity
     */
    @Test
    public void testDoWhileForLoopSimilarity() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/testFiles/DoWhileLoopTest.java"),
                        util.convertFileToString("src/test/srcCodeFiles/testFiles/ForLoopTests.java"));
        assertEquals(codeSimilarity.compareFiles() , 73.33, 0.00);
    }

    /**
     * Test while and break statement similarity
     */
    @Test
    public void testWhileBreakStatementSimilarity() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/testFiles/WhileLoopTest.java"),
                        util.convertFileToString("src/test/srcCodeFiles/testFiles/BreakStatement.java"));
        assertEquals(codeSimilarity.compareFiles() , 25.00, 0.00);
    }

    /**
     * Test switch statements similarity
     */
    @Test
    public void testSwitchStatementSimilarity() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/testFiles/SwitchCaseExample2.java"),
                        util.convertFileToString("src/test/srcCodeFiles/testFiles/SwitchExample.java"));
        assertEquals(codeSimilarity.compareFiles() , 25.00, 0.00);
    }

    /**
     * Test assert statements similarity
     */
    @Test
    public void testAssertStatementSimilarity() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/testFiles/AssertionTest1.java"),
                        util.convertFileToString("src/test/srcCodeFiles/testFiles/AssertionTest2.java"));
        assertEquals(codeSimilarity.compareFiles() , 66.67, 0.00);
    }

    /**
     * Test for enhanced for loops and continue statement similarity
     */
    @Test
    public void testEnhancedForAndContinueStatementSimilarity() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/testFiles/EnhancedFor.java"),
                        util.convertFileToString("src/test/srcCodeFiles/testFiles/ContinueAndFor.java"));
        assertEquals(codeSimilarity.compareFiles() , 76.92, 0.00);
    }

    /**
     * Test similarity when file name method names and variables changed
     */
    @Test
    public void testStructSimilarityNamesChanged() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set16/Sample1/Customer.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set16/Sample2/Person.java"));
        assertEquals(codeSimilarity.compareFiles() , 100.0, 0.00);
    }

    /**
     * Test similarity with different arrays
     */
    @Test
    public void testStructSimilarityWithArrays() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set18/Sample1/SearchArray.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set18/Sample2/SearchArray.java"));
        assertEquals(codeSimilarity.compareFiles() , 82.61, 0.00);
    }

    /**
     * Test similarity with method implementation changed
     */
    @Test
    public void testStructSimilarityWithImplementationChanged() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set20/Sample1/Solution.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set20/Sample2/Solution.java"));
        assertEquals(codeSimilarity.compareFiles() , 50.0, 0.00);
    }

    /**
     * Test similarity with method implementation changed
     */
    @Test
    public void testStructSimilarityStatementCoverage() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/ThrowsStatement.java"),
                        util.convertFileToString("src/test/srcCodeFiles/Constructor.java"));
        assertEquals(codeSimilarity.compareFiles() , 9.09, 0.00);
    }

}

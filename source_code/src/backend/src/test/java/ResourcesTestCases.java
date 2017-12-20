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
public class ResourcesTestCases {
    CompareFactory cf;
    Util util;

    @Before
    public void setUp() throws Exception {
        cf = new CompareFactory();util = new Util();
    }



    /**
     * Test to check set01
     */
    @Test
    public void testCodeSimilaritySet01() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set01/Sample1/LinkedList.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set01/Sample2/SimpleLinkedList.java"));
        assertEquals(codeSimilarity.compareFiles() , 100.0, 0.00);
    }

    /**
     * Test to check set02
     */
    @Test
    public void testCodeSimilaritySet02S1() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set02/Sample1/LinkedList.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set02/Sample2/LinkedList.java"));
        assertEquals(codeSimilarity.compareFiles() , 100.0, 0.00);
    }

    /**
     * Test to check set02 file 2
     */
    @Test
    public void testCodeSimilaritySet02S2() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set02/Sample1/LinkedList.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set02/Sample2/ListIterator.java"));
        assertEquals(codeSimilarity.compareFiles() , 100.0, 0.00);
    }

    /**
     * Test to check set02 file 3
     */
    @Test
    public void testCodeSimilaritySet02S3() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set02/Sample1/LinkedList.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set02/Sample2/Node.java"));
        assertEquals(codeSimilarity.compareFiles() , 100.0, 0.00);
    }

    /**
     * Test to check set03 file 1-1
     */
    @Test
    public void testCodeSimilaritySet03S1_1() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set03/Sample1/DLL.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set03/Sample2/LinkedList.java"));
        assertEquals(codeSimilarity.compareFiles() , 57.78, 0.00);
    }

    /**
     * Test to check set03 file 1-2
     */
    @Test
    public void testCodeSimilaritySet03S1_2() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set03/Sample1/DLL.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set03/Sample2/ListIterator.java"));
        assertEquals(codeSimilarity.compareFiles() , 37.5, 0.00);
    }

    /**
     * Test to check set03 file 1-3
     */
    @Test
    public void testCodeSimilaritySet03S1_3() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set03/Sample1/DLL.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set03/Sample2/Node.java"));
        assertEquals(codeSimilarity.compareFiles() , 100.0, 0.00);
    }

    /**
     * Test to check set03 file 2-1
     */
    @Test
    public void testCodeSimilaritySet03S2_1() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set03/Sample1/DLLNode.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set03/Sample2/LinkedList.java"));
        assertEquals(codeSimilarity.compareFiles() , 20.0, 0.00);
    }

    /**
     * Test to check set03 file 2-2
     */
    @Test
    public void testCodeSimilaritySet03S2_2() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set03/Sample1/DLLNode.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set03/Sample2/ListIterator.java"));
        assertEquals(codeSimilarity.compareFiles() , 50.0, 0.00);
    }

    /**
     * Test to check set03 file 2-3
     */
    @Test
    public void testCodeSimilaritySet03S2_3() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set03/Sample1/DLLNode.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set03/Sample2/Node.java"));
        assertEquals(codeSimilarity.compareFiles() , 0.0, 0.00);
    }

    /**
     * Test to check set 4file 1-1
     */
    @Test
    public void testCodeSimilaritySet04S1_1() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set04/Sample1/DLL.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set04/Sample2/LinkedList.java"));
        assertEquals(codeSimilarity.compareFiles() , 96.08, 0.00);
    }

    /**
     * Test to check set4 file 1-2
     */
    @Test
    public void testCodeSimilaritySet04S1_2() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set04/Sample1/DLL.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set04/Sample2/Node.java"));
        assertEquals(codeSimilarity.compareFiles() , 37.5, 0.00);
    }

    /**
     * Test to check set4 file 2-1
     */
    @Test
    public void testCodeSimilaritySet04S2_1() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set04/Sample1/DLLNode.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set04/Sample2/LinkedList.java"));
        assertEquals(codeSimilarity.compareFiles() , 11.76, 0.00);
    }

    /**
     * Test to check set4 file 2-2
     */
    @Test
    public void testCodeSimilaritySet04S2_2() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set04/Sample1/DLLNode.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set04/Sample2/Node.java"));
        assertEquals(codeSimilarity.compareFiles() , 100.0, 0.00);
    }

    /**
     * Test to check set5 file 1-1
     */
    @Test
    public void testCodeSimilaritySet05S1_1() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set05/Sample1/DLL.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set05/Sample2/LinkedList.java"));
        assertEquals(codeSimilarity.compareFiles() , 30.59, 0.00);
    }

    /**
     * Test to check set5 file 1-2
     */
    @Test
    public void testCodeSimilaritySet05S1_2() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set05/Sample1/DLL.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set05/Sample2/Node.java"));
        assertEquals(codeSimilarity.compareFiles() , 33.33, 0.00);
    }

    /**
     * Test to check set5 file 2-1
     */
    @Test
    public void testCodeSimilaritySet05S2_1() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set05/Sample1/DLLNode.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set05/Sample2/LinkedList.java"));
        assertEquals(codeSimilarity.compareFiles() , 8.24, 0.00);
    }

    /**
     * Test to check set5 file 2-2
     */
    @Test
    public void testCodeSimilaritySet05S2_2() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set05/Sample1/DLLNode.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set05/Sample2/Node.java"));
        assertEquals(codeSimilarity.compareFiles() , 100.0, 0.00);
    }

    /**
     * Test to check set7 file 1-1
     */
    @Test
    public void testCodeSimilaritySet07S1_1() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set07/Sample1/src/IntegerConverter.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set07/Sample2/src/src/CallConvertor.java"));
        assertEquals(codeSimilarity.compareFiles() , 0.0, 0.00);
    }

    /**
     * Test to check set7 file 1-2
     */
    @Test
    public void testCodeSimilaritySet07S1_2() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set07/Sample1/src/IntegerConverter.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set07/Sample2/src/src/ConverterX.java"));
        assertEquals(codeSimilarity.compareFiles() , 50.0, 0.00);
    }

    /**
     * Test to check set7 file 1-3
     */
    @Test
    public void testCodeSimilaritySet07S1_3() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set07/Sample1/src/IntegerConverter.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set07/Sample2/src/src/NumberStringConstants.java"));
        assertEquals(codeSimilarity.compareFiles() , 0.0, 0.00);
    }

    /**
     * Test to check set8 file 1-1
     */
    @Test
    public void testCodeSimilaritySet08S1_1() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set08/Sample1/src/SolutionA.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set08/Sample2/src/SolutionB.java"));
        assertEquals(codeSimilarity.compareFiles() , 82.5, 0.00);
    }

    /**
     * Test to check set9 file 1-1
     */
    @Test
    public void testCodeSimilaritySet09S1_1() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set09/Sample1/src/Tokenize.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set09/Sample2/src/Words.java"));
        assertEquals(codeSimilarity.compareFiles() , 53.33, 0.00);
    }


    /**
     * Test to check set11 solution.java - solution.java
     */
    @Test
    public void testCodeSimilaritySet11S3_3() throws IOException {
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set11/Sample1/Solution.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set11/Sample2/Solution.java"));
        assertEquals(codeSimilarity.compareFiles() , 27.27, 0.00);

    }

    /**
     * Test to check set16
     */
    @Test
    public void testCodeSimilaritySet16S1_1() throws IOException{
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set16/Sample1/Customer.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set16/Sample2/DatabasePerson.java"));
        assertEquals(codeSimilarity.compareFiles() , 11.96, 0.00);
    }

    /**
     * Test to check set17
     */
    @Test
    public void testCodeSimilaritySet17S1_1() throws IOException{
        Comparator codeSimilarity = cf.performPlagiarismDetection
                (util.convertFileToString("src/test/srcCodeFiles/set17/Sample1/ValidateTree.java"),
                        util.convertFileToString("src/test/srcCodeFiles/set17/Sample2/ValidateTree.java"));
        assertEquals(codeSimilarity.compareFiles() , 100.0, 0.00);
    }

}

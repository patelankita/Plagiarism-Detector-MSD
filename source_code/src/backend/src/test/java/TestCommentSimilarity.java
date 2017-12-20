import Utility.Comparator;
import Utility.CompareFactory;
import Utility.Util;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Chaitanya and Ankita on 11/10/17.
 */

/**
 * Test for Plagiarism Detector.
 */
public class TestCommentSimilarity {
    CompareFactory cf;
    Util util;

    @Before
    public void setUp() throws Exception {
        cf = new CompareFactory();util = new Util();
    }

    /**
     * Test to get comment similarity
     */
    @Test
    public void testCommentSimilarity() throws IOException {
            Comparator commentSimilarity = cf.getCommentSimilarity
                    (util.bufferedReader("src/test/srcCodeFiles/Node.java"),
                            util.bufferedReader("src/test/srcCodeFiles/Node2.java"));
            assertEquals(commentSimilarity.compareFiles(), 99.0, 0.0);
    }

    /**
     * Test to get comment similarity if none of the files has comments
     */
    @Test
    public void testCommentSimilarityWithNoCommentsInFiles() throws IOException {
            Comparator commentSimilarity = cf.getCommentSimilarity
                    (util.bufferedReader("src/test/srcCodeFiles/Node7.java"),
                            util.bufferedReader("src/test/srcCodeFiles/Node8.java"));
            assertEquals(commentSimilarity.compareFiles(), 0.0, 0.0);
    }

    /**
     * Test to get comment similarity if one of the files is empty
     */
    @Test
    public void testCommentSimilarityWithOneEmptyFile() throws IOException {
            Comparator commentSimilarity = cf.getCommentSimilarity
                    (util.bufferedReader("src/test/srcCodeFiles/Node2.java"),
                            util.bufferedReader("src/test/srcCodeFiles/Node4.java"));
            assertEquals(commentSimilarity.compareFiles(), 0.0, 0.0);
    }

    /**
     * Test to get comment similarity if both the files are empty
     */
    @Test
    public void testCommentSimilarityBothEmptyFile() throws IOException {
            Comparator commentSimilarity = cf.getCommentSimilarity
                    (util.bufferedReader("src/test/srcCodeFiles/Node4.java"),
                            util.bufferedReader("src/test/srcCodeFiles/Node5.java"));
            assertEquals(commentSimilarity.compareFiles(), 0.0, 0.0);
    }

    /**
     * Test to get comment similarity with few similar comments
     */
    @Test
    public void testCommentSimilarityRandomFiles() throws IOException {
            Comparator commentSimilarity = cf.getCommentSimilarity
                    (util.bufferedReader("src/test/srcCodeFiles/Node.java"),
                            util.bufferedReader("src/test/srcCodeFiles/Node6.java"));
            assertEquals(commentSimilarity.compareFiles(), 9.0, 0.0);
    }

    /**
     * Test with same comments and different codes
     */
    @Test
    public void testCommentSimilaritySet19() throws IOException {
            Comparator commentSimilarity = cf.getCommentSimilarity
                    (util.bufferedReader("src/test/srcCodeFiles/set19/Sample1/Solution.java"),
                            util.bufferedReader("src/test/srcCodeFiles/set19/Sample2/Solution.java"));
            assertEquals(commentSimilarity.compareFiles(), 100.0, 0.0);
    }
}

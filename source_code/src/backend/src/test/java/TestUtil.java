import Utility.Comparator;
import Utility.CompareFactory;
import Utility.Util;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by Chaitanya and Ankita on 11/10/17.
 */

/**
 * Test for Plagiarism Detector.
 */
public class TestUtil {
    CompareFactory cf;
    Util util;

    @Before
    public void setUp() throws Exception {
        cf = new CompareFactory();util = new Util();
    }

    /**
     * Test util's convertFileToString method
     */
    @Test
    public void testConvertFileToString() throws IOException {

        String file = util.convertFileToString("src/test/srcCodeFiles/testFiles/testUtil.java");
        String actual = "public class AssertionTest1 { }";
        assertEquals(file, actual);
    }

    /**
     * Test util's testGetIntegerList method
     */
    @Test
    public void testGetIntegerList() throws IOException {
        int count[] = {32,50,22,40,10};
        ArrayList<Integer> arrInt = new ArrayList<Integer>();
        for(int i = 0; i < 5; i++) {
            arrInt.add(count[i]);
        }
        Set<Integer> setInt = new HashSet<Integer>();
        for(int i = 0; i < 5; i++) {
            setInt.add(count[i]);
        }
        ArrayList<Integer> result = util.getIntegerList(setInt);
        assertEquals(result, arrInt);
    }



}

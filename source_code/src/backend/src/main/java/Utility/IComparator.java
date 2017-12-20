package Utility; /**
 * Created by Chaitanya and Ankita on 11/10/17.
 */

import java.io.IOException;

/**
 * Utility.IComparator is an interface that has an abstract method compareFiles()
 * and each class overrides this method.
 * @return nothing
 * */
public interface IComparator {

    /**
     * compareFiles method returns the similarity score between two files.
     * This method will be implemented by all the class implementing the interface.
     * Change to method
     * @return Double
     * @exception IOException On input error.
     */
    double compareFiles() throws IOException;

}

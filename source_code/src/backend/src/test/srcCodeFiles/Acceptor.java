package core;

import java.io.File;

/*
Acceptor will validate that the provided input file matches JDetective's accepted file types
 */
public class Acceptor {

    //Filter criteria used for file input validation
    private static final String ACCEPTED_FILE_EXTENSION = ".java";

    //Verifies if provided input file is of an accepted type. Returns 'True' is yes and 'False' if not
    public boolean validate(String fileName) {
        File source = new File(fileName);
        return source.exists() && (!source.isFile() || fileName.endsWith(ACCEPTED_FILE_EXTENSION));
    }
}
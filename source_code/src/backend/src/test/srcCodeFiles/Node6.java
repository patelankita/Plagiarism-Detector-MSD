import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;

/**
 * @author Tom White
 *
 */

/**
 * Overiding the isSplitable method of TextInputFormat
 * so each mapper gets one file.
 */
public class NonSplitableTextInputFormat extends TextInputFormat{

    @Override
    protected boolean isSplitable(JobContext context, Path path) {
        return false;
    }
}

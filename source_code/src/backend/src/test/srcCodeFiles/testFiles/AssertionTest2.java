
import java.io.IOException;

public class AssertionTest2 {

   public static void main(String argv[]) throws IOException {
      System.out.print("Enter your marital status: ");
      int c = System.in.read();
      //Assertion.NDEBUG=false;
      switch ((char) c) {
         case 's':
         case 'S': System.out.println("Single"); break;
         case 'm':
         case 'M': System.out.println("Married"); break;
         case 'd':
         case 'D': System.out.println("Divorced"); break;
         default: Assertion.assert(!true, "Invalid Option"); break;
      }

   }
}
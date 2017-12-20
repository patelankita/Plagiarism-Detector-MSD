import java.util.Scanner;

public class Blueberry {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        int blueCount = 0;
        int berryCount = 0;
        int blueberryCount = 0;

            do {
                if(i % 3 == 0 && i % 5 == 0) {
                    System.out.printf("%3d %s\n",  i, "Blueberry");    
                    blueberryCount++;
                }
                else if(i % 3 == 0) {
                    System.out.printf("%3d %s\n",  i, "Blue");    
                    blueCount++;
                }
                else if(i % 5 == 0) {
                    System.out.printf("%3d %s\n",  i, "Berry");    
                    berryCount++;
                }
                else {
                System.out.printf("%3d\n", i);
                }
                i++;
                }

            while (i <= 100);
    }
}








and this is what it looks like when I run it:

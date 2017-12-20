public class Test {

	public static void main(String args[]) {
		int [] numbers = {10, 20, 30, 40, 50};

		for(int x : numbers ) {
			if( x == 30 ) {
				continue;
			}
			System.out.print( x );
			System.out.print("\n");
		}
	}

	class ForLoop {
		public static void main(String[] args) {

			char[] vowels = {'a', 'e', 'i', 'o', 'u'};

			for (int i = 0; i < vowels.length; ++ i) {
				System.out.println(vowels[i]);
			}
		}
	}
}
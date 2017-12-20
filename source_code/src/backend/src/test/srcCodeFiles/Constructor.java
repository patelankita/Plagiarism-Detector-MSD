	public void writeList() {
		PrintWriter out = null;
		try {
			System.out.println("Entered try statement");
			out = new PrintWriter(new FileWriter("OutFile.txt"));
			for (int i = 0; i < SIZE; i++) {
				out.println("Value at: " + i + " = " + list.get(i));
			}
		}
		catch and finally blocks  . . .
	}
}

	public Foo acquireFoo(int id) {
		Foo result = null;
		if (id > 50) {
			result = fooService.read(id);
		} else {
			result = new Foo(id);
		}
		assert result != null;

		return result;
	}

	void foo()
	{
		;;
		label: do
		{
		} while (false);
	}

public class MsLunch {
	private long c1 = 0;
	private long c2 = 0;
	private Object lock1 = new Object();
	private Object lock2 = new Object();

	public void inc1() {
		synchronized(lock1) {
			c1++;
		}
	}

	public void inc2() {
		synchronized(lock2) {
			c2++;
		}
	}
}

public class MyClass {
	public MyClass(int x) {}
}

public class MySubClass extends MyClass {
	public MySubClass(int a, int b) {
		int c = a + b;
		super(c);  // COMPILE ERROR
	}
}
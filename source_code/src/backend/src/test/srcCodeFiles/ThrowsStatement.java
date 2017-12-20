class Example1{  
   void method1() throws ArithmeticException{  
	throw new ArithmeticException("Calculation error"); 
   }  
   void method2() throws ArithmeticException{  
	method1();  
   }  
   void method3(){  
	try{  
	   method2();  
	}
	catch(ArithmeticException e){
	   System.out.println("ArithmeticException handled");
	}  
   }  
   public static void main(String args[]){  
	Example1 obj=new Example1();  
	obj.method3();  
	System.out.println("End Of Program");  
   }

	class B extends A{

		public void printIt(){

			super.printIt();

			System.out.println(“method in class B”);

		}

	}


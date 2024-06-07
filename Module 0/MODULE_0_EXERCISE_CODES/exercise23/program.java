public class program
{
	public float test(float a, float b)
	{
		/*
		Divide by Zero- Given the two numbers a and b, find the division a/b.
	        The program should handle divide by zero exception.
		*/

		try{
			float c = a/b;
			return c;
		}catch(ArithmeticException e){
			System.out.println("Not possible");
	}
		return 0;
	}
	
}

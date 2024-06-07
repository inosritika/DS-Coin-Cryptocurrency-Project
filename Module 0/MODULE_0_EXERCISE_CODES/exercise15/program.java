
import java.lang.*;
public class program
{
	public String test(String hex)
	{

		String binary="";
		int num  = Integer.parseInt(hex);
		binary = Integer.toBinaryString(num);
		return binary;
	}
}

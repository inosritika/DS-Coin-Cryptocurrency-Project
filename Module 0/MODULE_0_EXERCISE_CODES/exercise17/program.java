public class program
{
	public int test(String number) {
		/*
		Exercise 17: Most frequent digit- Given a number, the objective is to find out
		the most frequently occuring digit in the number. If more than 2 digits have
		the same frequency, return the smallest digit. The number is input as a string
		and the output should be the digit as an integer. For e.g. if the number is
		12345121, the most frequently occuring digit is 1. If the number is 9988776655
		the output should be 5 as it is the smallest of the digits with the highest frequency.
		*/
		long a;
		a = Integer.parseInt(number);
		int[] b = new int[10];
		while (a != 0) {
			int c = (int) a % 10;
			b[c]++;
			a = a / 10;
		}
		int max = Integer.MIN_VALUE;
		int ind = 0;
		for (int i = 0; i < 10; i++) {
			if (b[i] > max) {
				max = b[i];
				ind = i;
			} else if (max == b[i]) {
				ind = Integer.min(ind, i);
			}
		}
        return ind;
	}
}

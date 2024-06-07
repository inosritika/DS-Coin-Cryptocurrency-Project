public class program
{
	public int[][] test(int M1[][], int M2[][])
	{
		/*
		Exercise 18: Matrix addition- Given two matrices M1 and M2, the objective to
		add them. Each matrix is provided as an int[][], a 2 dimensional integer array.
		The expected output is also 2 dimensional integer array.
		*/
		int num1= M1.length;
		int num2=M1[0].length;
		int addition[][]= new int[num1][num2];
		for(int i=0;i<num1;i++){
			for(int j=0;j<num2;j++) {
				addition[i][j] = M1[i][j] + M2[i][j];
			}
		}
		return addition;
	}
}

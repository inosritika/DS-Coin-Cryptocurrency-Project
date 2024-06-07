import java.util.Vector;

public class program
{
	public String[] test(String fileNames[])
	{


		Vector<String> v = new Vector<String>();
		int len = fileNames.length;
		for(int i=0;i<len ;i++){
			if(fileNames[i].length()<5){
				continue;
			}
			if(fileNames[i].substring(fileNames[i].length()-5,fileNames[i].length()).equals(".java")){
				v.add(fileNames[i]);
			}
		}
		int lenv = v.size();
		String javaFiles[]= new String[lenv];
		for(int i=0;i<lenv;i++)
		{
			javaFiles[i]=v.get(i);
		}
		return javaFiles;
	}
}

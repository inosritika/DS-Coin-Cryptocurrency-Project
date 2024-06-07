public class intro implements Runnable {
	
	public void run(){
	   System.out.println("Thread says Hello World!");
	}
	
	public static void main(String args[]){
		intro1 thread = new intro1();
		Thread tobj= new Thread(thread);
		tobj.start();
	}
		
}

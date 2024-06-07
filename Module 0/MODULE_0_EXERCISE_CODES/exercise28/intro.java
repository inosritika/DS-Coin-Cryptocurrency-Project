public class intro extends Thread {
  public void run(){
	for(int i=1;i<10;i++){
 	   System.out.println(Thread.currentThread().getName()+
	 	"says hello "+i+" times");
	   try{Thread.sleep(1000);}catch(InterruptedException e){}
	}
  }
  public static void main(String args[]) {
	intro1 thr1 = new intro1();
	intro1 thr2 = new intro1();
	thr1.start();
	thr2.start();
  }
}

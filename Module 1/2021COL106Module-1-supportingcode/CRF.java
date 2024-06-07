import HelperClasses.Pair;
import HelperClasses.sha256;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class CRF extends sha256 {

    // Stores the output size of the function Fn()
    public int outputsize;

    CRF(int size) {
        outputsize = size;
        assert outputsize <= 64;
    }

    // Outputs the mapped outputSize characters long string s' for an input string s
    public String Fn(String s) {
        String shasum = encrypt(s);
        return shasum.substring(0, outputsize);
    }

    /*==========================
    |- To be done by students -|
    ==========================*/

    //    public Pair<String, String> FindCollDeterministic() {
//                String m = "45327";
//                Pair<String,String>ans=null;
//                CRF CRF_obj = new CRF(5);
//                String[] check = new String[(int) Math.pow(16.0,5.0)+2];
//                check[0]=CRF_obj.Fn(m);
//                for(int i=1;i<=(int) Math.pow(16.0,5.0)+1;i++){
//                    check[i]= CRF_obj.Fn(check[i-1]);
//                    for(int j = 1 ; j < i ; j++){
//                        if(Objects.equals(check[i], check[j])){
//                          ans = new  Pair<String,String>(check[i-1],check[j-1])  ;
//                         return ans;
//
//                        }
//
//                    }
//                }
//
//
//        return null;
//    }
//
//
//
//
//             String getAlphaNumericString(int n)
//            {
//
//                // lower limit for LowerCase Letters
//                int lowerLimit = 97;
//
//                // lower limit for LowerCase Letters
//                int upperLimit = 122;
//
//                Random random = new Random();
//
//                // Create a StringBuffer to store the result
//                StringBuffer r = new StringBuffer(n);
//
//                for (int i = 0; i < n; i++) {
//
//                    // take a random value between 97 and 122
//                    int nextRandomChar = lowerLimit
//                            + (int)(random.nextFloat()
//                            * (upperLimit - lowerLimit + 1));
//
//                    // append a character at the end of bs
//                    r.append((char)nextRandomChar);
//                }
//
//                // return the resultant string
//                return r.toString();
//            }
//    public void FindCollRandomized() {
//                String attempt_filename = "FindCollRandomizedAttempts.txt";
//                Random a = new Random();
//                int outputsize = 5;
//                String find[] = new String[(int) (1000*Math.sqrt(outputsize))];
//                for(int i=0;i<1000*(int)Math.sqrt(outputsize);i++){
//                        find[i]= getAlphaNumericString(a.nextInt());
//                        System.out.println(find[i]);
//                }
//                  String outcome_filename = "FindCollRandomizedOutcome.txt";
//                  String A= null,B=null;
//                  int flag=0;
//                for (int i=0;i<1000*(int)Math.sqrt(outputsize);i++){
//                      for (int j=0;j<1000*(int)Math.sqrt(outputsize);j++){
//                          if(i==j){
//                              continue;
//                          }
//                          CRF crf_obj = new CRF(64);
//                          A = crf_obj.Fn(find[i]);
//                          B = crf_obj.Fn(find[j]);
//                          if(A.equals(B)){
//                              flag=1;
//                              break;
//                          }
//                      }
//                      if(flag==1){
//                          System.out.println("FOUND");
//                          System.out.println(A);
//                          System.out.println(B);
//                      }
//                      else{
//                          System.out.println("NOT FOUND");
//                      }
//                  }
//
//
//
//
//
//    }
//}
    public Pair<String, String> FindCollDeterministic() {
        String y0 = "";
        for (int i = 0; i <= outputsize; i++) {
            y0 += "0";
        }
        for (int i = 0; i < Math.pow(2, outputsize) + 1; i++) {
            String y1 = Fn(y0);
            Pair<String, String> ans = new Pair(y0, y1);
            if (y1 == y0) {
                return ans;
            } else {
                y0 = y1;
            }
        }
        return null;
    }

    public void FindCollRandomized() throws FileNotFoundException {
        String attempt_filename = "FindCollRandomizedAttempts.txt";
        String outcome_filename = "FindCollRandomizedOutcome.txt";
        File f1 = new File(attempt_filename);
        File f2 = new File(outcome_filename);

        FileOutputStream fs1 = new FileOutputStream(attempt_filename, true);
        PrintStream p1 = new PrintStream(fs1);
        FileOutputStream fs2 = new FileOutputStream(outcome_filename, true);
        PrintStream p2 = new PrintStream(fs2);
        for (int j = 0; j < 1048577; j++) {
            String str = "";
            for (int i = 0; i < 10; i++) {
                float a = (float) (10 * Math.random());
                str += Math.floor(a);
            }
            String str2 = Fn(str);
            p1.print(str);
            if (str == str2) {
                p2.print("Found\n");
                p2.print(str + "\n");
                p2.print(str2 + "\n");
            } else {
                p2.print("Not Found");
            }
        }
    }
}
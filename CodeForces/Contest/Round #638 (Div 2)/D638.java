   //make sure to make new file!
import java.io.*;
import java.util.*;

public class D638{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      long[] pow2 = new long[35];
      pow2[0] = 1L;
      for(int k = 1; k < 35; k++){
         pow2[k] = 2L*pow2[k-1];
      }
      
      
      for(int q = 1; q <= t; q++){

         long n = Long.parseLong(f.readLine());
         
         if(n==2){
            out.println("1");
            out.println("0");
            continue;
         }
         
         
         int log = -1;
         for(int k = 0; k < 35; k++){
            if(pow2[k] > n){
               log = k;
               break;
            }
         }
         
         ArrayList<Long>  alist = new ArrayList<Long>();
         
         long sum = 0L;
         for(int k = 0; k < log-2; k++){
            alist.add(pow2[k]);
            sum += pow2[k];
         }
         
         long remaining = n-sum;
         if(remaining >= pow2[log-2]*2){
            alist.add(pow2[log-2]);
            alist.add(remaining-pow2[log-2]);
         } else {
            if(remaining - pow2[log-2] >= pow2[log-3]){
               alist.add(remaining-pow2[log-2]);
               alist.add(pow2[log-2]);
            } else {
               alist.add(pow2[log-3]);
               alist.add(remaining-pow2[log-3]);
            }
         }
         
         out.println(alist.size()-1);
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 1; k < alist.size(); k++){
            sj.add("" + (alist.get(k)-alist.get(k-1)));
         }
         out.println(sj.toString());
      

      }
      
      
      
      
      out.close();
   }
   
      
}
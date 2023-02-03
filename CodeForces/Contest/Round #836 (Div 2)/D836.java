//make sure to make new file!
import java.io.*;
import java.util.*;

public class D836{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         long n = Long.parseLong(f.readLine());

         //target of sum is (n*2)^2, target of range is n*2
         long target = n*2*n*2;
         
         ArrayList<Long> answer = new ArrayList<Long>();
         
         long sumt = n*4;
         if(n%2 == 1) answer.add(sumt);
         answer.add(sumt-n);
         answer.add(sumt+n);
         
         for(int k = 1; k <= n-(2+(n%2)); k++){
            answer.add(sumt-k);
            answer.add(sumt+k);
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            sj.add("" + answer.get(k));
         }
         
         out.println(sj.toString());
         

      }
      
      
      
      
      out.close();
   }
   
      
}
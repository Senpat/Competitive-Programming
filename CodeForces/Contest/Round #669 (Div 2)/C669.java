//make sure to make new file!
import java.io.*;
import java.util.*;

public class C669{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[] answer = new int[n+1];
      
      
      int last = 1;
      for(int k = 2; k <= n; k++){
         out.println("? " + last + " " + k);
         out.flush();
      
         int q1 = Integer.parseInt(f.readLine());
         out.println("? " + k + " " + last);
         out.flush();
         
         int q2 = Integer.parseInt(f.readLine());
         
         if(q1 > q2){
            answer[last] = q1;
            last = k;
         } else {
            answer[k] = q2;
         }
      }
      
      answer[last] = n;
      
      StringJoiner sj = new StringJoiner(" ");
      sj.add("!");
      for(int k = 1; k <= n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      out.close();
   }
   
}
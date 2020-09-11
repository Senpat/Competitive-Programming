//make sure to make new file!
import java.io.*;
import java.util.*;

public class A666{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long[] array = new long[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      if(n==1){
         out.println("1 1");
         out.println("0");
         out.println("1 1");
         out.println("0");
         out.println("1 1");
         out.println(-1L*array[0]);
      } else {
      
         out.println("1 " + n);
         StringJoiner q1 = new StringJoiner(" ");
         long nl = (long)n;
         for(int k = 0; k < n; k++){
            if(k == n-1) q1.add("0");
            else q1.add("" + (-1L*array[k]*nl));
         }
         out.println(q1.toString());
      
         out.println("1 " + (n-1));
         StringJoiner q2 = new StringJoiner(" ");
         for(int k = 0; k < n-1; k++){
            q2.add("" + (array[k]*(nl-1)));
         }
         out.println(q2.toString());
      
         out.println(n + " " + n);
         out.println(-1L*array[n-1]);
            
      
      
      
      }
      
      
      
      
      
      out.close();
   }
   
      
}
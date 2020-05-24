//make sure to make new file!
import java.io.*;
import java.util.*;

public class A86{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
         
         long c = Long.parseLong(st.nextToken());
         long d = Long.parseLong(st.nextToken());
         
         long answer = Math.min((a+b)*c, d*Math.min(a,b) + c*(Math.max(a,b)-Math.min(a,b)));
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}
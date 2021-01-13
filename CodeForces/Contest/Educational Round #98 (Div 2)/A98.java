//make sure to make new file!
import java.io.*;
import java.util.*;

public class A98{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int min = Math.min(n,m);
         int max = Math.max(n,m);
         
         int answer = min*2 + Math.max(0,(max-min)*2-1);
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}
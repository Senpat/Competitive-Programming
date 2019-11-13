//make sure to make new file!
import java.io.*;
import java.util.*;

public class A67{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t1 = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t1; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int s = Integer.parseInt(st.nextToken());
         int t = Integer.parseInt(st.nextToken()); 
      
         int answer = n-Math.min(s,t)+1;
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
      
}
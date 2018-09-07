//Equality
import java.io.*;
import java.util.*;

public class A508{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] freq = new int[m];
      
      int[] array = new int[n];
      
      line = f.readLine();
      
      for(int k = 0; k < n; k++){
         freq[line.charAt(k)-65]++;
      }
      
      int min = Integer.MAX_VALUE;
      
      for(int k = 0; k < n; k++){
         min = Math.min(freq[k],min);
      }
      
      out.println(min*m);
      
      
      out.close();
   }
   
}
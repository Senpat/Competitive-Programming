//make sure to make new file!
import java.io.*;
import java.util.*;

public class C163{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n+1];
      int[] freq = new int[n+1];
      
      for(int k = 2; k <= n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         freq[array[k]] ++;
      }
      
      
      for(int k = 1; k <= n; k++){
         out.println(freq[k]);
      }
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
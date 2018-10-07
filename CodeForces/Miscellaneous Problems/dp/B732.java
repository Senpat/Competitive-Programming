//Cormen — The Best Friend Of a Man
import java.io.*;
import java.util.*;

public class B732{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] next = new int[n];
      next[0] = array[0];
      
      int count = 0;
      
      for(int k = 1; k < n; k++){
         next[k] = Math.max(array[k],m-next[k-1]);
         count+=Math.max(0,next[k]-array[k]);
      }
      
      out.println(count);
      for(int k = 0; k < n; k++){
         out.print(next[k] + " ");
      }
      
      
      
      
      out.close();
   }
   
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class K{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      boolean[] win = new boolean[m+1];
      win[0] = false;
      for(int k = 0; k <= m; k++){
         if(win[k]) continue;
         for(int j = 0; j < n; j++){
            if(k+array[j] <= m){
               win[k+array[j]] = true;
            }
         }
      }
      
      if(win[m]){
         out.println("First");
      } else {
         out.println("Second");
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
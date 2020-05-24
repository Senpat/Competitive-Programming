//make sure to make new file!
import java.io.*;
import java.util.*;

public class BG7{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] answer = new int[n];
      answer[0] = array[0];
      
      int max = answer[0];
      
      for(int k = 1; k < n; k++){
         answer[k] = array[k]+max;
         max = Math.max(max,answer[k]);
      }
      
      for(int k = 0; k < n; k++){
         out.print(answer[k] + " ");
      }
      
      

      
      
      
      
      
      out.close();
   }
   
      
}
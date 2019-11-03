//make sure to make new file!
import java.io.*;
import java.util.*;

public class B135{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n+1];
      
      int count = 0;
      for(int k = 1; k <= n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         if(array[k] != k){
            count++;
         }
      }
      
      if(count <= 2){
         out.println("YES");
      } else {
         out.println("NO");
      }

      
      
      
      
      out.close();
   }
   
      
}
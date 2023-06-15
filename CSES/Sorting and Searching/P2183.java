//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2183{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      Long[] array = new Long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      Arrays.sort(array);
      
      long cur = 0;
      
      for(int k = 0; k < n; k++){
         if(array[k] > cur+1){
            break;
         }
         cur += array[k];
      }
      
      out.println(cur+1);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
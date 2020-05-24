//make sure to make new file!
import java.io.*;
import java.util.*;

public class D162{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      char[] array = f.readLine().toCharArray();
      
      long r = 0L;
      long g = 0L;
      long b = 0L;
      
      for(int k = 0; k < n; k++){
         if(array[k] == 'R') r++;
         if(array[k] == 'G') g++;
         if(array[k] == 'B') b++;
      }
      
      long answer = r*g*b;
      
      for(int k = 1; k <= n/2; k++){
         for(int i = 0; i+2*k < n; i++){
            if(array[i] != array[i+k] && array[i] != array[i+2*k] && array[i+k] != array[i+2*k]) answer--;
         }
      }
      
      out.println(answer);
      
      

      
      
      
      
      
      out.close();
   }
   
      
}
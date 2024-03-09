//make sure to make new file!
import java.io.*;
import java.util.*;
//thank you deepl.org
public class A29{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(f.readLine());
      }
      
      int pn = (1 << n);
      
      int answer = Integer.MAX_VALUE;
      for(int mask = 0; mask < pn; mask++){
         int a1 = 0;
         int a2 = 0;
         for(int k = 0; k < n; k++){
            if(((mask >> k)&1) == 0){
               a1 += array[k];
            } else {
               a2 += array[k];
            }
         }
         
         answer = Math.min(answer,Math.max(a1,a2));
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
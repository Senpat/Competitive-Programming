//make sure to make new file!
import java.io.*;
import java.util.*;

public class Ab{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(f.readLine());
      }  
      
      
      int lastmax = -1;
      int added = 0;
      int i = 0;
      while(added<n){
         i++;
         lastmax = -1;
         for(int k = 0; k < n; k++){
            if(array[k] == 0) continue;
            if(array[k] > lastmax){
               added++;
               lastmax = array[k];
               array[k] = 0;
            }
         }
      }
      
      out.println(i);
            
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class A{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(f.readLine());
      }  
      
      
      int[] lis = new int[n+1];
      lis[0] = array[0];
      
      int max = 0;
      for(int k = 1; k < n; k++){
         int i = 0;
         while(array[k] < lis[i]){
            i++;
         }
         
         lis[i] = array[k];
         max = Math.max(max,i);
      }
      
      out.println(max+1);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
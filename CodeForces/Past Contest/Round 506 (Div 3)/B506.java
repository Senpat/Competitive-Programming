//Creating the Contest
//tutorial
import java.io.*;
import java.util.*;

public class B506{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      for(int k = 0; k < n; k++) array[k] = Integer.parseInt(st.nextToken());
      
      
      int max = 1;
      for(int k = 0; k < n; k++){
         int index = k;
         while(index<n-1 && array[index+1] <= array[index]*2){
            index++;
         }
         max = Math.max(max,index-k+1);
         k = index;
         
      }
      
      out.println(max);
      
      out.close();
   }
   
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class A69{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
         int n = Integer.parseInt(f.readLine());
         
         
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         int max1 = 0;
         int max2 = 0;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            if(array[k] > max1){
               max2 = max1;
               max1 = array[k];
               
            } else if(array[k] > max2){
               max2 = array[k];
            }
         }
         
         int answer = Math.max(0,Math.min(max2-1, n-2));
         out.println(answer);
      }
   
      
      
      
      
      out.close();
   }
   
      
}
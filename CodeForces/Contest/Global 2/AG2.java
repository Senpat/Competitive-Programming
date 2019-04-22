//make sure to make new file!
import java.io.*;
import java.util.*;

public class AG2{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      
      for(int k = 0; k< n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      if(array[0] != array[n-1]){
         out.println(n-1);
      } else {
         int right = n-1;
         int index = n-1;
         while(array[index] == array[n-1]){
            index--;
         }
         right = index;
         
         index = 0;
         while(array[index] == array[0]){
            index++;
         }
         
         int left = n-1-index;
         
         out.println(Math.max(right,left));
      }
      
      

      
      
      
      
      out.close();
   }
   
      
}
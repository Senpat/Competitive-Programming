//make sure to make new file!
import java.io.*;
import java.util.*;

public class BGB{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         int prev = array[n-1]+1;
         int answer = 1;
         for(int k = n-2; k >= 0; k--){
            if(array[k] == prev) continue;
            else if(array[k] == prev-1){
               prev = array[k];
               answer++;
            } else {
               prev = array[k]+1;
               answer++;
            }
         }
         
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}
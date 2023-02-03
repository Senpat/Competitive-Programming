//make sure to make new file!
import java.io.*;
import java.util.*;

public class B842{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         int target = 1;
         for(int k = 0; k < n; k++){
            if(array[k] == target){
               target++;
            }
         }
         
         target--;
         
         int answer = (n-target+m-1)/m;
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}
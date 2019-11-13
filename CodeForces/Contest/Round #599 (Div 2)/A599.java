//make sure to make new file!
import java.io.*;
import java.util.*;

public class A599{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <=t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
      
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n];
         
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         Arrays.sort(array);
         
         int answer = 1;
         for(int k = 1; k <= n; k++){
            if(array[n-k] < k) break;
            answer = k;
         }
         
         out.println(answer);
      
      }
      
   
      
      
      
      
      out.close();
   }
   
      
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class A549{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      //find last of 0 and 1
      int one = -1;
      for(int k = n-1; k >= 0; k--){
         if(array[k] == 1){
            one = k;
            break;
         }
      }
      
      int zero = -1;
      for(int k = n-1; k >= 0; k--){
         if(array[k] == 0){
            zero = k;
            break;
         }
      }
      
      int answer = Math.min(zero,one) + 1;
      out.println(answer);
      
      
      
      
      out.close();
   }
   
      
}
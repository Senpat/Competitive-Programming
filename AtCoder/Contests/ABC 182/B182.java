//make sure to make new file!
import java.io.*;
import java.util.*;

public class B182{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int maxnum = -1;
      int max = -1;
      
      for(int k = 2; k <= 1000; k++){
         int count = 0;
         for(int j = 0; j < n; j++){
            if(array[j] % k == 0){
               count++;
            }
         }
         
         if(count > max){
            max = count;
            maxnum = k;
         }
      }
      
      out.println(maxnum);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}
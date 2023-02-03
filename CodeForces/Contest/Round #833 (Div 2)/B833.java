//make sure to make new file!
import java.io.*;
import java.util.*;

public class B833{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      int M = 100;
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         String s = f.readLine();
         
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Character.getNumericValue(s.charAt(k));
         }
         
         int answer = 0;
         for(int k = 0; k < n; k++){
            int num = 0;
            int max = 0;
            
            int[] freq = new int[10];
            
            for(int j = 0; j < M && k+j < n; j++){
               if(freq[array[k+j]] == 0) num++;
               
               freq[array[k+j]]++;
               max = Math.max(max,freq[array[k+j]]);
               
               if(max <= num) answer++;
            }
         }
         
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
      
}
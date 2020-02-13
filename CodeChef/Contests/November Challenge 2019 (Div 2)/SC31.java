//make sure to make new file!
import java.io.*;
import java.util.*;

class SC31{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         int[] array = new int[10];
         for(int k = 0; k < n; k++){
            String s = f.readLine();
            for(int j = 0; j < 10; j++){
               if(s.charAt(j) == '1'){
                  array[j]++;
               }
            }
         }
         
         int answer = 0;
         for(int k = 0; k < 10; k++){
            if(array[k] % 2 == 1) answer++;
         }
         
         out.println(answer);
      
      }
      
      
      
      
      out.close();
   }
   
      
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class A779{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         char[] array = f.readLine().toCharArray();
         
         if(n == 1){
            out.println(0);
            continue;
         }
         
         int last0 = -1;
         int answer = 0;
         for(int k = 0; k < n; k++){
            if(array[k] == '0'){
               if(last0 != -1){
                  answer += Math.max(0,2-(k-last0-1));
               }
               last0 = k;
            }
         }
         
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}
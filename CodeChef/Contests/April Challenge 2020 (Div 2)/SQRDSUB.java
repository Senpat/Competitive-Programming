//make sure to make new file!
import java.io.*;
import java.util.*;

public class SQRDSUB{
   
   public static void main(String[] args)throws java.lang.Exception{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Math.abs(Integer.parseInt(st.nextToken()));
         }
         
         
         long one2 = 0L;
         for(int k = 0; k < n; k++){
            if(array[k] % 4 != 0 && array[k] % 2 == 0){
               
               long l = 1;
               long r = 1;
               
               int i = k-1;
               while(i >= 0 &&  array[i] % 2 == 1){
                  i--;
                  l++;
               }
               
               i = k+1;
               while(i < n && array[i]%2 == 1){
                  i++;
                  r++;
               }
               
               one2 += l*r;
            }
         }
               
                
         
         long answer = ((long)n+1)*(long)n/2 - (one2);
         out.println(answer);
         

      }
      
      
      
      
      out.close();
   }
   
      
}
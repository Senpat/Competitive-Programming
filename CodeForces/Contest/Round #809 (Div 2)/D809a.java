//make sure to make new file!
import java.io.*;
import java.util.*;

public class D809a{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[] array = new int[n];
         st = new StringTokenizer(f.readLine());
         int min = Integer.MAX_VALUE;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            min = Math.min(min,array[k]);
         }
         
         //min
         
         int answer = Integer.MAX_VALUE;
         for(int k = 0; k <= min; k++){
            int max = 0;
            for(int j = 0; j < n; j++){
               int p;
               if(k != 0){
                  p = Math.min(m,array[j]/k);
               } else {
                  p = Math.min(m,array[j]+1);
               }
               max = Math.max(max,array[j]/p);
            }
            answer = Math.min(answer,max-k);
         }
         
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}
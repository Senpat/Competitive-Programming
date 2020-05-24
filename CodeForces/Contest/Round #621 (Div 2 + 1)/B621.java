//make sure to make new file!
import java.io.*;
import java.util.*;

public class B621{
   
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
         
         int max = 0;
         boolean foundexact = false;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            max = Math.max(array[k],max);
            if(array[k] == m){
               foundexact = true;
            }
         }
         
         if(foundexact){
            out.println(1);
         } else if(max > m/2){
            out.println(2);
         } else {
            if(m%max == 0){
               out.println(m/max);
            } else {
               out.println(m/max+1);
            }
         }
         
         

      }
      
      
      
      
      out.close();
   }
   
      
}
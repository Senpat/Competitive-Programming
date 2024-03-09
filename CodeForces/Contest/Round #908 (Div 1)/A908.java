//make sure to make new file!
import java.io.*;
import java.util.*;

public class A908{
   
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
         
         boolean cycle = false;
         int len = 0;
         boolean[] seen = new boolean[n];
         
         int i = n-1;
         
         while(true){
            if(seen[i]){
               cycle = true;
               break;
            } else if(array[i] > n){
               break;
            }
            
            seen[i] = true;
            len++;
            i = (i-array[i]+n)%n;
         }
         
         if(cycle || len >= m){
            out.println("Yes");
         } else {
            out.println("No");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}
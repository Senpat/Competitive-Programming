//make sure to make new file!
import java.io.*;
import java.util.*;

public class Solution{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[] a = new int[n];
         int[] b = new int[n];
         
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            a[k] = Integer.parseInt(st.nextToken());
         }
         
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            b[k] = Integer.parseInt(st.nextToken());
         }
         
         int answer = 0;
         for(int l = 0; l < n; l++){
            for(int r = l; r < n; r++){
               int maxa = 0;
               int maxb = 0;
               for(int k = l; k <= r; k++){
                  maxa = Math.max(maxa,a[k]);
                  maxb = Math.max(maxb,b[k]);
               }
               if(Math.abs(maxa-maxb) <= m){
                  answer++;
               }
            }
         }
         
         out.println("Case #" + q + ": " + answer);
      
      }
      
      
      
      
      out.close();
   }
   
      
}
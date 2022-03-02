//make sure to make new file!
import java.io.*;
import java.util.*;

public class DG14{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         
         int[] lfreq = new int[n+1];
         int[] rfreq = new int[n+1];
         
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < l; k++){
            int i = Integer.parseInt(st.nextToken());
            lfreq[i]++;
         }
         for(int k = 0; k < r; k++){
            int i = Integer.parseInt(st.nextToken());
            rfreq[i]++;
         }
         
         for(int k = 1; k <= n; k++){
            int min = Math.min(lfreq[k],rfreq[k]);
            l -= min;
            r -= min;
            lfreq[k] -= min;
            rfreq[k] -= min;
         }
         
         int answer = Math.min(l,r);
         
         //you will never have to move more than half of every freq
         
         if(l != r){
            int dup = 0;            //number of times you can adjust l or r with cost of 1
            
            if(l > r){
               //move from l to r
               for(int k = 1; k <= n; k++){
                  dup += lfreq[k]/2;
               }
            } else {
               //move from r to l
               for(int k = 1; k <= n; k++){
                  dup += rfreq[k]/2;
               }
            }
            
            int dif = Math.abs((l-r)/2);
            
            if(dif <= dup) answer += dif;
            else answer += dup + 2*(dif-dup);
            
         }
         
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
      
}
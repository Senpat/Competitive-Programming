//make sure to make new file!
import java.io.*;
import java.util.*;

public class BPR{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
         
         Integer[] array = new Integer[m];
         for(int k = 0; k < m; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         Arrays.sort(array);
         
         int maxspread1 = n+5;
         int maxspread2 = n+5;
         for(int k = 0; k < m; k++){
            if(array[k] == 1) continue;
            //calculate max spread
            int cur = (n-1)/(array[k]-1);
            if(k != m-1){
               cur = (n-1)/(array[k]);
            }
            maxspread1 = Math.min(maxspread1,cur);
            
            cur = n/array[k];
            maxspread2 = Math.min(maxspread2,cur);
            
         } 
         
         if(Math.max(maxspread1,maxspread2) < x){
            out.println("NO");
         } else {
            out.println("YES");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}
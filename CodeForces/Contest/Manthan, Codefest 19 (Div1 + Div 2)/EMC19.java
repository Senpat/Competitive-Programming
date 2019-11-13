//make sure to make new file!
import java.io.*;
import java.util.*;

public class EMC19{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      
      //ArrayList<ArrayList<Long>> matrix = new ArrayList<ArrayList<Long>>(n);
      long[] answer = new long[w];
      for(int t = 0; t < n; t++){
         st = new StringTokenizer(f.readLine());
         
         int m = Integer.parseInt(st.nextToken());
         long[] array = new long[m];
         for(int k = 0; k < m; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         if(m == w){
            for(int k = 0; k < m; k++){
               answer[k] += array[k];
            }
         } else {
            //prefix forward
            long[] pmaxf = new long[m];
            pmaxf[0] = array[0];
            for(int k = 1; k < m; k++){
               pmaxf[k] = Math.max(pmaxf[k-1],array[k]);
            }
            //prefix backward
            long[] pmaxb = new long[m];
            pmaxb[m-1] = array[m-1];
            for(int k = m-2; k >= 0; k--){
               pmaxb[k] = Math.max(pmaxb[k+1],array[k]);
            }
            
            for(int k = 0; k < w; k++){
               if(k < m && w-k-1 < m){
                  long max = Long.MIN_VALUE;
                  for(int j = k-(w-m-1); j < k-(w-m-1) + w-m+1; j++){
                     max = Math.max(max,array[j]);
                  }
                  answer[k] += max; 
               } else if(k < m){
                  answer[k] += pmaxf[k];
               } else if(w-k-1 < m){
                  answer[k] += pmaxb[k];
               } else {
                  //add max in all
                  answer[k] += pmaxb[0];
               }
            }
         }
      }
      
      for(int k = 0; k < w; k++){
         out.print(answer[k] + " ");
      }
      

      
      
      
      
      out.close();
   }
   
      
}
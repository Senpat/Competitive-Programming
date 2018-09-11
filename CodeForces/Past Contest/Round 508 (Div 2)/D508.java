//Slime
import java.io.*;
import java.util.*;

public class D508{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long[] dp = new long[n];
      boolean[] filled = new boolean[n];
      Arrays.fill(filled,true);
      
      for(int k = 0; k < n; k++) dp[k] = Long.parseLong(st.nextToken());
      
      int[] left = new int[n];
      int[] right = new int[n];
      
      left[0] = -1;
      right[n-1] = -1;
      
      for(int k = 1; k < n; k++){
         left[k] = k-1;
      } 
      for(int k = 0; k < n-1; k++){
         right[k] = k+1;
      }
      
      for(int i = 0; i < n-2; i++){                   //do until there are 2 remaining
         //calculate max difference caused
         long max = 0L;
         int index = 0;
         long val = 0L;
         for(int k = 0; k < n; k++){
            if(!filled[k]) 
               continue;
            if(right[k]==-1) 
               continue;                //last, doesn't have a right
            
            long comb1 = dp[k]-dp[right[k]];
            long comb2 = dp[right[k]]-dp[k];
            long curmax = 0;
            long update = 0;
            if(left[k]!=-1){                          //check if max difference is to the left
               if(curmax < Math.abs(comb1-dp[left[k]])){
                  curmax = Math.abs(comb1-dp[left[k]]);
                  update = comb1;
               }
               if(curmax < Math.abs(comb2-dp[left[k]])){
                  curmax = Math.abs(comb2-dp[left[k]]);
                  update = comb2;
               }
            }
            if(right[right[k]]!=-1){
               if(curmax < Math.abs(comb1-dp[right[right[k]]])){
                  curmax = Math.abs(comb1-dp[right[right[k]]]);
                  update = comb1;
               }
               if(curmax < Math.abs(comb2-dp[right[right[k]]])){
                  curmax = Math.abs(comb2-dp[right[right[k]]]);
                  update = comb2;
               }
            }
            
            
            if((right[right[k]] == -1 && curmax > max) || (right[right[k]] != -1 && curmax >= max)){                  //if last it must be solely biggest
               max = curmax;
               index = k;
               val = update;
            }
            
         }
         //update arrays
         dp[index] = val;
         filled[right[index]] = false;
         left[right[index]] = left[index];
         right[index] = right[right[index]];
      }
      
      long answer = -1L;
      for(int k = 0; k < n; k++){
         if(filled[k]){
            if(answer == -1){
               answer = dp[k];
            } else {
               answer = Math.abs(answer-dp[k]);
            }
         }
      }
      
      out.println(answer);
      
      out.close();
   }
   
}
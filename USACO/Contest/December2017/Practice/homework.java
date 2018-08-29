/*
ID: patrickgzhang
LANG: JAVA
TASK: homework
*/

import java.io.*;
import java.util.*;

class homework{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("homework.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));
      
      int num = Integer.parseInt(f.readLine());
      int[] nums = new int[num];
      
      int[] sum = new int[num];
      int[] min = new int[num];
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      for(int k = 0; k < num; k++){
         nums[k] = Integer.parseInt(st.nextToken());
      }
      
      sum[num] = nums[num];
      min[num] = nums[num];
      
      for(int k = num-1; k>=0; k--){
         sum[k] = sum[k+1] + nums[k];
         min[k] = Math.min(min[k+1],nums[k]);
      }
      
      int max = 0;
      
      
      for(int k = num-2; k>=0; k--){
         int avg = (sum[k]-min[k])/(num-k-1);
         max = Math.max(max,avg);
      }
      
      for(int k = num-2; k>=0; k--){
         int avg = (sum[k]-min[k])/(num-k-1);
         if(avg == max){
            out.println(k);
         }
      }
      
      out.close();
   }
}
//make sure to make new file!
import java.io.*;
import java.util.*;

public class A673{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k]  =Integer.parseInt(st.nextToken());
         }
         
         int[] last = new int[n+1];
         Arrays.fill(last,-1);
         
         int[] maxdifs = new int[n+1];
         Arrays.fill(maxdifs,-1);
         
         for(int k = 0; k < n; k++){
            maxdifs[array[k]] = Math.max(maxdifs[array[k]],k-last[array[k]]);
            last[array[k]] = k;
         }
         
         for(int k = 1; k <= n; k++){
            maxdifs[k] = Math.max(maxdifs[k],n-last[k]);
         }
         
         int[] nums = new int[n+2];
         Arrays.fill(nums,Integer.MAX_VALUE);
         
         for(int k = 1; k <= n; k++){
            nums[maxdifs[k]] = Math.min(nums[maxdifs[k]],k);
         }
         
         int answer = Integer.MAX_VALUE;
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 1; k <= n; k++){
            answer = Math.min(answer,nums[k]);
            if(answer == Integer.MAX_VALUE) sj.add("-1");
            else sj.add("" + answer);
         }
         
         out.println(sj.toString());
      

      }
      
      
      
      
      out.close();
   }
   
      
}
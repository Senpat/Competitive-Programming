//make sure to make new file!
import java.io.*;
import java.util.*;

public class D125{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int C = Integer.parseInt(st.nextToken());
      
      long[] costmins = new long[C+1];
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
      
         int c = Integer.parseInt(st.nextToken());
         long d = Long.parseLong(st.nextToken());
         long h = Long.parseLong(st.nextToken());
         
         costmins[c] = Math.max(costmins[c],d*h);
      }
      
      long[] coins = new long[C+1];
      
      for(int k = 1; k <= C; k++){
         if(costmins[k] == 0) continue;
         
         long start = costmins[k]-1;
         for(int j = k; j <= C; j += k){
            coins[j] = Math.max(coins[j],start);
            start += costmins[k];
         }
      }
      
      for(int k = 2; k <= C; k++){
         coins[k] = Math.max(coins[k],coins[k-1]);
      }
      
      int q = Integer.parseInt(f.readLine());
      
      int[] answer = new int[q];
      
      for(int qq = 0; qq < q; qq++){
         st = new StringTokenizer(f.readLine());
      
         long D = Long.parseLong(st.nextToken());
         long H = Long.parseLong(st.nextToken());
      
         long prod = D*H;
         
         int l = 1;
         int r = C;
         int ans = -1;
         
         while(l <= r){
            int mid = l + (r-l)/2;
            if(prod <= coins[mid]){
               ans = mid;
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
         
         answer[qq] = ans;
         
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < q; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      out.close();
   }
   
      
}
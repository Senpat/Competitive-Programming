//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve, tutorial
public class B783b{


   public static long[] t;
   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int tt = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= tt; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         long[] psums = new long[n];
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            if(k == 0) psums[k] = array[k];
            else psums[k] = psums[k-1] + array[k];
         }
         
         Integer[] index = new Integer[n];
         for(int k = 0; k < n; k++) index[k] = k;
         
         //increasing prefix, if tie go decreasing
         Arrays.sort(index, new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
               if(psums[(int)a] > psums[(int)b]) return 1;
               else if(psums[(int)a] < psums[(int)b]) return -1;
               return (int)(b-a);
            }
         });
         
         int[] order = new int[n];
         for(int k = 0; k < n; k++) order[index[k]] = k;
         
         t = new long[4*n+5];
         Arrays.fill(t,Long.MIN_VALUE);      //or build
   
         long[] dp = new long[n+1];
         Arrays.fill(dp,Long.MIN_VALUE);
         dp[0] = 0;
         for(int k = 0; k < n; k++){
            //use array[k] to fill dp[k+1]
            if(array[k] == 0){
               dp[k+1] = dp[k];
            } else if(array[k] < 0){
               dp[k+1] = dp[k]-1;
            }
            
            if(k == 0 && array[k] > 0) dp[k+1] = 1;
            
            if(k > 0){
               dp[k+1] = Math.max(dp[k+1],max(1,0,n-1,0,order[k]) + k);
            }
            if(psums[k] > 0){
               dp[k+1] = Math.max(dp[k+1],k+1);
            }
            
            update(1,0,n-1,order[k],dp[k+1]-k);
         }
         
         out.println(dp[n]);
      

      }
      
      
      
      
      out.close();
   }
   
   //to call: v=1, tl = 0, tr = n-1
   public static long max(int v, int tl, int tr, int l, long r) {
      if (l > r) 
         return Long.MIN_VALUE;
      if (l == tl && r == tr) {
         return t[v];
      }
      int tm = (tl + tr) / 2;
      return Math.max(max(v*2, tl, tm, l, Math.min(r, tm)),max(v*2+1, tm+1, tr, Math.max(l, tm+1), r));
   }
   
   //to call: v=1, tl = 0, tr = n-1
   public static void update(int v, int tl, int tr, int pos, long new_val) {
      if (tl == tr) {
         t[v] = new_val;
      } else {
         int tm = (tl + tr) / 2;
         if (pos <= tm)
            update(v*2, tl, tm, pos, new_val);
         else
            update(v*2+1, tm+1, tr, pos, new_val);
         t[v] = Math.max(t[v*2],t[v*2+1]);
      }
   }
   
      
}
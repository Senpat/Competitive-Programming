//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
public class E833{

   public static long MOD = 1000000007L;

   public static int n,m;
   public static int[] array;
   
   public static int[][] ctree;
   
   public static long[][] dp;
   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         n = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
         
         array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         //[x][0] points to left child (> edge), [x][1] points to right child (>= edge)
         ctree = new int[n][2];
         
         for(int k = 0; k < n; k++){
            ctree[k][0] = -1;
            ctree[k][1] = -1;
         }
         
         //stores top is lowest, bottom is biggest
         Stack<Integer> stack = new Stack<Integer>();
         for(int k = 0; k < n; k++){
            while(!stack.empty() && array[stack.peek()] < array[k]){
               ctree[k][0] = stack.pop();
            }
            if(!stack.empty()){
               ctree[stack.peek()][1] = k;
            }
            stack.push(k);
         }
         //root is at the bottom
         int root = stack.pop();
         while(!stack.empty()) root = stack.pop();
         
         dp = new long[n][m+1];
         dfs(root);
         
         long answer = 0L;
         for(int k = 1; k <= m; k++){
            answer = (answer + dp[root][k] + MOD)%MOD;
         }
         
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
   public static void dfs(int root){
      int l = ctree[root][0];
      int r = ctree[root][1];
      if(l == -1 && r == -1){
         for(int k = 1; k <= m; k++){
            dp[root][k] = 1L;
         }
         return;
      }
      if(l != -1){
         dfs(l);
      }
      if(r != -1){
         dfs(r);
      }
      
      long suml = 0L;
      long sumr = 0L;
      
      for(int k = 1; k <= m; k++){
         if(r != -1) sumr = (sumr + dp[r][k] + MOD)%MOD;
         
         long prod = 1L;
         if(l != -1) prod = (prod * suml + MOD)%MOD;
         if(r != -1) prod = (prod * sumr + MOD)%MOD;
         
         dp[root][k] = prod;
         
         if(l != -1) suml = (suml + dp[l][k] + MOD)%MOD;
         
      }
      
   }
   
      
}
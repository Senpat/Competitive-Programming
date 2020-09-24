//make sure to make new file!
import java.io.*;
import java.util.*;

public class K{

   public static int n;
   public static int m;
   public static int[][][] dp;
   public static char[] array;
   public static int[] arrayc;
   
   public static ArrayList<Character> chars;
   public static HashMap<Character,Integer> hmap;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      array = f.readLine().toCharArray();
      
      HashSet<Character> hset = new HashSet<Character>();
      hmap = new HashMap<Character,Integer>();
      
      chars = new ArrayList<Character>();
      for(int k = 0; k < n; k++){
         if(!hset.contains(array[k])){
            hmap.put(array[k],chars.size());
            chars.add(array[k]);
            hset.add(array[k]);
         }
      }
      
      arrayc = new int[n];
      for(int k = 0; k < n; k++){
         arrayc[k] = hmap.get(array[k]);
      }
      
      dp = new int[n][n][m];
      
      int answer = Integer.MAX_VALUE;
      for(int k = 0; k < m; k++){
         answer = Math.min(answer,dp(0,n-1,k));
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
   public static int dp(int l, int r, int c){
      if(dp[l][r][c] != 0) return dp[l][r][c];
      
      //get characters that are still remaining excluding c
      HashSet<Integer> hset = new HashSet<Integer>();
      for(int k = l; k <= r; k++){
         hset.add(arrayc[k]);
      }
      
      ArrayList<Integer> ls = new ArrayList<Integer>();
      ArrayList<Integer> rs = new ArrayList<Integer>();
      
      int comps = 0;
      
      int start = l;
      for(int k = l; k <= r; k++){
         if(arrayc[k] == c && (k == l || arrayc[k-1] != c)) comps++;
      
         if(arrayc[k] == c){
            if(k == start){
               start++;
            } else {
               ls.add(start);
               rs.add(k-1);
               start = k+1;
            }
         }
      }
      
      if(arrayc[r] != c){
         ls.add(start);
         rs.add(r);
      }
      
      
      int ret = Integer.MAX_VALUE;
      for(int ch : hset){
         if(ch == c) continue;
         
         int cur = 0;
         for(int k = 0; k < ls.size(); k++){
            cur += dp(ls.get(k),rs.get(k),ch);
         }
         
         ret = Math.min(ret,cur);
      }
      
      if(ret == Integer.MAX_VALUE) ret = 0;
      return dp[l][r][c] = ret + comps;
   }
}
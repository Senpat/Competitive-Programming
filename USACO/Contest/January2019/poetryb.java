/*
TASK: poetry
LANG: JAVA
*/
//MLE test 16
import java.io.*;
import java.util.*;

class poetryb{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("poetry.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("poetry.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());
      
      Word[] words = new Word[n];
      
      for(int k = 0; k < n; k++){
         
         st = new StringTokenizer(f.readLine());
      
         int sy = Integer.parseInt(st.nextToken());
         int rh = Integer.parseInt(st.nextToken());
      
         words[k] = new Word(sy,rh);
      }
      
      HashMap<Character,Integer> hmap = new HashMap<Character,Integer>();
      
      
      for(int k = 0; k < m; k++){
         char c = f.readLine().charAt(0);
         if(hmap.containsKey(c)){
            hmap.put(c,hmap.get(c)+1);
         } else {
            hmap.put(c,1);
         }
      }
      
      int max = 0;
      for(char c : hmap.keySet()){
         max = Math.max(max,hmap.get(c));
      }
      
      
      long[] dp = new long[s+1];
      dp[0] = 1L;
      
      long[] r = new long[n+1];
      
      for(int k = 0; k <= s; k++){
         
         for(int j = 0; j < n; j++){
            if(words[j].s + k > s) continue;
            if(words[j].s + k == s){
               r[words[j].r] = (r[words[j].r] + dp[k] + MOD) % MOD;
            }
            dp[words[j].s + k] = (dp[words[j].s + k] + dp[k] + MOD) % MOD;
         }
      }
      
      long[][] rpow = new long[n+1][max+1];
      for(int k = 1; k <= n; k++){
         rpow[k][0] = 1L;
      }
      long[] rsums = new long[max+1];
      
      for(int k = 1; k <= n; k++){
         
         for(int j = 1; j <= max; j++){
            rpow[k][j] = (rpow[k][j-1] * r[k] + MOD) % MOD;
            rsums[j] = (rsums[j] + rpow[k][j] + MOD) % MOD;
         }
      }
      
      
      
      
      
      
      long answer = 1L;
      for(char c : hmap.keySet()){
         answer = (answer * rsums[hmap.get(c)] + MOD) % MOD;
      }
      
      System.out.println(answer);
      out.println(answer);
      
         
      
      
      
        
      out.close();
   }
   
   public static class Word{
      int s;
      int r;
      public Word(int a, int b){
         s = a;
         r = b;
      }
   }
         
      
}
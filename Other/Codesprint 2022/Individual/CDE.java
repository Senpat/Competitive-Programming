//make sure to make new file!
import java.io.*;
import java.util.*;

public class CDE{
   
   public static long base = 29;
   public static long MOD = 1000000007L;
   
   public static int n;
   
   public static int[][] adj;
   public static Stack<Integer> stack;
   public static HashSet<Integer> hset;
   public static boolean[] seen;
   public static boolean cycle;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 2005;
      long[] pow = new long[N];
      pow[0] = 1L;
      for(int k = 1; k < N; k++){
         pow[k] = (pow[k-1]*base + MOD)%MOD;
      }
     
   
      n = Integer.parseInt(f.readLine());
   
      String[] array = new String[n];
      for(int k = 0; k < n; k++){
         array[k] = f.readLine();
      }
      
      ArrayList<ArrayList<Long>> front = new ArrayList<ArrayList<Long>>(n);
      ArrayList<ArrayList<Long>> back = new ArrayList<ArrayList<Long>>(n);
      for(int k = 0; k < n; k++){
         front.add(new ArrayList<Long>());
         back.add(new ArrayList<Long>());
      }
      
      for(int k = 0; k < n; k++){
         long hashf = 0L;
         long hashb = 0L;
         
         for(int j = 0; j < array[k].length(); j++){
            long addf = (pow[j]*(long)(array[k].charAt(j)-'`') + MOD)%MOD;
            long addb = (pow[j]*(long)(array[k].charAt(array[k].length()-j-1)-'`') + MOD)%MOD;
            
            hashf = (hashf + addf + MOD)%MOD;
            hashb = (hashb + addb + MOD)%MOD;
            
            front.get(k).add(hashf);
            back.get(k).add(hashb);
         }
      }
      
      
         
      adj = new int[n][n];
      for(int k = 0; k < n; k++) Arrays.fill(adj[k],0);
      
      int l;
      int r;
      int ans;
      for(int k = 0; k < n; k++){
         for(int j = k+1; j < n; j++){
            //fill adj[k][j]
            ans = -1;
            for(int mid = Math.min(array[k].length(),array[j].length())-1; mid >= 0; mid--){
               
               //hash of front is front.get(j).get(mid);
               //hash of back
               long hashb = front.get(k).get(front.get(k).size()-1);
               if(mid != array[k].length()-1){
                  int size = front.get(k).size();
                  hashb = ((hashb-front.get(k).get(size-mid-2))%MOD+MOD)%MOD;
                  hashb = (hashb * modInverse(pow[size-mid-1],MOD) + MOD)%MOD;
               }
               
               if(hashb == front.get(j).get(mid)){
                  ans = mid;
                  break;
               }
            }
            
            adj[k][j] = ans+1;
            
            ans = -1;
            
            for(int mid = Math.min(array[k].length(),array[j].length())-1; mid >= 0; mid--){
            
               //hash of front is front.get(k).get(mid-1);
               //hash of back
               long hashb = front.get(j).get(front.get(j).size()-1);
               if(mid != array[j].length()-1){
                  int size = front.get(j).size();
                  hashb = ((hashb-front.get(j).get(size-mid-2))%MOD+MOD)%MOD;
                  hashb = (hashb * modInverse(pow[size-mid-1],MOD) + MOD)%MOD;
               }
               
               if(hashb == front.get(k).get(mid)){
                  ans = mid;
                  break;
               }
            }
            
            adj[j][k] = ans+1;
            
            
         }
      }
         
      //do top sort
      /*
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            out.print(adj[k][j] + " ");
         }
         out.println();
      }*/
      
      stack = new Stack<Integer>();
      seen = new boolean[n];
      hset = new HashSet<Integer>();
      cycle = false;
      for(int k = 0; k < n; k++){
         
         if(!seen[k]){
            hset = new HashSet<Integer>();
            topsort(k);
         }
      }
      
      if(cycle){
         out.println("Shakespeare, who?");
         out.close();
         return;
      }
      
      //dp
      ArrayList<Integer> order = new ArrayList<Integer>();
      while(!stack.empty()){
         order.add(stack.pop());
      }
      
      int[] dp = new int[n];
      for(int k = 0; k < order.size(); k++){
         int v = order.get(k);
         for(int nei = 0; nei < n; nei++){
            if(nei == v) continue;
            dp[nei] = Math.max(dp[nei],dp[v] + adj[v][nei]);
         }
      }
      
      int max = 0;
      for(int k = 0; k < n; k++){
         max = Math.max(max,dp[k]);
      }
      
      out.println(max);
      
      out.close();
   }
   
   public static void topsort(int v){
      if(hset.contains(v)) cycle = true;
      if(cycle) return;
      
      hset.add(v);
      
      for(int nei = 0; nei < n; nei++){
         if(nei == v) continue;
         if(adj[v][nei] == 0) continue;
         if(seen[v]) continue;
         
         topsort(nei);
      }
      
      seen[v] = true;
      stack.add(v);
   }   
   
         //from geeksforgeeks
   public static long modInverse(long a, long m) 
   { 
      long m0 = m; 
      long y = 0;
      long x = 1; 
     
      if (m == 1) 
         return 0; 
     
      while (a > 1) 
      { 
           // q is quotient 
         long q = a / m; 
         long t = m; 
      
           // m is remainder now, process same as 
           // Euclid's algo 
         m = a % m;
         a = t; 
         t = y; 
      
           // Update y and x 
         y = x - q * y; 
         x = t; 
      } 
     
       // Make x positive 
      if (x < 0) 
         x += m0; 
      return x; 
   } 
   
   
   
}
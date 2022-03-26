//make sure to make new file!
import java.io.*;
import java.util.*;
//binary lifting solution
public class Kb{

   public static int n;
   public static long mod;
   
   public static long[] values;
   
   public static int D = 18;
   public static int[][] jmp;
   public static int[] depths;
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      mod = Long.parseLong(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
         
      
      values = new long[n+1];
      for(int k = 1; k <= n; k++){
         values[k] = Long.parseLong(f.readLine());
      }
      
      int N = 200005;
      long[] pow10 = new long[N];
      pow10[0] = 1L;
      for(int k = 1; k < N; k++){
         pow10[k] = (10L * pow10[k-1] + mod)%mod;
      }
      
      
      //precomp jmp
      jmp = new int[n+1][D];
      depths = new int[n+1];
      depths[1] = 0;
      dfs(1,-1);
      
      long[][] down = new long[n+1][D];
      long[][] up = new long[n+1][D];
      for(int k = 1; k <= n; k++){
         down[k][0] = values[k];
         up[k][0] = values[k];
      }
      
      for(int d = 1; d < D; d++){
         for(int k = 1; k <= n; k++){
            jmp[k][d] = jmp[jmp[k][d-1]][d-1];
            
            down[k][d] = (down[jmp[k][d-1]][d-1] * pow10[1 << (d-1)] + mod)%mod;
            down[k][d] = (down[k][d] + down[k][d-1] + mod)%mod;
            
            up[k][d] = (up[k][d-1] * pow10[1 << (d-1)] + mod)%mod;
            up[k][d] = (up[k][d] + up[jmp[k][d-1]][d-1] + mod)%mod;
            
         }
      }
      
      
      
      
      
      
      
      
      for(int qq = 0; qq < q; qq++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         
         //a up to lca, lca down to b, from most significant to least significant
         int lcaadjust = 0;                //pow of 10 to multiple lca by later
         
         long upans = 0L;
         long downans = 0L;
         if(depths[a] > depths[b]){
            for(int k = D-1; k >= 0; k--){
               if(((depths[a]-depths[b]) & (1 << k)) != 0){
                  long prod = (upans*pow10[1 << k] + mod)%mod;
                  upans = (prod + up[a][k] + mod)%mod;
                  
                  a = jmp[a][k];
               }
            }
         }
         
         if(depths[b] > depths[a]){
            for(int k = D-1; k >= 0; k--){
               if((depths[b]-depths[a] & (1 << k)) != 0){
                  long prod = (down[b][k]*pow10[lcaadjust] + mod)%mod;
                  downans = (prod + downans + mod)%mod;
                  
                  b = jmp[b][k];
                  
                  lcaadjust += (1 << k);
               }
            }
         }
         
         if(a == b){
            upans = (upans * pow10[lcaadjust+1] + mod)%mod;
            long lcaans = (values[a] * pow10[lcaadjust] + mod)%mod;
            long answer = (upans + downans + lcaans + mod)%mod;
            out.println(answer);
            continue;
         }
         
         for(int k = D-1; k >= 0; k--){
            if(jmp[a][k] != jmp[b][k]){
               long prod = (upans*pow10[1 << k] + mod)%mod;     
               upans = (prod + up[a][k] + mod)%mod;
               
               prod = (down[b][k]*pow10[lcaadjust] + mod)%mod;
               downans = (prod + downans + mod)%mod;
               
               a = jmp[a][k];
               b = jmp[b][k];
               
               lcaadjust += (1 << k);
            }
         }
         
         
         //add a and b to upans and downans
         upans = (upans*10L + values[a] + mod)%mod;
         downans = (downans + values[b]*pow10[lcaadjust] + mod)%mod;
         lcaadjust++;
         
         upans = (upans * pow10[lcaadjust+1] + mod)%mod;
         long lcaans = (values[jmp[a][0]] * pow10[lcaadjust] + mod)%mod;           //jmp[a][0] is the lca
         long answer = (upans + downans + lcaans + mod)%mod;
         out.println(answer);
         
         
         
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         jmp[nei][0] = v;
         depths[nei] = depths[v]+1;
         dfs(nei,v);
      }
   }
   
      
}
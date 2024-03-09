//make sure to make new file!
import java.io.*;
import java.util.*;

public class C901{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 5005;
      //probability that you go to each neighbor for every even # of neighbors
      //it is optimal to select the highest probability neighbors first
      double[][] probs = new double[N][N];
      
      probs[2][0] = 0.0;
      probs[2][1] = 0.5;
      
      for(int k = 4; k < N; k += 2){
         double dk = (double)k;
         probs[k][k-1] = 1.0/dk;
         //use probs[k-2]
         for(int j = 0; j < k-2; j++){
            probs[k][j] += probs[k-2][j] * (k-2-j) / dk;
            probs[k][j+1] += probs[k-2][j] * (j+1) / dk;
         }
      }
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adj.get(a).add(b);
         }
         
         double[] dp = new double[n+1];
         dp[n] = 1.0;
         
         for(int k = n-1; k >= 1; k--){
            if(adj.get(k).size() % 2 == 1){
               //for odd # of neighbors, the probability is the average
               double psum = 0.0;
               for(int nei : adj.get(k)){
                  psum += dp[nei];
               }
               
               dp[k] = psum/adj.get(k).size();
            } else {
               //for even, use the probs matrix
               int curn = adj.get(k).size();
               ArrayList<Double> p = new ArrayList<Double>();
               for(int nei : adj.get(k)){
                  p.add(dp[nei]);
               }
               Collections.sort(p);
               
               for(int j = 0; j < curn; j++){
                  dp[k] += p.get(j) * probs[curn][j];
               }
            }
         }
         
         out.println(dp[1]);

      }
      
      
      
      
      out.close();
   }
   
      
}
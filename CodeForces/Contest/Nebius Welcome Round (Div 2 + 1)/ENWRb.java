//make sure to make new file!
import java.io.*;
import java.util.*;
//better method of finding all cycles
//bitwise dp to find cycles
public class ENWRb{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int np2 = (1 << n);
      
      //0-INDEXED NODES
      ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(n);
      for(int k = 0; k < n; k++) adj.add(new ArrayList<Integer>());
      
      boolean[][] adjm = new boolean[n][n];
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken())-1;
         int b = Integer.parseInt(st.nextToken())-1;
      
         adj.get(a).add(b);
         adj.get(b).add(a);
         
         adjm[a][b] = true;
         adjm[b][a] = true;
      }
      
      //get all cycles
      //stores mask of cycles
      ArrayList<ArrayList<Integer>> cycles = new ArrayList<ArrayList<Integer>>(np2);
      for(int k = 0; k <= np2; k++) cycles.add(new ArrayList<Integer>());
      
      for(int k = n-1; k >= 0; k--){
         int[][] dp = new int[k+1][1 << (k+1)];         //stores if it is possible to go from a to b with mask (stores parent)
         for(int j = 0; j <= k; j++) Arrays.fill(dp[j],-1);
         
         dp[k][(1 << k)] = k;
         for(int mask = 0; mask < (1 << (k+1)); mask++){
            for(int to = 0; to <= k; to++){
               if(dp[to][mask] == -1) 
                  continue;
               for(int nei = 0; nei < k; nei++){
                  if(adjm[to][nei] && (mask & (1 << nei)) == 0 && dp[nei][mask ^ (1 << nei)] == -1){
                     dp[nei][mask ^ (1 << nei)] = to;
                  }
               }
               //nei == k
               if(adjm[to][k] && cycles.get(mask).size() == 0){
                  cycles.get(mask).add(k);
                  cycles.get(mask).add(to);
                  int prev = to;
                  int curmask = mask;
                  int p = dp[to][curmask];
                  while(p != k){
                     cycles.get(mask).add(p);
                     int temp = p;
                     curmask ^= (1 << prev);
                     p = dp[p][curmask];
                     prev = temp;
                  }
                     
                  
               }
            }  
         }
      }
      
      boolean foundanswer = false;
      for(int mask = 0; mask < np2; mask++){
         if(cycles.get(mask).size() == 0) 
            continue;
         //see if every node is in it or 1 away
         boolean fail = false;
         int[] answer = new int[n];
         Arrays.fill(answer,-1);
         for(int k = 0; k < n; k++){
            if((mask & (1 << k)) != 0) 
               continue;
            for(int nei : adj.get(k)){
               if((mask & (1 << nei)) != 0){
                  answer[k] = nei;
               }
            }
            if(answer[k] == -1){
               fail = true;
               break;
            }
         }
         
         if(fail) 
            continue;
         //found answer
         for(int k = 0; k < cycles.get(mask).size()-1; k++){
            answer[cycles.get(mask).get(k)] = cycles.get(mask).get(k+1);
         }
         answer[cycles.get(mask).get(cycles.get(mask).size()-1)] = cycles.get(mask).get(0);
         out.println("Yes");
         for(int k = 0; k < n; k++){
            out.print((answer[k]+1) + " ");
         }
         out.println();
         foundanswer = true;
         break;
      }
      
      
      if(!foundanswer){
         out.println("No");
      }
      
      
      
      
      
      out.close();
   }
   
   
      
}
//make sure to make new file!
import java.io.*;
import java.util.*;
//wrong, assumes there is only 1 component
public class E87b{

   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] parity;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      StringTokenizer st = new StringTokenizer(f.readLine());
   
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int n1 = Integer.parseInt(st.nextToken());
      int n2 = Integer.parseInt(st.nextToken());
      int n3 = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      parity = new int[n+1];
      Arrays.fill(parity,-1);
      
      ArrayList<HashSet<Integer>> a = new ArrayList<HashSet<Integer>>();
      ArrayList<HashSet<Integer>> b = new ArrayList<HashSet<Integer>>();
      
      for(int k = 1; k <= n; k++){
         if(parity[k] != -1) 
            continue;
         
         HashSet<Integer> hseta = new HashSet<Integer>();
         HashSet<Integer> hsetb = new HashSet<Integer>();
         
         parity[k] = 0;
      
         Queue<Integer> q = new LinkedList<Integer>();
         q.add(k);
      
         while(!q.isEmpty()){
            int i = q.poll();
            
            if(parity[i] == 0) hseta.add(i);
            else hsetb.add(i);
            
            for(int nei : adj.get(i)){
               if(parity[nei] == parity[i]) {
                  out.println("NO");
                  out.close();
                  return;
               }
               if(parity[nei] != -1) 
                  continue;
               parity[nei] = 1-parity[i];
               q.add(nei);
            }
         }
         
         a.add(hseta);
         b.add(hsetb);
      }
      
      int[][] dp = new int[a.size()][n+1];
      
      for(int k = 0; k < a.size(); k++) Arrays.fill(dp[k],Integer.MIN_VALUE);
      
      dp[0][a.get(0).size()] = 1;
      dp[0][b.get(0).size()] = 2;
      
      for(int k = 0; k < a.size()-1; k++){
         for(int j = 0; j <= n; j++){
            if(dp[k][j] == Integer.MIN_VALUE) continue;
            //add a, set to 1
            dp[k+1][j+a.get(k+1).size()] = 1;
            //add b, set to 2
            dp[k+1][j+b.get(k+1).size()] = 2;
         }
      }
      
      if(dp[a.size()-1][n2] == Integer.MIN_VALUE){
         out.println("NO");
      } else {
         HashSet<Integer> two = new HashSet<Integer>();
         
         int x = n2;
         
         for(int k = a.size()-1; k >= 0; k--){
            if(dp[k][x] == 1){
               for(int i : a.get(k)) two.add(i);
               x -= a.get(k).size();
            } else {
               for(int i : b.get(k)) two.add(i);
               x -= b.get(k).size();
            }
         }
         
         StringJoiner sj = new StringJoiner("");
         int ones = 0;
         for(int k = 1; k <= n; k++){
            if(two.contains(k)){
               sj.add("2");
            } else {
               if(ones < n1){
                  sj.add("1");
                  ones++;
               } else {
                  sj.add("3");
               }
            }
         }
         
         out.println("YES");
         out.println(sj.toString());
      
      }
      
      
      
      
      
            
      
      
      
      
      
      out.close();
   }
   

      
}
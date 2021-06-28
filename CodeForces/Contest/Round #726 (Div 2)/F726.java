//make sure to make new file!
import java.io.*;
import java.util.*;

public class F726{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int qq = 1; qq <= t; qq++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         long[] val = new long[n+1];
         long[] tar = new long[n+1];
         
         st = new StringTokenizer(f.readLine());
         for(int k = 1; k <= n; k++){
            val[k] = Long.parseLong(st.nextToken());
         }
         st = new StringTokenizer(f.readLine());
         for(int k = 1; k <= n; k++){
            tar[k] = Long.parseLong(st.nextToken());
         }
         
         long[] dif = new long[n+1];
         long sumdif = 0L;
         for(int k = 1; k <= n; k++){
            dif[k] = tar[k]-val[k];
            sumdif += dif[k];
         }
         
         ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
      
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adj.get(a).add(b);
            adj.get(b).add(a);
         }
         
         if(Math.abs(sumdif) % 2 == 1){
            out.println("NO");
            continue;
         }
         
         //check parity of cycle
         int[] parity = new int[n+1];
         Arrays.fill(parity,-1);
         parity[1] = 0;
         
         boolean hasoddcycle = false;
         
         Queue<Integer> q = new LinkedList<Integer>();
         q.add(1);
         
         while(!q.isEmpty()){
            int x = q.poll();
            
            for(int nei : adj.get(x)){
               if(parity[nei] == -1){
                  parity[nei] = 1-parity[x];
                  q.add(nei);
               } else if(parity[nei] == parity[x]){
                  hasoddcycle = true;
                  break;
               }
            }
            
            if(hasoddcycle) break;
         }
         
         if(hasoddcycle){
            out.println("YES");
         } else {
            long sumdifodd = 0L;
            long sumdifeven = 0L;
            
            for(int k = 1; k <= n; k++){
               if(parity[k] == 1) sumdifodd += dif[k];
               else sumdifeven += dif[k];
            }
            
            if(sumdifodd == sumdifeven){
               out.println("YES");
            } else {
               out.println("NO");
            }
         }

      }
      
      
      
      
      out.close();
   }
   
      
}
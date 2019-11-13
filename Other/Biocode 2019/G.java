/*
If you want to aim high, aim high
Don't let that studying and grades consume you
Just live life young
****************************
*/
import java.util.*;
import java.io.*;

   public class G
   {
   
      public static ArrayList<ArrayList<Integer>> adj;
      public static HashSet<Integer> hs;
      public static long[] answer;
      
      public static void main(String args[]) throws Exception
      {
         BufferedReader infile = new BufferedReader(new InputStreamReader(System.in));  
         StringTokenizer st = new StringTokenizer(infile.readLine());
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         adj = new ArrayList<ArrayList<Integer>>(n+1);
         
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(infile.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adj.get(a).add(b);
         }
         
         hs = new HashSet<Integer>();
         answer = new long[n+1];
         
         for(int k = 1; k <= n; k++){
            if(!hs.contains(k)){
               dfs(k);
            }
         }
         
         for(int k = 1; k <= n; k++){
            System.out.println(answer[k]);
         }
      }
      
      public static void dfs(int v){
         if(hs.contains(v)) return;
         hs.add(v);
         
         long count = 0;
         for(int nei : adj.get(v)){
            dfs(nei);
            count += answer[nei]+1;
         }
         
         answer[v] = count;
      }
      
      
   }
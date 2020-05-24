//make sure to make new file!
import java.io.*;
import java.util.*;
//TLE, FIXED IN F165b
public class F165{

   public static int[] values;
   public static ArrayList<ArrayList<Integer>> adj;
   public static ArrayList<TreeMap<Integer,Integer>> lis;
   public static int[] answer;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      values = new int[n+1];
      for(int k = 1; k <= n; k++){
         values[k] = Integer.parseInt(st.nextToken());
      }
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      
      lis = new ArrayList<TreeMap<Integer,Integer>>();
      answer = new int[n+1];
      
      dfs(1, -1);
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 1; k <= n; k++){
         sj.add(""+answer[k]);
      }
      out.println(sj.toString());
      
      
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      int loc = -1;
      //get answer for itself
      if(lis.isEmpty()){
         lis.add(new TreeMap<Integer,Integer>());
         lis.get(0).put(values[v],1);
         loc = 0;
      } else if(values[v] <= lis.get(0).firstKey()){
          //add to first list
          if(lis.get(0).containsKey(values[v])){
            lis.get(0).put(values[v],lis.get(0).get(values[v])+1);
          } else {
            lis.get(0).put(values[v],1);
         }
         loc = 0;
      } else if(values[v] > lis.get(lis.size()-1).firstKey()){
         //add
         lis.add(new TreeMap<Integer,Integer>());
         lis.get(lis.size()-1).put(values[v],1);
         loc = lis.size()-1;
      } else {
         int l = 0;
         int r = lis.size()-1;
         int ans = r;
         
         while(l <= r){
            int mid = l + (r-l)/2;
            
            if(values[v] <= lis.get(mid).firstKey()){
               r = mid-1;
               ans = mid;
            } else {
               l = mid+1;
               
            }
         }
         
         if(lis.get(ans).containsKey(values[v])){
            lis.get(ans).put(values[v],lis.get(ans).get(values[v])+1);
         } else {
            lis.get(ans).put(values[v],1);
         }
         loc = ans;
      }
      
      answer[v] = lis.size();
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfs(nei,v);
      }
      
      //remove
      if(lis.get(loc).get(values[v]) > 1){
         lis.get(loc).put(values[v],lis.get(loc).get(values[v])-1);
      } else {
         lis.get(loc).remove(values[v]);
         if(lis.get(loc).isEmpty()){
            lis.remove(lis.size()-1);
         }
      }
      
   }
}
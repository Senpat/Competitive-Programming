//make sure to make new file!
import java.io.*;
import java.util.*;
//replace treeset with arraylist
public class F165b{

   public static int[] values;
   public static ArrayList<ArrayList<Integer>> adj;
   public static ArrayList<ArrayList<Integer>> lis;
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
      
      
      lis = new ArrayList<ArrayList<Integer>>();
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
         lis.add(new ArrayList<Integer>());
         lis.get(0).add(values[v]);
         loc = 0;
      } else if(values[v] <= lis.get(0).get(lis.get(0).size()-1)){
          //add to first list
         lis.get(0).add(values[v]);
         
         loc = 0;
      } else if(values[v] > lis.get(lis.size()-1).get(lis.get(lis.size()-1).size()-1)){
         //add
         lis.add(new ArrayList<Integer>());
         lis.get(lis.size()-1).add(values[v]);
         loc = lis.size()-1;
      } else {
         int l = 0;
         int r = lis.size()-1;
         int ans = r;
         
         while(l <= r){
            int mid = l + (r-l)/2;
            
            if(values[v] <= lis.get(mid).get(lis.get(mid).size()-1)){
               r = mid-1;
               ans = mid;
            } else {
               l = mid+1;
               
            }
         }
         
         
         lis.get(ans).add(values[v]);
         
         loc = ans;
      }
      
      answer[v] = lis.size();
      
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         dfs(nei,v);
      }
      
      //remove
      lis.get(loc).remove(lis.get(loc).size()-1);
      if(lis.get(loc).isEmpty()){
         lis.remove(lis.size()-1);
      }
      
      
   }
}